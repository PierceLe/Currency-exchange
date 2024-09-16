package CurrencyExchange;

import java.util.HashMap;
import java.util.Map;

/**
 * The Currency class represents a specific currency in the currency exchange system.
 * Each Currency object contains the currency code, symbol, and conversion rates to other currencies.
 * The class provides functionality to:
 * - Retrieve the currency code and symbol.
 * - Get the current conversion rates for this currency.
 */
public class Currency {
    private final String code;  // The unique code for the currency (e.g., "USD").
    private final Map<String, Double> conversionRate;  // A map storing conversion rates to other currencies.

    /**
     * Constructs a Currency object with a code, symbol, and a set of initial conversion rates.
     *
     * @param code The unique code representing the currency (e.g., "USD").
     * @param conversionRate A map of conversion rates to other currencies (e.g., "EUR" -> 0.85).
     */
    public Currency(String code, Map<String, Double> conversionRate) {
        this.code = code;
        this.conversionRate = conversionRate;  // Initialize conversion rates
    }

    /**
     * Returns the unique code for the currency (e.g., "USD").
     *
     * @return The code of the currency.
     */
    public String getCode() {
        return code;
    }


    /**
     * Retrieves the current conversion rates for this currency.
     * The returned map contains the conversion rates to other currencies.
     *
     * @return A map of conversion rates where the key is the target currency code
     *         and the value is the conversion rate.
     */
    public Map<String, Double> getConversionRate() {
        return new HashMap<>(conversionRate);  // Return a copy for safety
    }

    public Double getRate(String toCurrencyCode) {
        Double rate = conversionRate.get(toCurrencyCode);
        if (rate == null) {
            return -1.0;
        }
        return rate;
    }
}