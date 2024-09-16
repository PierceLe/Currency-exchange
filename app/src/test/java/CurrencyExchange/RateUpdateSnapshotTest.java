package CurrencyExchange;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class RateUpdateSnapshotTest {
    RateUpdateSnapshot snapshot;

    @BeforeEach
    public void testConstructor() {
        snapshot = new RateUpdateSnapshot("USD", "AUD", 0.67);
        assertNotNull(snapshot);
    }

    @Test
    public void testGetFromCurrency() {
        assertEquals("USD", snapshot.getFromCurrency());
    }

    @Test
    public void testGetToCurrency() {
        assertEquals("AUD", snapshot.getToCurrency());
    }

    @Test
    public void testGetRate() {
        assertEquals(0.67, snapshot.getRate());
    }
}
