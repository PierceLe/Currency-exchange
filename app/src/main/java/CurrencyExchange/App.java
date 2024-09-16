package CurrencyExchange;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class App {
    private final String currencyFile;
    private final String conversionRateFile;
    private final String userHistoryFile;
    private final String popularCurrenciesFile;
    private final DatabaseService databaseService;
    private final ExchangeCurrencyManager controller;
    private Scanner scanner = new Scanner(System.in);
    private final Validating validate;
    private final PopularCurrenciesManager popularCurrenciesManager;

    public App(String currencyFile,String conversionRateFile,String userHistoryFile, String popularCurrenciesFile)
    {
        controller = new ExchangeCurrencyManager(this);
        this.currencyFile = currencyFile;
        this.conversionRateFile = conversionRateFile;
        this.userHistoryFile = userHistoryFile;
        this.popularCurrenciesFile = popularCurrenciesFile;
        this.validate = new Validating(this);
        this.databaseService = new DatabaseService(currencyFile,conversionRateFile,userHistoryFile,popularCurrenciesFile);
        this.popularCurrenciesManager = new PopularCurrenciesManager(this, popularCurrenciesFile);

    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void updateScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void adminMenu() {
        Drawing drawing = new Drawing();
        drawing.adminLabel();
        while (true) {
            drawing.displayingAdminMenu();
            try {
                int choice = scanner.nextInt();
                String allCurrencies = Arrays.toString(databaseService.getAllCurrencies().toArray());
                if (choice == 1) {
                    if (!controller.currencyExchangeManager(drawing, allCurrencies, scanner)) {
                        continue;
                    }
                }
                else if (choice == 2) {

                    if (!controller.addNewCurrencyManager(scanner)) {
                        continue;
                    }
                }
                else if(choice == 3)
                {
                    if (!controller.updateConversionRateManager(scanner, drawing, allCurrencies)) {
                        continue;
                    }
                }
                else if (choice == 4)
                {
                    drawing.loadingAction("Loading Popular Currency Section");
                    String type = "admin";
                    popularCurrenciesManager.loadExchangeRates();
                    popularCurrenciesManager.loadPopularCurrencies(type);
                    controller.popularCurrencyManagerForAdmin(scanner, drawing, allCurrencies, popularCurrenciesManager);
                } else if (choice == 5) {
                    if (!controller.conversionRateHistoryManager(scanner, drawing, allCurrencies)) {
                        continue;
                    }
                }
                else if (choice == 6)
                {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect format of date, must be in YYYY-MM-DD format.❌");
            }
            catch (Exception e) {
                System.out.println("Invalid choice. Try again.❌");
            }
        }
    }

    public void userMenu() throws InterruptedException {
        Drawing drawing = new Drawing();
        drawing.loadingAction("Loading User Menu");
        drawing.currencyExchangeLabel();
        System.out.println();
        while (true) {
            drawing.displayingUserMenu();
            try {
                String allCurrencies = Arrays.toString(databaseService.getAllCurrencies().toArray());
                // Currency converter
                int choice = scanner.nextInt();
                if (choice == 1) {
                    if (!controller.currencyExchangeManager(drawing, allCurrencies, scanner)) {
                        continue;
                    }
                }
                else if (choice == 2)
                {
                    drawing.loadingAction("Loading Popular Currency Section");
                    String type = "normal";
                    popularCurrenciesManager.loadExchangeRates();
                    popularCurrenciesManager.loadPopularCurrencies(type);
                    if (PopularCurrenciesManager.popularCurrencies == null) {
                        continue;
                    }
                    drawing.printPopularCurrencies();
                    drawing.printPopularCurrenciesDisplayLabel();
                    popularCurrenciesManager.displayPopularCurrencies();
                }
                else if(choice == 3)
                {
                    drawing.printUpdateConversionRate();
                    if (!controller.conversionRateHistoryManager(scanner, drawing, allCurrencies)) {
                        continue;
                    }
                }
                else if (choice == 4)
                {
                    break;
                }
            }
            catch (DateTimeParseException e) {
                System.out.println("Incorrect format of date, must in YYYY-MM-DD format.❌");
            }
            catch (Exception e) {
                System.out.println("Invalid choice. Please try again.❌");
            }
            System.out.println(Drawing.ANSI_RED + "-----------------------------------------------------------------------------------" + Drawing.ANSI_RESET);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        App app = new App("../database/currencies.csv", "../database/conversionRate.txt","../database/currenciesHistory.txt", "../database/popularCurrencies.txt");
        Drawing drawing = new Drawing();
        drawing.printingHomePage();
        drawing.printOverwrittenMessages();
        label:
        while (true) {
            drawing.currencyLabel();
            System.out.println();
            System.out.println("Please choose the type of user:");
            System.out.println("---------------------------------------------");
            System.out.println("1. \uD83D\uDC68\u200D\uD83C\uDF93 User");
            System.out.println("2. \uD83D\uDC77\u200D♂️ Admin");
            System.out.println("3. \uD83D\uDEAA Exit");
            System.out.println("---------------------------------------------");
            System.out.print("Enter your choice here (1 to 3): ");
            String input = app.getScanner().next();
            switch (input) {
                case "1":
                    app.userMenu();
                    break;
                case "2":
                    app.validate.login();
                    app.adminMenu();
                    break;
                case "3":
                    drawing.endProgram();
                    break label;
                default:
                    System.out.println(Drawing.ANSI_RED + "Invalid choice. Please try again.❌" + Drawing.ANSI_RESET);
                    break;
            }
        }
    }
}

