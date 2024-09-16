package CurrencyExchange;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class HistoryConversionRateTest {
    @Test
    // success
    public void testdisplayConversionRate() throws IOException, InterruptedException {
        String input = "1\n3\nusd\naud\n2022-10-11\n2024-09-16\n4\n3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }
}
