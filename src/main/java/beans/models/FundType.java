package beans.models;

public enum FundType {
    POLISH("Polish"), FOREIGN("Foreign"), CASH("Cash");

    private final String type;

    FundType(String type) {
        this.type = type;
    }

    public String value() {
        return this.type;
    }
}
