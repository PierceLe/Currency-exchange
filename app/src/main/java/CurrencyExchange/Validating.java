package CurrencyExchange;

import java.util.ArrayList;

public class Validating {

    private App app;

    public Validating(App app) {
        this.app = app;
    }

    public void login() throws InterruptedException {
        Drawing drawing = new Drawing();
        while (true) {
            System.out.print("Please enter your username: ");
            String username = app.getScanner().next();
            System.out.print("Please enter your password: ");
            String password = app.getScanner().next();
            if (AdminSingleton.getInstance().verifyUserName(username)
                    && AdminSingleton.getInstance().verifyPasswordHashed(password)
            ) {
                drawing.loadingAction("Logging in");
                System.out.println(Drawing.ANSI_GREEN + "Successfully logged in!" + Drawing.ANSI_RESET);
                break;
            }
            else
            {
                System.out.println(Drawing.ANSI_RED + "Invalid username or password" + Drawing.ANSI_RESET);
            }
        }
    }

    public Boolean isValidCode(String code) {
        ArrayList<String> allCurrenciesCode = app.getDatabaseService().getAllCurrencies();
        return (allCurrenciesCode.contains(code.toUpperCase()));
    }

    public static boolean isDuplicate(String code, String[] popularCurrencies) {
        for (String inCode : popularCurrencies) {
            if (code.equals(inCode)) {
                return true;
            }
        }
        return false;
    }
}
