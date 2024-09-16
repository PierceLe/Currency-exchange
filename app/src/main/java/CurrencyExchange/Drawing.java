package CurrencyExchange;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Drawing {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_ORANGE = "\u001B[38;5;214m"; // Custom for orange
    public static final String ANSI_INDIGO = "\u001B[38;5;69m"; // Custom for indigo
    public static final String ANSI_VIOLET = "\u001B[35m";
    public static final String[] RAINBOW_COLORS = {
            ANSI_RED, ANSI_ORANGE, ANSI_YELLOW, ANSI_GREEN, ANSI_BLUE, ANSI_INDIGO, ANSI_VIOLET
    };


    public void printingHomePage() throws InterruptedException {
        String[] lines = {
                "                      ....                       ..                  ",
                "                :*************..                 **=                 ",
                "             =****.        .:****..*******************.              ",
                "          .****..             ..***..*******************..           ",
                "         .***....     .....     ..***.********************..         ",
                "        .**..         #**..       .***.*******************.          ",
                "       .**...     .**********..    .+**.****************.            ",
                "      .**:..     +**********-..      **-#************#.              ",
                "      .**..      *****.......        .**.........**+ .               ",
                "      .**        **********=..       .**.       ....                 ",
                "      .**         .=*********-.      .**..+##*-. ..                  ",
                "      .**.       .#... ..*****.      :**************..               ",
                "      .***.      +***********..     .**....     ...****.             ",
                "       .**..    .-**********..     .***             .:***.           ",
                "       ..**-.        .#**..       .***.                ***.          ",
                "         .***.           .       .**+..   ...::..      ..**.         ",
                "          ..***.              .+***.....***********.    ..**         ",
                "             .*****........:*****.    *************#.     **=..      ",
                "               ..=************.... *##*****####..*..      .**.       ",
                "                  .........-**.    ************.....      .**.       ",
                "               ..**....    .**.   .************..         .**.       ",
                "              .****....    .**.   ....*****=....**.      .***..      ",
                "            .**************.***.      .*************.    .**...      ",
                "         .-*****************.**+       ..*********-...  .**.         ",
                "        =********************.***..      .........     .**..         ",
                "          :*******************.+**=..               ..***...         ",
                "           ..*******************.****...         ..+***...           ",
                "              .****.................******=:.-*******..              ",
                "               ..**                  ...+*******:..                  ",
                "                 ..                                                  "
        };

        String[] colors = {ANSI_RED, ANSI_ORANGE, ANSI_YELLOW, ANSI_GREEN, ANSI_BLUE, ANSI_PURPLE, ANSI_CYAN};

        for (int i = 0; i < lines.length; i++) {
            System.out.print(colors[i % colors.length]);
            System.out.println(lines[i]);
            System.out.print(ANSI_RESET);
            Thread.sleep(20);
        }
    }

    public void printCurrencyExchange() {
        String[] lines = {
                "...................                                               ...................",
                "..................                      .....                      ..................",
                ".................                       =###=                       .................",
                "................                      ::+###-                       ................",
                "................      +*****************####=:::::::::::::::::       ................",
                "................      +*****+*+*+*+**+***###=::::..:....::::::        ...............",
                "...........#########* +***          :  +####-:::.         .:::  #########*...........",
                ".......*############* +*+          **.  ####-  :::          ::  *#############:......",
                "#### ##########%%%%%# +**  * .*   ****  -###=:::::   .. :   ::  #%%%%%########## ####",
                "###% ##########%%%%%# +**  *  *   ***   -###-  :::   :: .   ::  #%%%%%########## ####",
                "###% #################***         -**   -###=  :::          ::*################# ####",
                "###% ##################*+          +***--###-.:::           :-################## ####",
                "#### #########+%%%%%# +****          ***####=::           ::::  #@%%%%+######### ####",
                "...............#%%%%  +*****************####=:::::::::::::::::   %%%%#...............",
                "....................                  -:+###-                    :...................",
                "...................                     =###-                     ..................."
        };
        coloring(lines);
    }

    private void coloring(String[] lines) {
        String[] colors = {ANSI_RED, ANSI_ORANGE, ANSI_YELLOW, ANSI_GREEN, ANSI_BLUE, ANSI_INDIGO, ANSI_VIOLET};
        int colorIndex = 0;
        for (String line : lines) {
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (Character.isWhitespace(ch)) {
                    System.out.print(ch);
                } else {
                    System.out.print(colors[colorIndex % colors.length] + ch + ANSI_RESET);
                    colorIndex++;
                }
            }
            System.out.println();
        }
    }

    public void printPopularCurrencies() {
        String[] lines = {
                "                                                                                         ",
                "                                                                                         ",
                "                                                                                         ",
                "              @@@@@                  @@@        @@        @@            @@               ",
                "            @@@@@@@@@           @@@@@@@@@      @@@@     @@@@        @@@@@@@@@            ",
                "           @@@@ @@@@@@         @@@@@  @@@       @@@@   @@@@        @@@@@  @@@@           ",
                "           @@@@ @@            @@@@               @@@@ @@@@         @@@@    @@@           ",
                "            @@@@@@@@        @@@@@@@@@@@@        @@@@@@@@@@@@      @@@@@@@@               ",
                "              @@@@@@@@      @@@@@@@@@@@@        @@@@@@@@@@@@      @@@@@@@@               ",
                "            @@  @@ @@@      @@@@@@@@@@@@        @@@@@@@@@@@@        @@@                  ",
                "           @@@  @@ @@@         @@@@             @@@@@@@@@@@@       @@@@                  ",
                "           @@@@@@@@@@@         @@@@@@@@@@           @@@           @@@@@@@@@@@@           ",
                "             @@@@@@@              @@@@@@@           @@@           @@@@@@@@@@@@           ",
                "                @@                                                                       ",
                "                                                                                         ",
                "                                                                                         ",
                "                                                                                         "
        };
        coloring(lines);
    }

    public void printConversionHistory() {
        String[] lines = {
                "                              @@@@@@@.                                ",
                "                    *@@@.          .@@@:                              ",
                "                 +@@                 .@@:                             ",
                "              .@@:         .:-:          *@@.                          ",
                "             @@.       .@@@@. -@@@         .@@                         ",
                "           @@.      :@@              @@.       -@+.                    ",
                "         .@@.     =@%                 @@.       =@.                    ",
                "        -@.    .@@           @@        @@        .@.                   ",
                "      =@.     @@            @@@=.       .@-      -@                    ",
                "     .@      %@        @@@      @@       @.      @@.                   ",
                "    +@      .@.       @@        .@.      @@      .@                    ",
                "    @*      @%       @%.   .@@@@. @=      @@      @@                   ",
                "   =@      %@          @@       .@@        @.     @                    ",
                "   =@      @@           @@.       @@       @@     @@                   ",
                "    @=      @*        @@    @   .@-       @@.    @@                    ",
                "     @=      @@          @@        @@     @+.     @@                   ",
                "      %@       @@            @@@  @@    @@.       @.                   ",
                "       @@       .@#            @   @@  @@.     .@-                     ",
                "        %@       .@@:           @@     .@      .@@                     ",
                "         .@=        @@        @@       @@       @@                     ",
                "           @@        @@.   .@@      .@@        @@                      ",
                "              @@             @@@@  @@           @@                     ",
                "                .@@@       @@@@.             @@.                       ",
                "                  .@@.    @@ .@@                 @@                    ",
                "                       .@@@@    .@@               @@                   ",
                "                          @@@@@@@               .@@                    ",
                "                           .@@@@@@                 @@                  ",
                "                                                        @+             ",
                "                                                         @@:           ",
                "                                                           .@:         ",
                "                                                                       ",
        };
        coloring(lines);
    }

    public void loadingAction(String message) throws InterruptedException {
        System.out.println(ANSI_CYAN + message + ANSI_RESET);

        String[] animationFrames = {".   ", "..  ", "... ", "...."};
        for (int i = 0; i < 5; i++) {
            for (String frame : animationFrames) {
                System.out.print("\rLoading" + frame);
                Thread.sleep(50);

            }
        }
        System.out.println("\r" + ANSI_GREEN + "Completed!" + ANSI_RESET);
        Thread.sleep(500);
        System.out.println();
    }

    public void printTestImage(String[] asciiLines) {
        int colorIndex = 0;
        for (String line : asciiLines) {
            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                if (Character.isWhitespace(ch)) {
                    System.out.print(ch);
                } else {
                    System.out.print(RAINBOW_COLORS[colorIndex % RAINBOW_COLORS.length] + ch + ANSI_RESET);
                    colorIndex++;
                }
            }
            System.out.println();
        }
    }

    public void currencyLabel()
    {
        System.out.println(ANSI_RESET + ANSI_BLUE + "=============================================" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "*      " + ANSI_ORANGE + "Welcome to Currency Exchange App" + ANSI_GREEN + "      *" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "=============================================" + ANSI_RESET);
    }

    public void currencyExchangeLabel()
    {
        System.out.println(ANSI_RESET + ANSI_GREEN + "============================================" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "    \uD83D\uDCB0  Currency Exchange Services  \uD83D\uDCB0" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "============================================" + ANSI_RESET);
    }

    public void adminLabel()
    {
        System.out.println(ANSI_RESET + ANSI_BLUE + "============================================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "       \uD83D\uDEE0      Admin Menu      \uD83D\uDEE0" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "============================================" + ANSI_RESET);
    }

    public void currencyExchangeAppLabel()
    {
        System.out.println(ANSI_RESET + ANSI_GREEN + "========================================================================================" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "         💱 Welcome to the Currency Exchange Application! 💱" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "========================================================================================" + ANSI_RESET);
    }


    public void clearScreen() {
        // This works on most terminals that support ANSI escape sequences
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void welcomeCurrencyExchange()
    {
        System.out.println(ANSI_CYAN + "-----------------------------------------------------------------------------------  " );
        System.out.println(ANSI_YELLOW + "💱                            Exchange Section                                  💱 ");
        System.out.println(ANSI_CYAN + "-----------------------------------------------------------------------------------  " + ANSI_RESET);
    }

    public void printPopularCurrenciesDisplayLabel()
    {
        System.out.println(ANSI_CYAN + "-----------------------------------------------------------------------------------  " );
        System.out.println(ANSI_YELLOW + "\uD83D\uDCCA                         Popular Currencies Section                         \uD83D\uDCCA ");
        System.out.println(ANSI_CYAN + "-----------------------------------------------------------------------------------  " + ANSI_RESET);

    }

    public void printAddCurrencyLabel()
    {
        System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------------------------  ");
        System.out.println(ANSI_GREEN + "\uD83D\uDCB5                         Add Currency Section                         \uD83D\uDCB5 ");
        System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------------------------  " + ANSI_RESET);
    }

    public void printUpdateConversionRate()
    {
        System.out.println(ANSI_RED + "-----------------------------------------------------------------------------------  ");
        System.out.println(ANSI_CYAN + "🔄                         Update Conversion Section                        🔄 ");
        System.out.println(ANSI_RED + "-----------------------------------------------------------------------------------  " + ANSI_RESET);
    }

    public void printPopularCurrencyAction()
    {
        System.out.println(ANSI_BLUE + "-----------------------------------------------------------------------------------  ");
        System.out.println(ANSI_GREEN + "⭐                         Popular Currencies Section                       ⭐ ");
        System.out.println(ANSI_BLUE + "-----------------------------------------------------------------------------------  " + ANSI_RESET);
    }

    public void printUpdatePopularCurrency()
    {
        System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------------------------  ");
        System.out.println(ANSI_YELLOW + "🔔                         Update Popular Currency                       🔔 ");
        System.out.println(ANSI_PURPLE + "-----------------------------------------------------------------------------------  " + ANSI_RESET);
    }

    public void printConversionHistoryLabel()
    {
        System.out.println(ANSI_RED + "-----------------------------------------------------------------------------------  ");
        System.out.println(ANSI_CYAN + "📜                         Conversion History Section                       📜 ");
        System.out.println(ANSI_RED + "-----------------------------------------------------------------------------------  " + ANSI_RESET);
    }



    public void drawPopularCurrenciesTable(String[] popularCurrencies, HashMap<String, HashMap<String, Double>> exchangeRates, List<RateUpdateSnapshot> rateUpdates)
    {
        // Print the title in cyan
        System.out.println(ANSI_CYAN + "Exchange Rates Table of Popular Currencies:" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "---------------------------------------------------------------" + ANSI_RESET);

        // Print the header row (the column titles) with cyan color for headers
        System.out.printf(ANSI_CYAN + "| %-10s |" + ANSI_RESET, "From/To");
        for (String currency : popularCurrencies) {
            System.out.printf(ANSI_CYAN + " %10s |" + ANSI_RESET, currency);  // Right-align the currency headers
        }
        System.out.println();
        System.out.println(ANSI_CYAN + "---------------------------------------------------------------" + ANSI_RESET);

        // Print the exchange rates row by row
        for (String fromCurrency : popularCurrencies) {
            // Print the left column (fromCurrency) in white bold
            System.out.printf(ANSI_WHITE + "| %-10s |" + ANSI_RESET, fromCurrency);

            for (String toCurrency : popularCurrencies) {
                if (fromCurrency.equals(toCurrency)) {
                    // If converting to the same currency, print a dash
                    System.out.printf(ANSI_YELLOW + " %10s |" + ANSI_RESET, "-");
                } else {
                    // Fetch the rate from the exchangeRates map
                    Double rate = exchangeRates.get(fromCurrency) != null
                            ? exchangeRates.get(fromCurrency).get(toCurrency)
                            : null;
                    if (rate == null) {
                        // Print "N/A" in red for missing values
                        System.out.printf(ANSI_RED + " %10s |" + ANSI_RESET, "N/A");
                    } else {
                        // Check if the rate has increased or decreased
                        Calculating calculating = new Calculating();
                        Integer trend = calculating.trend(rateUpdates, fromCurrency, toCurrency);
                        if (trend == 1) {
                            // Rate increased, display in green with an up arrow
                            System.out.printf(ANSI_GREEN + " %9.2f↑ |" + ANSI_RESET, rate);
                        } else if (trend == -1) {
                            // Rate decreased, display in red with a down arrow
                            System.out.printf(ANSI_RED + " %9.2f↓ |" + ANSI_RESET, rate);
                        } else {
                            // No change, display in yellow
                            System.out.printf(ANSI_YELLOW + " %10.2f |" + ANSI_RESET, rate);
                        }
                    }
                }
            }
            System.out.println();
            System.out.println(ANSI_CYAN + "---------------------------------------------------------------" + ANSI_RESET);
        }
    }

    public void fireworkAnimation() throws InterruptedException {
        clearScreen();
        // Stage 1 - Launch
        System.out.println("                |");
        System.out.println("                |");
        System.out.println("                |");
        Thread.sleep(200);
        // Stage 2 - Small Explosion
        clearScreen();
        System.out.println(ANSI_YELLOW + "                *" + ANSI_RESET);
        Thread.sleep(200);
        // Stage 3 - Medium Explosion
        clearScreen();
        System.out.println(ANSI_RED + "               ***" + ANSI_RESET);
        System.out.println(ANSI_RED + "              *****" + ANSI_RESET);
        System.out.println(ANSI_RED + "               ***" + ANSI_RESET);
        Thread.sleep(200);
        // Stage 4 - Large Explosion
        clearScreen();
        System.out.println(ANSI_GREEN + "               * * *" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "              *  *  *" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "             *   *   *" + ANSI_RESET);
        Thread.sleep(300);
        // Stage 5 - Fade out
        clearScreen();
        System.out.println(ANSI_CYAN + "               *   *   *" + ANSI_RESET);
        Thread.sleep(300);
        clearScreen();
    }

    public void drawBar(double value, String label, double maxValue) {
        int barLength = (int) ((value / maxValue) * 40); // Scale based on the maximum value
        System.out.printf("%-20s: ", label);
        for (int i = 0; i < barLength; i++) {
            System.out.print("█");
        }
        System.out.printf(" %.4f\n", value); // Print the value at the end of the bar
    }

    public void drawPlot(ArrayList<Double> exchangeRateData)
    {
        Calculating calculating = new Calculating();
        double max = calculating.max(exchangeRateData);    // Replace with actual values
        double min = calculating.min(exchangeRateData);    // Replace with actual values
        double median = calculating.median(exchangeRateData);    // Replace with actual values
        double mean = calculating.mean(exchangeRateData);    // Replace with actual values
        double stddev = calculating.standardDeviation(exchangeRateData);    // Replace with actual values

        // Find the maximum value to scale bars
        double globalMax = Math.max(max, Math.max(mean, Math.max(median, stddev)));

        System.out.println("Statistics Overview:");
        drawBar(max, "Maximum", globalMax);
        drawBar(min, "Minimum", globalMax);
        drawBar(median, "Median", globalMax);
        drawBar(mean, "Mean", globalMax);
        drawBar(stddev, "Standard Deviation", globalMax);
    }



    public void endProgram() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            fireworkAnimation();
            Thread.sleep(1000);  // Pause for a second before the next firework
        }
    }

    public String[] welcomeMessage() {
        return new String[]{
                " /$$      /$$           /$$                                             ",
                "| $$  /$ | $$          | $$                                             ",
                "| $$ /$$$| $$  /$$$$$$ | $$  /$$$$$$$  /$$$$$$  /$$$$$$/$$$$   /$$$$$$   ",
                "| $$/$$ $$ $$ /$$__  $$| $$ /$$_____/ /$$__  $$| $$_  $$_  $$ /$$__  $$  ",
                "| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$  \\ $$| $$ \\ $$ \\ $$| $$$$$$$$  ",
                "| $$$/ \\  $$$| $$_____/| $$| $$      | $$  | $$| $$ | $$ | $$| $$_____/  ",
                "| $$/   \\  $$|  $$$$$$$| $$|  $$$$$$$|  $$$$$$/| $$ | $$ | $$|  $$$$$$$  ",
                "|__/     \\__/ \\_______/|__/ \\_______/ \\______/ |__/ |__/ |__/ \\_______/  ",
                "                                                                       ",
                "                                                                       ",
                "                                                                       "
        };
    }

    public String[] toMessage() {
        return new String[]{
                " /$$$$$$$$       ",
                "|__  $$__/       ",
                "   | $$  /$$$$$$ ",
                "   | $$ /$$__  $$",
                "   | $$| $$  \\ $$",
                "   | $$| $$  | $$",
                "   | $$|  $$$$$$/",
                "   |__/ \\______/ ",
                "                 ",
                "                 ",
                "                 "
        };
    }

    public String[] currencyMessage() {
        return new String[]{
                "  /$$$$$$                                                                       ",
                " /$$__  $$                                                                      ",
                "| $$  \\__/ /$$   /$$  /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$$   /$$$$$$$ /$$   /$$",
                "| $$      | $$  | $$ /$$__  $$ /$$__  $$ /$$__  $$| $$__  $$ /$$_____/| $$  | $$",
                "| $$      | $$  | $$| $$  \\__/| $$  \\__/| $$$$$$$$| $$  \\ $$| $$      | $$  | $$",
                "| $$    $$| $$  | $$| $$      | $$      | $$_____/| $$  | $$| $$      | $$  | $$",
                "|  $$$$$$/|  $$$$$$/| $$      | $$      |  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$$",
                " \\______/  \\______/ |__/      |__/       \\_______/|__/  |__/ \\_______/ \\____  $$",
                "                                                                       /$$  | $$",
                "                                                                      |  $$$$$$/",
                "                                                                       \\______/ "
        };
    }


    public String[] exchangeMessage() {
        return new String[]{
                " /$$$$$$$$                     /$$                                               ",
                "| $$_____/                    | $$                                               ",
                "| $$       /$$   /$$  /$$$$$$$| $$$$$$$   /$$$$$$  /$$$$$$$   /$$$$$$   /$$$$$$  ",
                "| $$$$$   |  $$ /$$/ /$$_____/| $$__  $$ |____  $$| $$__  $$ /$$__  $$ /$$__  $$ ",
                "| $$__/    \\  $$$$/ | $$      | $$  \\ $$  /$$$$$$$| $$  \\ $$| $$  \\ $$| $$$$$$$$ ",
                "| $$        >$$  $$ | $$      | $$  | $$ /$$__  $$| $$  | $$| $$  | $$| $$_____/ ",
                "| $$$$$$$$ /$$/\\  $$|  $$$$$$$| $$  | $$|  $$$$$$$| $$  | $$|  $$$$$$$|  $$$$$$$ ",
                "|________/|__/  \\__/ \\_______/|__/  |__/ \\_______/|__/  |__/ \\____  $$ \\_______/ ",
                "                                                             /$$  \\ $$          ",
                "                                                            |  $$$$$$/          ",
                "                                                             \\______/           "
        };
    }

    public String[] appMessage() {
        return new String[]{
                "  /$$$$$$                     ",
                " /$$__  $$                    ",
                "| $$  \\ $$  /$$$$$$   /$$$$$$  ",
                "| $$$$$$$$ /$$__  $$ /$$__  $$ ",
                "| $$__  $$| $$  \\ $$| $$  \\ $$ ",
                "| $$  | $$| $$  | $$| $$  | $$ ",
                "| $$  | $$| $$$$$$$/| $$$$$$$/ ",
                "|__/  |__/| $$____/ | $$____/  ",
                "          | $$      | $$       ",
                "          | $$      | $$       ",
        };
    }

    public void printOverwrittenMessages() throws InterruptedException {
        // Array of all message functions
        String[][] messages = {welcomeMessage(), toMessage(), exchangeMessage(), currencyMessage(), appMessage()};

        // Loop through each message
        for (String[] message : messages) {

            // Clear the terminal before printing each message
            clearScreen();
            Thread.sleep(100);

            // Print the current message
            printTestImage(message);

            // Pause for a moment so that the effect is visible
            Thread.sleep(2000);
        }

    }


    public void displayingAdminMenu() {
        System.out.println("What would you like to do?");
        System.out.println("-------------------------------------------");
        System.out.println("1. 💱 Exchange Currency");
        System.out.println("2. 💲 Add currency");
        System.out.println("3. \uD83D\uDD04 Update conversion rate");
        System.out.println("4. ✏\uFE0F  Popular currency actions");
        System.out.println("5. \uD83D\uDD52 Conversion History");
        System.out.println("6. \uD83D\uDEAA Log out");
        System.out.println("-------------------------------------------");
        System.out.print("Please enter your choice: (1 to 6) ");
    }


    public void displayingUserMenu() {
        System.out.println("What would you like to do?");
        System.out.println("---------------------------------------------");
        System.out.println("1. 💱 Exchange Currency");
        System.out.println("2. 🌍 Display Popular Currencies");
        System.out.println("3. \uD83D\uDD52 Conversion History");
        System.out.println("4. \uD83D\uDEAA Exit");
        System.out.println("---------------------------------------------");
        System.out.print("Please enter your choice (1 to 4): ");
    }

    public void displayingConversionHistory (String allCurrencies) throws InterruptedException {
        this.loadingAction("Loading Conversion History");
        this.printConversionHistory();
        this.printConversionHistoryLabel();
        // Conversion history will be handed later.
        System.out.println("Welcome to the history of conversion rates!");
        System.out.println();
        // Input source currency
        System.out.printf("Select the source currency %s\uD83D\uDCE4: ", allCurrencies);
        System.out.println("---------------------------------------------");
        System.out.println("Welcome to the history of conversion rates!");
        System.out.println();
    }



}