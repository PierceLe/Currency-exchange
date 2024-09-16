package CurrencyExchange;

import org.junit.jupiter.api.Test;

import java.util.*;

        import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {

    @Test
    public void testConstructor(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
    }

    @Test
    public void testgetCode(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals("USD", currency.getCode());
    }

    @Test
    public void testgetConversionRate(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(4,conversionRates.size());
        assertTrue(currency.getConversionRate().containsKey("USD") && currency.getConversionRate().get("USD") == 1.0);
        assertTrue(currency.getConversionRate().containsKey("EUR") && currency.getConversionRate().get("EUR") == 2.0);
        assertTrue(currency.getConversionRate().containsKey("GBP") && currency.getConversionRate().get("GBP") == 3.0);
        assertTrue(currency.getConversionRate().containsKey("CHF") && currency.getConversionRate().get("CHF") == 1.32);
    }

    @Test
    public void testPositiveGetRate1(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(2.0,currency.getRate("EUR"));
    }

    @Test
    public void testPositiveGetRate2(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(1.32,currency.getRate("CHF"));
    }

    @Test
    public void testNegativeGetRate1(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(-1.0,currency.getRate("VND"));
    }

    @Test
    public void testNegativeGetRate2(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", null);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(-1.0,currency.getRate("USD"));
    }

    @Test
    public void testEdgeGetRate1(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        conversionRates.put("USD", 1.0);
        conversionRates.put("EUR", 2.0);
        conversionRates.put("GBP", 3.0);
        conversionRates.put("CHF", 1.32);
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(-1.0,currency.getRate("eur"));
    }

    @Test
    public void testEdgeGetRate2(){
        Map<String, Double> conversionRates = new HashMap<String, Double>();
        Currency currency = new Currency("USD", conversionRates);
        assertNotNull(currency);
        assertEquals(-1.0,currency.getRate("EUR"));
    }
}
