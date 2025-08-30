package ch.timor.projects.currencyconverter.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Scanner;

public class BackendDemo {
    public final static Logger LOG = LoggerFactory.getLogger(BackendDemo.class);
    public static void main(String[] args) throws InterruptedException {
        SupportedCurrenciesStorage currenciesStorage = new SupportedCurrenciesStorage();
        CurrencyConversionClient conversionClient = new CurrencyConversionClient();
        HashMap<String, String> currencyMap = currenciesStorage.getCurrencyMap();
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println("Welcome to the [BACKEND DEMO] of rysers CurrencyConverter!");
        System.out.println("=============================================================");
        System.out.println("=============================================================");
        System.out.println("At the moment we have a total of [1" + currenciesStorage.getAmountOfSupportedCurrencies()
            + "] Currencies that we support!");
        System.out.println("\n");
        System.out.println("Your options are as followed:");
        System.out.println("Press [1] to get a list of all the currencies available");
        System.out.println("Press [2] to start a conversion");
        System.out.println("Press [ENTER] to quit");
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            //!---USER WANTS A LIST OF SUPPORTED CURRENCIES---!
            if("1".equals(input)) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Printing list of all the currencies.....");
                Thread.sleep(1000);
                System.out.println("......");
                Thread.sleep(1000);
                System.out.println("......");
                Thread.sleep(1000);
                for(String key: currencyMap.keySet()) {
                    System.out.println(key + ": " + currencyMap.get(key));
                }
                System.out.println("=============================================================");
                System.out.println("=============================================================");
                System.out.println("Press [1] to get a list of all the currencies available");
                System.out.println("Press [2] to start a conversion");
                System.out.println("Press [ENTER] to quit");

            //!---USER IS GOING TO MAKE A CONVERSION CALL---!
            } else if("2".equals(input)) {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                //!---Collecting VALID inputs---!
                String from;
                while(true) {
                    System.out.println("Please enter which currency you would like to convert: ");
                    from = scanner.nextLine().trim().toUpperCase();
                    if(currencyMap.get(from) == null) {
                        LOG.error("Invalid currency!");
                    } else {
                        break;
                    }
                }

                String amount;
                int intAmount;
                while(true) {
                    System.out.println("Please Enter the amount of the currency you would like to convert: ");
                     amount = scanner.nextLine().trim();
                    if(Integer.valueOf(amount) < 1) {
                        System.out.println("Amount can't be less than 1!");
                    } else {
                        intAmount = Integer.valueOf(amount);
                        break;
                    }
                }

                String to;
                while(true) {
                    System.out.println("Please enter to what currency you would like to convert " + from + "to");
                    to = scanner.nextLine().trim().toUpperCase();
                    if(currencyMap.get(from) == null) {
                        LOG.error("Invalid currency!");
                    } else {
                        break;
                    }
                }
                //!---FINISHED COLLECTING ALL VALID INPUTS---!
                String res = conversionClient.makeConversionRequest(from, to, intAmount);
                ConversionResponse response = conversionClient.parseResponse(res);
                System.out.println("Processing inputs....");
                Thread.sleep(1000);
                System.out.println("Checking up to date conversion rates...");
                Thread.sleep(1000);
                System.out.println("Texting your mom....");
                Thread.sleep(1000);
                System.out.println("=============================================================");
                System.out.println("=============================================================");
                if("true".equals(response.getSuccess())) {
                    System.out.println("Converting " + response.getQuery().getAmount()  + " "
                            + response.getQuery().getFrom() + " to " + response.getQuery().getTo()
                            + " results in a total of: " + Math.round(Double.valueOf(response.getResult()))
                            + " " + response.getQuery().getTo());
                }
                Thread.sleep(3000);
                System.out.println("Press [1] to get a list of all the currencies available");
                System.out.println("Press [2] to start a conversion");
                System.out.println("Press [ENTER] to quit");
            } else if("".equals(input)) {
                break;
            } else {
                LOG.error("Invalid input!");
            }
        }
    }
}
