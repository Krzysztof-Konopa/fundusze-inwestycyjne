package beans.services.impl;

import beans.daos.StrategyDAO;
import beans.dtos.InvestmentDTO;
import beans.models.Fund;
import beans.models.Investment;
import beans.models.Result;
import beans.models.Strategy;
import beans.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    private static final int PERCENT_SCALE = 2;
    private static final int CASH_SCALE = 0;

    @Autowired
    private StrategyDAO strategyDAO;

    @Override
    @Transactional
    public Investment compute(InvestmentDTO investmentDTO) {

        long cash = investmentDTO.getCash();
        long remainCash = investmentDTO.getCash();
        List<Fund> funds = investmentDTO.getFunds();
        String strategyName = investmentDTO.getStrategyName();
        Strategy strategy = strategyDAO.getByName(strategyName);

        List<Result> allResults = new ArrayList<>();

        for(Map.Entry<String, String> entry : strategy.getVolumesMap().entrySet()) {
            List<Fund> fundsPerType = funds.stream()
                    .filter(fund -> fund.getType().equals(entry.getKey()))
                    .collect(Collectors.toList());

            List<Result> results = getResultsFromFunds(entry.getValue(), fundsPerType, cash, remainCash);
            remainCash -= sumInvestedCash(results);

            allResults = Stream.of(allResults, results)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        }

        return new Investment(allResults, remainCash);
    }

    private List<Result> getResultsFromFunds(String percent, List<Fund> funds, long cash, long remainCash) {
        BigDecimal fundPercent = new BigDecimal(percent);
        BigDecimal overallCash = new BigDecimal(cash);
        BigDecimal percentPerFund;
        BigDecimal cashPerFund;
        int fundCount = funds.size();
        List<Result> results = new ArrayList<>();

        for(Fund fund : funds) {
            percentPerFund = fundPercent.divide(new BigDecimal(fundCount), PERCENT_SCALE, BigDecimal.ROUND_FLOOR);
            cashPerFund = percentPerFund.multiply(overallCash).divide(new BigDecimal("100"), CASH_SCALE, BigDecimal.ROUND_FLOOR);
            remainCash -= cashPerFund.longValue();

            if(remainCash < 0) {
                cashPerFund = BigDecimal.ZERO;
            }

            results.add(new Result(fund, cashPerFund.toString(), percentPerFund.toString()));

            fundCount--;
            fundPercent = fundPercent.subtract(percentPerFund);
        }
        return results;
    }

    private long sumInvestedCash(List<Result> results) {
        return results.stream()
                .mapToLong(result -> Long.valueOf(result.getCashValue()))
                .sum();
    }
}
