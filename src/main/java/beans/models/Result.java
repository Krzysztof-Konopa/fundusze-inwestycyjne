package beans.models;

public class Result {

    private Fund fund;

    private String cashValue;

    private String percentValue;

    public Result(){}

    public Result(Fund fund, String cashValue, String percentValue) {
        this.fund = fund;
        this.cashValue = cashValue;
        this.percentValue = percentValue;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getCashValue() {
        return cashValue;
    }

    public void setCashValue(String cashValue) {
        this.cashValue = cashValue;
    }

    public String getPercentValue() {
        return percentValue;
    }

    public void setPercentValue(String percentValue) {
        this.percentValue = percentValue;
    }
}
