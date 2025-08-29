package ch.timor.projects.currencyconverter.model;

import java.util.Map;

public class CurrencyListResponse {
    private String success;
    private Map<String, String> currencies;

    public String getSuccess() {
        return success;
    }

    public Map<String, String> getCurrencies() {
        return currencies;
    }
}
