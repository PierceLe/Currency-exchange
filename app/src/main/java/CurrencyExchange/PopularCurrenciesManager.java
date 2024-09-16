package CurrencyExchange;

import java.util.*;
import java.io.*;

public class PopularCurrenciesManager {
    private HashMap<String, HashMap<String, Double>> exchangeRates;
    public static String[] popularCurrencies = null;
    private final String POPULAR_CURRENCIES_FILE;
    private App app;

    public PopularCurrenciesManager(App app, String popularCurrenciesFile) {
        exchangeRates  = new HashMap<>();
        POPULAR_CURRENCIES_FILE = popularCurrenciesFile;
        this.app = app;
    }


    public void loadExchangeRates() {
        exchangeRates = app.getDatabaseService().getRatesFromData();
        System.out.println(Drawing.ANSI_GREEN + "Successfully loaded exchange rates." + Drawing.ANSI_RESET);
    }

    public void loadPopularCurrencies(String type) {
        File f = new File(POPULAR_CURRENCIES_FILE);

        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line = br.readLine();
                if (line != null && !line.trim().isEmpty()) {
                    popularCurrencies = line.split(",");
                    System.out.println(Drawing.ANSI_GREEN + "Successfully loaded popular currencies." + Drawing.ANSI_RESET);
                } else {
                    if (type.equals("admin")) {
                        System.out.println(Drawing.ANSI_RED + "Popular currencies file is empty. Please specify them." + Drawing.ANSI_RESET);
                        specifyPopularCurrencies();
                    }
                    else {
                        System.out.println(Drawing.ANSI_RED + "The admin has not specified any popular currency." + Drawing.ANSI_RESET);
                    }

                }
            } catch (IOException e) {
                System.out.println(Drawing.ANSI_RED + "Error while loading popular currencies." + Drawing.ANSI_RESET);
            }

        } else {
            System.out.println(Drawing.ANSI_RED + "Popular currencies file does not exist. Please specify them." + Drawing.ANSI_RESET);
            specifyPopularCurrencies();
        }
    }

    public void specifyPopularCurrencies() {
        if (popularCurrencies == null) {
            popularCurrencies = new String[4];
            System.out.println("Please enter 4 popular currencies code: ");

            for (int i = 0; i < 4; i++) {
                String code;
                while (true) {
                    System.out.print("Enter currency code #" + (i + 1) + ": ");
                    code = app.getScanner().next().toUpperCase();
                    Validating validating = new Validating(app);
                    if (Validating.isDuplicate(code, popularCurrencies)) {
                        System.out.println(Drawing.ANSI_RED + "You already entered this currency. Try again." + Drawing.ANSI_RESET);
                    }
                    else if (exchangeRates.containsKey(code)) {
                        popularCurrencies[i] = code;
                        break;
                    } else {
                        System.out.println(Drawing.ANSI_RED + "Please enter a valid code." + Drawing.ANSI_RESET);
                    }
                }
            }
            app.getDatabaseService().writePopularCurrenciesToDatabase(popularCurrencies);
            System.out.println(Drawing.ANSI_GREEN + "Popular currencies have been specified" + Drawing.ANSI_RESET);
        } else {
            System.out.println(Drawing.ANSI_RED + "Popular currencies have already been specified before." + Drawing.ANSI_RESET);
        }
    }


    public void updatePopularCurrencies() {
        if (popularCurrencies == null || popularCurrencies.length == 0) {
            System.out.println(Drawing.ANSI_RED + "Popular currencies have not been specified yet. Please specify them first." + Drawing.ANSI_RESET);
            return;
        }
        System.out.println("Current popular currencies: ");
        for (int i = 0; i < popularCurrencies.length; i++) {
            System.out.println((i + 1) + ". " + popularCurrencies[i]);
        }

        System.out.println("Enter the indexes of the currencies you want to update:");
        String input = app.getScanner().next();
        String[] choices = input.split(",");

        for (String choiceStr : choices) {
            int choice;
            try {
                choice = Integer.parseInt(choiceStr.trim());
            } catch (NumberFormatException e) {
                System.out.println(Drawing.ANSI_RED + "You entered an invalid index. Try again. " + Drawing.ANSI_RESET);
                continue;
            }

            if (choice < 1 || choice > 4) {
                System.out.println(Drawing.ANSI_RED + "Invalid choice: " + choice + ". Please select a valid number between 1 and 4." + Drawing.ANSI_RESET);
                continue;
            }

            String newCode;
            while (true) {
                System.out.println("Enter the new currency code to replace " + popularCurrencies[choice - 1] + ":");
                newCode = app.getScanner().next().toUpperCase();
                Validating validating = new Validating(app);
                if (Validating.isDuplicate(newCode, popularCurrencies)) {
                    System.out.println(Drawing.ANSI_RED + "This currency is already one of the popular currencies. Try again." + Drawing.ANSI_RESET);
                } else if (!exchangeRates.containsKey(newCode)) {
                    System.out.println(Drawing.ANSI_RED + "Invalid currency code. Please enter a code that is available." + Drawing.ANSI_RESET);
                } else {
                    break;
                }
            }
            popularCurrencies[choice - 1] = newCode;

        }
        System.out.println(Drawing.ANSI_GREEN + "Successfully updated popular currencies." + Drawing.ANSI_RESET);
        app.getDatabaseService().writePopularCurrenciesToDatabase(popularCurrencies);
    }

    public void displayPopularCurrencies() {
        if (popularCurrencies == null || popularCurrencies.length == 0) {
            System.out.println(Drawing.ANSI_RED + "Popular currencies have not been specified yet. Please specify them first." + Drawing.ANSI_RESET);
            return;
        }
        // Load the history of exchange rate updates
        List<RateUpdateSnapshot> rateUpdates = app.getDatabaseService().loadCurrenciesHistory();
        Drawing drawing = new Drawing();
        drawing.drawPopularCurrenciesTable(popularCurrencies, exchangeRates, rateUpdates);
    }

}