package ch.timor.projects.currencyconverter.model;

public enum URL {
    BASE_URL("https://api.currencylayer.com/"), API_KEY("?access_key=b683cbc749d902ae2af12800a94788d7"),
    CONVERSION("convert"), CURRENCY_LIST("list");

    private final String value;

    URL(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
