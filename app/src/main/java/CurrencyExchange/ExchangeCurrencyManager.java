package CurrencyExchange;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

/**
 * The ExchangeCurrencyeManager class is responsible for managing currency exchange rates,
 * user exchange history, and administrative rate history.
 * This class provides methods to:
 * - Track the history of exchange rates for auditing and administrative purposes.
 * - Track user-specific currency exchange history.
 * - Manage privacy settings related to user history.
 * - Perform currency exchange by converting amounts between different currencies.
 */
public class ExchangeCurrencyManager {

    private final App app;

    public ExchangeCurrencyManager(App app) {
        this.app = app;
    }


    public Boolean currencyExchangeManager(Drawing drawing, String allCurrencies, Scanner scanner) throws InterruptedException {
        Validating validating = new Validating(app);
        drawing.loadingAction("Loading Currency Exchange Application");
        drawing.printCurrencyExchange();
        drawing.welcomeCurrencyExchange();
        // Input source currency
        System.out.printf("Select the source currency \uD83D\uDCE4 %s: ", allCurrencies);
        String sourceCurrency = scanner.next().toUpperCase();
        if (!validating.isValidCode(sourceCurrency)) {
            System.out.println(Drawing.ANSI_RED + "Invalid currency code!❌" + Drawing.ANSI_RESET);
            return false;
        }
        // Input target currency
        System.out.printf("Select the target currency \uD83C\uDFAF %s : ", allCurrencies);
        String targetCurrency = scanner.next().toUpperCase();
        if (!validating.isValidCode(targetCurrency))
        {
            System.out.println(Drawing.ANSI_RED + "Invalid currency code!❌" + Drawing.ANSI_RESET);
            return false;
        }
        // Get the rate after exchange
        // Input amount
        System.out.print("Enter the amount you want to convert \uD83D\uDCB0: ");
        Double amount = scanner.nextDouble();
        if (amount instanceof Double) {
            Calculating calculating = new Calculating();
            calculating.exchangeRate(app, sourceCurrency, targetCurrency, amount, true);
            System.out.println();
            return true;
        }
        return false;
    }

    public Boolean addNewCurrencyManager(Scanner scanner) {
        Validating validating = new Validating(app);
        Drawing drawing = new Drawing();
        drawing.printAddCurrencyLabel();
        System.out.println("Enter currency code \uD83D\uDCB1: ");
        String code = scanner.next().toUpperCase();
        if (code instanceof String && (!validating.isValidCode(code)) && code.length() == 3) {
            CurrencyFactory currencyFactory = new CurrencyFactory(app);
            currencyFactory.addNewCurrency(code);
            System.out.println(Drawing.ANSI_GREEN + "Successful adding new currency! \u2705" + Drawing.ANSI_RESET);
            System.out.println("------------------------------------------------------------------------------------------");
        }
        return false;
    }

    public Boolean updateConversionRateManager(Scanner scanner, Drawing drawing, String allCurrencies) throws InterruptedException, IOException {
        Validating validating = new Validating(app);
        drawing.loadingAction("Loading Update Currency Section");
        drawing.printUpdateConversionRate();
        CurrencyFactory currencyFactory = new CurrencyFactory(app);
        System.out.printf("Enter currency code that you want to change the conversionRate: %s%n", allCurrencies);
        System.out.print("From currency code \uD83D\uDCE4: ");
        String fromCurrencyCode = scanner.next().toUpperCase();
        if (!validating.isValidCode(fromCurrencyCode)) {
            return false;
        }
        System.out.print("To currency code \uD83C\uDFAF: ");
        String toCurrencyCode = scanner.next().toUpperCase();
        if (!validating.isValidCode(toCurrencyCode)) {
            return false;
        }
        System.out.print("The new conversionRate is ✨: ");
        Double newRate = scanner.nextDouble();
        if (newRate instanceof Double) {
            currencyFactory.updateRateCurrency(fromCurrencyCode, toCurrencyCode, newRate);
            return true;
        }
        return false;
    }

    public void popularCurrencyManagerForAdmin(Scanner scanner, Drawing drawing, String allCurrencies, PopularCurrenciesManager popularCurrenciesManager) throws InterruptedException, IOException {
        while (true)
        {
            drawing.printPopularCurrencyAction();
            System.out.println("What would you like to do?");
            System.out.println("--------------------------------------------");
            System.out.println("1. \uD83D\uDD04 Update popular currencies");
            System.out.println("2. \uD83C\uDF0D Display popular currencies");
            System.out.println("3. 🏃 Exit");
            System.out.println("--------------------------------------------");
            System.out.println("Please enter your choice: (1 to 3)");
            try
            {
                int choice1 = scanner.nextInt();

                if (choice1 == 1)
                {
                    drawing.printUpdatePopularCurrency();
                    popularCurrenciesManager.updatePopularCurrencies();
                }
                else if (choice1 == 2)
                {
                    drawing.printPopularCurrencies();
                    drawing.printPopularCurrenciesDisplayLabel();
                    popularCurrenciesManager.displayPopularCurrencies();
                }
                else if (choice1 == 3)
                {
                    break;
                }
                else
                {
                    System.out.println(Drawing.ANSI_RED + "Invalid choice!❌" + Drawing.ANSI_RESET);
                }
            }
            catch (Exception e)
            {
                System.out.println(Drawing.ANSI_RED + "Invalid choice!❌" + Drawing.ANSI_RESET);
            }
        }
    }


    public Boolean conversionRateHistoryManager(Scanner scanner, Drawing drawing, String allCurrencies) throws InterruptedException, IOException, DateTimeParseException {
        drawing.printConversionHistory();
        // Conversion history will be handed later.
        drawing.printConversionHistoryLabel();
        System.out.println("Welcome to the history of conversion rates!");
        System.out.println();
        System.out.printf("Select the source currency \uD83D\uDCE4 %s: ", allCurrencies);
        String sourceCurrency = scanner.next().toUpperCase();
        if (!HistoryConversionRate.isExist(sourceCurrency, app.getDatabaseService().getAllCurrencies())){
            return false;
        }
        System.out.printf("Select the target currency \uD83C\uDFAF %s: ", allCurrencies);
        String targetCurrency = scanner.next().toUpperCase();
        if (!HistoryConversionRate.isExist(targetCurrency,app.getDatabaseService().getAllCurrencies())){
            return false;
        }
        System.out.print("Select the start date [YYYY-MM-DD] \uD83D\uDCC5: ");
        String inStartDate = scanner.next();
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        FORMATTER = FORMATTER.withLocale(Locale.ENGLISH);
        LocalDate startDate = LocalDate.parse(inStartDate, FORMATTER);

        System.out.print("Select the end date [YYYY-MM-DD] \uD83D\uDCC5: ");
        String inEndDate = scanner.next();
        FORMATTER = FORMATTER.withLocale(Locale.ENGLISH);
        LocalDate endDate = LocalDate.parse(inEndDate, FORMATTER);

        // Check if start date is before end date
        if(!startDate.isBefore(endDate)){
            System.out.println(Drawing.ANSI_RED + "Date must be before end date." + "\n" + Drawing.ANSI_RESET);
            return false;
        }

        if (endDate.isAfter(LocalDate.now())) {
            System.out.println(Drawing.ANSI_RED + "End Date must be before or equal to today date, " + LocalDate.now().toString() + "\n" + Drawing.ANSI_RESET);
            return false;
        }
        HistoryConversionRate historyConversion = new HistoryConversionRate(startDate, endDate, sourceCurrency, targetCurrency);
        historyConversion.printHistory(app);
        return true;
    }
}