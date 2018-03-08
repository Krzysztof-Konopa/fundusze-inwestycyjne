package beans.services;

import beans.dtos.InvestmentDTO;
import beans.models.Investment;

import java.util.Objects;

public interface InvestmentService {

    public Investment compute(InvestmentDTO investment);

    static void validateInvestmentDTO(InvestmentDTO investmentDTO) {
        if(Objects.isNull(investmentDTO)) {
            throw new NullPointerException("Investment is [null]");
        }
        if(Objects.isNull(investmentDTO.getStrategyName())) {
            throw new NullPointerException("Strategy name is [null]. InvestmentDTO: [" + investmentDTO + "]");
        }
        if(Objects.isNull(investmentDTO.getFunds())) {
            throw new NullPointerException("Funds Collection is [null]. InvestmentDTO: [" + investmentDTO + "]");
        }
        investmentDTO.getFunds().forEach(fund -> {
            if(Objects.isNull(fund.getType())) {
                throw new NullPointerException("Type in setup investment is [null]. In [" + fund + "]" +
                        "This parameter is mandatory for further computations");
            }
        });
    }
}
