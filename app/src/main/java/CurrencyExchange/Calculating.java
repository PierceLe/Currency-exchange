package CurrencyExchange;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculating {
    public Calculating() {

    }

    public Integer trend(List<RateUpdateSnapshot> rateUpdates, String fromCurrency, String toCurrency) {
        List<RateUpdateSnapshot> interestedUpdate = new ArrayList<>();

        for (RateUpdateSnapshot rateUpdate : rateUpdates) {
            if (rateUpdate.getFromCurrency().equals(fromCurrency) && rateUpdate.getToCurrency().equals(toCurrency)) {
                interestedUpdate.add(rateUpdate);
            }
        }
        if (interestedUpdate.size() < 2) {
            return 0;
        }
        RateUpdateSnapshot newest = interestedUpdate.get(interestedUpdate.size() - 1);
        RateUpdateSnapshot secondNewest = interestedUpdate.get(interestedUpdate.size() - 2);
        return Double.compare(newest.getRate(), secondNewest.getRate());
    }


    public Double min(ArrayList<Double> rateData) {
        if (rateData.isEmpty()){
            return 0.0;
        }
        return Collections.min(rateData);
    }

    public Double max(ArrayList<Double> rateData) {
        if (rateData.isEmpty()){
            return 0.0;
        }
        return Collections.max(rateData);
    }

    public Double standardDeviation(ArrayList<Double> rateData) {
        if (rateData.isEmpty()){
            return 0.0;
        }
        Double mean = this.mean(rateData);

        double sumOfSquares = 0.0;
        for (Double d: rateData) {
            sumOfSquares += Math.pow(d - mean, 2);
        }
        return Math.sqrt(sumOfSquares/rateData.size());
    }

    public Double mean(ArrayList<Double> rateData){
        if (rateData.isEmpty()){
            return 0.0;
        }
        Double sum = 0.0;
        for (Double d: rateData) {
            sum += d;
        }
        return sum/rateData.size();
    }

    public Double median(ArrayList<Double> rateData) {
        Collections.sort(rateData);
        if (rateData.isEmpty()){
            return 0.0;
        }else if (rateData.size() % 2 == 0) {
            int midIdx = rateData.size() / 2;
            return (rateData.get(midIdx) + rateData.get(midIdx - 1))/2;
        } else {
            return rateData.get(rateData.size()/2);
        }
    }

    public void exchangeRate(App app, String codeCurrencyFrom, String codeCurrencyTo, Double amount, Boolean isAdmin) {
        CurrencyFactory factory = new CurrencyFactory(app);
        Currency currencyFrom = factory.createCurrency(codeCurrencyFrom);
        Double rate = currencyFrom.getRate(codeCurrencyTo);
        double result = amount * rate;
        if (result < 0) {
            System.out.print(Drawing.ANSI_RED + "Invalid currency selection. Please try again.\n" +  Drawing.ANSI_RESET);
            return;
        } else {
            System.out.printf(Drawing.ANSI_GREEN + "Currency exchange successfully converted to %.2f\n" + Drawing.ANSI_RESET, result);
            System.out.print(Drawing.ANSI_INDIGO + "---------------------------------------------\n"+ Drawing.ANSI_RESET);
        }
        LocalDate date = LocalDate.now();
        Integer admin = isAdmin ? 1 : 0;
        app.getDatabaseService().pushingToConversionRate(String.format(
                "%s,%s,%s,%s,%s,%.2f,%s", date, rate, codeCurrencyFrom, codeCurrencyTo, amount, result, admin
        ));
    }
}
