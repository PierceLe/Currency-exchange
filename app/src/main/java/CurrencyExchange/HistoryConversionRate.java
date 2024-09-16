package CurrencyExchange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HistoryConversionRate {

    private final int RATE_IDX = 0;
    private final int SOURCE_IDX = 1;
    private final int TARGET_IDX = 2;
    private final int AMOUNT_IDX =3;
    private final int RESULT_IDX = 4;
    private final int ADMIN_IDX = 5;
    private LocalDate startDate;
    private LocalDate endDate;
    private String sourceCurrency;
    private String targetCurrency;
    private Boolean haveConversionRate;


    private Map<LocalDate, ArrayList<String>> relevantData = new HashMap<>();

    public HistoryConversionRate(LocalDate startDate, LocalDate endDate, String sourceCurrency, String targetCurrency) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.haveConversionRate = false;
    }

    public Map<LocalDate, ArrayList<String>> getRelevantData() {
        return relevantData;
    }

    public static boolean isExist(String currency, ArrayList<String> allCurrencies) {
        boolean isExist = allCurrencies.contains(currency);
        if (! isExist) {
            System.out.printf(Drawing.ANSI_RED + "Currency '%s' does not exist.\n\n", currency + Drawing.ANSI_RESET);
        }
        return isExist;
    }

    public void searchRelevantHistory(App app) {
        Map<String, ArrayList<String>> conversionData = app.getDatabaseService().retrieveConversionRateData();

        for (Map.Entry<String, ArrayList<String>> e : conversionData.entrySet()) {
            DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            FORMATTER = FORMATTER.withLocale(Locale.ENGLISH);
            LocalDate date = LocalDate.parse(e.getKey(), FORMATTER);

            // Check if the date within the time frame
            boolean compareStart = date.isAfter(this.startDate) | date.isEqual(this.startDate);
            boolean compareEnd = date.isBefore(this.endDate) | date.isEqual(this.endDate);
            if (!(compareStart && compareEnd)) {
                continue;
            }

            ArrayList<String> conversions = new ArrayList<>();
            for (String s: e.getValue()) {
                String[] infoConversion = s.split(",");
                if((infoConversion[SOURCE_IDX].trim().equals(sourceCurrency) && infoConversion[TARGET_IDX].trim().equals(targetCurrency)
                        && Integer.parseInt(infoConversion[ADMIN_IDX].trim()) == 1)) {
                    conversions.add(s);
                }
                if ((infoConversion[SOURCE_IDX].trim().equals(sourceCurrency) && infoConversion[TARGET_IDX].trim().equals(targetCurrency)
                        && Integer.parseInt(infoConversion[ADMIN_IDX].trim()) == 0)) {
                     this.haveConversionRate = true;
                }
            }
            if (this.haveConversionRate && conversions.isEmpty()) {
                System.out.println(Drawing.ANSI_RED + "Do not have any conversion rates of admin" + Drawing.ANSI_RESET);
                continue;
            }
            if (conversions.isEmpty()) {
                continue;
            }
            this.relevantData.put(date, conversions);
        }


    }
    public void printHistory(App app) {
        this.searchRelevantHistory(app);
        System.out.println(this.relevantData.size());
        Drawing drawing = new Drawing();


        // Print display the title.
        System.out.println(Drawing.ANSI_RED + "---------------------------------------------");
        System.out.println(Drawing.ANSI_CYAN + "📜          HISTORY OF CONVERSION RATE        📜");
        System.out.println(Drawing.ANSI_RED + "---------------------------------------------" + Drawing.ANSI_RESET);
        System.out.println("\uD83D\uDCC5Start date: " + this.startDate.toString());
        System.out.println("\uD83D\uDCC5End date: " + this.endDate.toString());
        System.out.println("\uD83D\uDCB1Source currency: " + this.sourceCurrency);
        System.out.println("\uD83C\uDFAFTarget currency: " + this.targetCurrency);

        // Print display all the conversion rate
        System.out.println(Drawing.ANSI_YELLOW + "---------------------------------------------");
        System.out.println(Drawing.ANSI_BLUE + "📈      ALL HISTORY OF CONVERSION RATE      📈");
        System.out.println(Drawing.ANSI_YELLOW + "---------------------------------------------" + Drawing.ANSI_RESET);

        int count = 1;
        ArrayList<Double> exchangeRateData = new ArrayList<>();

        for (Map.Entry<LocalDate, ArrayList<String>> e : this.relevantData.entrySet()) {
            for (String s : e.getValue()) {
                String[] infoConversion = s.split(",");
                System.out.printf("%d. From: %s, To: %s. Exchange rate: %s, Amount: %s, Result %s\n",
                        count, infoConversion[SOURCE_IDX].trim(), infoConversion[TARGET_IDX].trim(), infoConversion[RATE_IDX].trim(),
                        infoConversion[AMOUNT_IDX].trim(), infoConversion[RESULT_IDX].trim());
                count++;
                exchangeRateData.add(Double.parseDouble(infoConversion[RATE_IDX].trim()));
            }

        }
        Calculating calculating = new Calculating();
        // Print display all the conversion rate
        System.out.println(Drawing.ANSI_GREEN + "---------------------------------------------");
        System.out.println(Drawing.ANSI_PURPLE + "🔢      HISTORY NUMERICAL SUMMARY      🔢");
        System.out.println(Drawing.ANSI_GREEN + "---------------------------------------------" + Drawing.ANSI_RESET);
        System.out.printf("\uD83D\uDCC8 Maximum: %.4f\n", calculating.max(exchangeRateData));
        System.out.printf("\uD83D\uDCC9 Minimum: %.4f\n", calculating.min(exchangeRateData));
        System.out.printf("\uD83D\uDCCA Median: %.4f\n", calculating.median(exchangeRateData));
        System.out.printf("\uD83D\uDCD0 Mean: %.4f\n", calculating.mean(exchangeRateData));
        System.out.printf("\uD83D\uDCCF Standard Deviation: %.4f\n", calculating.standardDeviation(exchangeRateData));
        drawing.drawPlot(exchangeRateData);

    }

}
