package CurrencyExchange;

public class RateUpdateSnapshot {
    private final String fromCurrency;
    private final String toCurrency;
    private final double rate;

    public RateUpdateSnapshot(String fromCurrency, String toCurrency, double rate) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.rate = rate;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public double getRate() {
        return rate;
    }
}