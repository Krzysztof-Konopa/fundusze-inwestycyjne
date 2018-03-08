package beans.dtos;

import beans.models.Fund;

import java.util.List;

public class InvestmentDTO {

    private long cash;

    private String strategyName;

    private List<Fund> funds;

    public long getCash() {
        return cash;
    }

    public void setCash(long cash) {
        this.cash = cash;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategy) {
        this.strategyName = strategy;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public void setFunds(List<Fund> funds) {
        this.funds = funds;
    }
}
