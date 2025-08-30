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

public class CurrencyConversionClient {
    private final static Logger LOG = LoggerFactory.getLogger(CurrencyConversionClient.class);
    private HttpClient client = HttpClient.newHttpClient();
    Gson gson = new Gson();

    public CurrencyConversionClient() {
    }

    public String makeConversionRequest(String fromCurrency, String toCurrency, int amount) {
        if(amount > 0) {
            HttpRequest req = null;

            try {
                req = HttpRequest.newBuilder()
                        .uri(new URI(URL.BASE_URL.getValue() + URL.CONVERSION.getValue() + URL.API_KEY.getValue()
                                + "&from=" + fromCurrency + "&to=" + toCurrency + "&amount=" + String.valueOf(amount)))
                        .GET()
                        .build();
                HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
                return response.body();
            } catch (URISyntaxException e) {
                LOG.error("Invalid Syntax: {}", e.getInput());
            } catch (IOException e) {
                LOG.error("I/O Error: {}", e.getMessage());
            } catch (InterruptedException e) {
                LOG.error("Request has been interrupted: {}", e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("amount has to be greater than 0!");
        }
        return null;
    }

    public ConversionResponse parseResponse(String Json) {
        ConversionResponse responseObject = gson.fromJson(Json, ConversionResponse.class);
        return responseObject;
    }

    public boolean validateResponseObject(String from, String to, int amount, HashMap<String, String> currencyMap) {
        if(amount < 1) {
            throw new IllegalArgumentException("amount cant be less than 1!");
        }
        if(currencyMap.get(from) == null) {
            throw new IllegalArgumentException("Currency " + from + " doesn't exist!");
        }

        if(currencyMap.get(to) == null) {
            throw new IllegalArgumentException("Currency " + to + " doesn't exist!");
        }
        return true;
    }
}

