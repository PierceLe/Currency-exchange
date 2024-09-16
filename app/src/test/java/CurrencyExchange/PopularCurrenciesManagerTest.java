package CurrencyExchange;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
public class PopularCurrenciesManagerTest {


    /**
     * Tests the output of user menu when user tries to display popular currencies but the admin has not specified any
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testUserDisplayPopular1() throws IOException, InterruptedException {
        String input = "1\n2\n4\n3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    /**
     * Tests the output of user menu when user tries to display the popular currencies that have been specified
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testUserDisplayPopular2() throws IOException, InterruptedException {
        String input = "1\n2\n4\n2\nadmin\n12345678\n4\nusd\naud\neur\nsgd\n3\n6\n1\n2\n4\n3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    /**
     * Tests the output of admin menu when admin tries to access the popular currencies section and choose to display it.
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testAdminDisplayPopular() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n4\n2\n3\n6\n3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    /**
     * Tests the output of admin menu when admin tries to access the popular currencies section and choose to update it.
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testAdminUpdatePopular() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n4\n1\n1,2,3,4\ngbp\ncad\naud\nusd\n3\n6\n3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }
}