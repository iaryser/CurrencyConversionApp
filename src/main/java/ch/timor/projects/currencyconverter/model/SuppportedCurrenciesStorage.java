package ch.timor.projects.currencyconverter.model;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class SuppportedCurrenciesStorage {
    private HashMap<String, String> currencyMap = new HashMap<>();
    public static Logger LOG = LoggerFactory.getLogger(SuppportedCurrenciesStorage.class);

    public SuppportedCurrenciesStorage() {
        this.fillMapWithSupportedCurrencies();
    }

    private String requestListOfAvailableCurrencies() {
        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpRequest listReq = HttpRequest.newBuilder()
                    .uri(new URI(URL.BASE_URL.getValue() + URL.CURRENCY_LIST.getValue() + URL.API_KEY.getValue()))
                    .GET()
                    .build();

            HttpResponse<String> currencyJson = client.send(listReq, HttpResponse.BodyHandlers.ofString());
            return currencyJson.body();

        } catch (URISyntaxException e) {
            LOG.error("Invalid URI: {}", e.getInput());
        } catch (IOException e) {
            LOG.error("Request failed due to I/O problem: {}", e.getMessage());
        } catch (InterruptedException e) {
            LOG.error("Request inetrrupted: {}", e.getMessage());
        }
        return "";
    }

    private void fillMapWithSupportedCurrencies() {
        String json = requestListOfAvailableCurrencies();
        if(json.isEmpty()) {
            LOG.error("No currencies retrieved!");
            return;
        }
        Gson gson = new Gson();
        CurrencyListResponse response = gson.fromJson(json, CurrencyListResponse.class);
        if("true".equals(response.getSuccess())) {
            this.currencyMap.putAll(response.getCurrencies());
            LOG.info("Loaded {} currencies into collection", currencyMap.size());
        } else {
            LOG.error("Currency API returned no Data or failed");
        }


    }

    public HashMap<String, String> getCurrencyMap() {
        return currencyMap;
    }

    public int getAmountOfSupportedCurrencies() {
        return currencyMap.size();
    }
}
