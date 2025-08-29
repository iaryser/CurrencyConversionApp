package ch.timor.projects.currencyconverter.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class APICallTest {
    public static void main(String[] args) throws Exception {
        //I'll just perfom a simple API call in order to see which endpoints I want to use for this project
        String APIKEY = "?access_key=b683cbc749d902ae2af12800a94788d7";
        String BASE_URL = "https://api.currencylayer.com/";
        String ENDPOINT = "convert";

        HttpClient client = HttpClient.newHttpClient(); //Initializing client to actually send req

        HttpRequest conversionRequest = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + ENDPOINT + APIKEY + "&from=USD&to=GBP&amount=10"))
                .GET()
                .build();

        HttpResponse<String> conversionResponse = client.send(conversionRequest, HttpResponse.BodyHandlers.ofString());
        String responseBody = conversionResponse.body();


        //Pretty printing the Response to see how we structure our ConversionResponseObject
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(responseBody);
        String prettyJson = gson.toJson(jsonElement);
        System.out.println(prettyJson);

        ConversionResponse responseObject = gson.fromJson(responseBody, ConversionResponse.class);

        System.out.println("GET-Request executed with success: " + responseObject.getSuccess());
        if("true".equals(responseObject.getSuccess())) {
            System.out.println("Query details of request: " + responseObject.getQuery() );
            System.out.println("Converting " + responseObject.getQuery().getAmount()  + " "
                    + responseObject.getQuery().getFrom() + " to " + responseObject.getQuery().getTo()
                    + " results in a total of: " + Math.round(Double.valueOf(responseObject.getResult()))
                    + " " + responseObject.getQuery().getTo());
        }



    }
}
