package CurrencyExchange;
import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The CurrencyFactory class is responsible for creating and managing currencies in the currency exchange system.
 * This class follows the **Factory Method Pattern**, where different types of `Currency` objects
 * are created based on the currency code provided. The factory provides a standard interface to create currencies
 * without specifying the exact class that will be instantiated.
 * It allows for:
 * - Creating a currency based on the provided currency code.
 * - Adding new currencies to the system.
 * Note: All functions in this class should only be used by admin users to ensure proper access control.
 */
public class CurrencyFactory {
    private final HashMap<String, HashMap<String, Double>> currenciesRate;
    private ArrayList<String> allCurrencies;
    private App app;



    public CurrencyFactory(App app) {
        this.app = app;
        this.currenciesRate = app.getDatabaseService().getRatesFromData();
        this.allCurrencies = app.getDatabaseService().getAllCurrencies();

    }


    public Currency createCurrency(String code) {
        if (code instanceof String) {
            // you need to remind that code
            Map<String, Double> conversionRate = currenciesRate.get(code);
            return new Currency(code, conversionRate);
        }
        return null;

    }


    public void addNewCurrency(String code) {
        if (code instanceof String) {
            ArrayList<String> conversionRateFromNewCurrency = new ArrayList<>();
            ArrayList<String> conversionRateToNewCurrency = new ArrayList<>();
            conversionRateFromNewCurrency.add(String.format("\"%s\"", code));
            System.out.println("Please enter the exchange rates for the new currency (" + code + ") to other currencies.");
            System.out.println("You can skip a currency by pressing Enter, which means no conversion.");

            for (String currency : allCurrencies) {
                if (!currency.equals(code)) {
                    System.out.print("Enter exchange rate for " + code + " to " + currency + ": ");
                    String input = app.getScanner().next();

                    if (input.isEmpty()) {
                        conversionRateFromNewCurrency.add("");
                        conversionRateToNewCurrency.add("");
                    } else {
                        try {
                            double rate = Double.parseDouble(input);
                            conversionRateFromNewCurrency.add(String.format("%.2f", rate));
                            conversionRateToNewCurrency.add(String.format("%.2f", 1/rate));
                            updateCurrenciesHistory(code, currency, rate);
                        } catch (NumberFormatException e) {
                            System.out.println(Drawing.ANSI_RED + "Invalid input. Skipping " + currency);
                            conversionRateFromNewCurrency.add("");
                            conversionRateToNewCurrency.add("");
                        }
                    }
                }
            }
            conversionRateToNewCurrency.add("-");
            app.getDatabaseService().addRowToCSV(conversionRateFromNewCurrency);
            app.getDatabaseService().addColumnToCSV(String.format("\"%s\"", code), conversionRateToNewCurrency);
            this.allCurrencies  = app.getDatabaseService().getAllCurrencies();
        }
    }



    public void updateRateCurrency(String fromCurrencyCode, String toCurrencyCode, Double newRate) throws IOException {

        int convertFrom = allCurrencies.indexOf(fromCurrencyCode) + 1;
        int convertTo = allCurrencies.indexOf(toCurrencyCode) + 1;

        if ((convertFrom == convertTo) || (convertFrom == 0) || (convertTo == 0)) {
            System.out.println(Drawing.ANSI_RED + "Invalid input. Skipping " + fromCurrencyCode + " to " + toCurrencyCode + Drawing.ANSI_RESET);
            return;
        }
        if ((fromCurrencyCode instanceof String) && (toCurrencyCode instanceof String)) {
            app.getDatabaseService().updateRate(convertFrom, convertTo, newRate);
            updateCurrenciesHistory(fromCurrencyCode, toCurrencyCode, newRate);
            System.out.println(Drawing.ANSI_GREEN + "Successful adding new conversion rate!" + Drawing.ANSI_RESET);
            System.out.println("------------------------------------------------------------------------------------------");
        }
        else {
            System.out.println(Drawing.ANSI_RED + "Invalid input." + Drawing.ANSI_RESET);
        }


    }

    private void updateCurrenciesHistory(String fromCurrencyCode, String toCurrencyCode, Double rate) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        String messageToCurrencyHistory = String.format("At %s: updated a currency rate from %s to %s with new rate %.2f",formattedDateTime, fromCurrencyCode, toCurrencyCode, rate);
        app.getDatabaseService().updateCurrenciesHistoryPath(messageToCurrencyHistory);
        String messageToCurrencyHistory2 = String.format("At %s: updated a currency rate from %s to %s with new rate %.2f",formattedDateTime, toCurrencyCode, fromCurrencyCode, 1/rate);
        app.getDatabaseService().updateCurrenciesHistoryPath(messageToCurrencyHistory2);
    }
}