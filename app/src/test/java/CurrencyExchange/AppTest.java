package CurrencyExchange;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;


public class AppTest
{
    @Test
    // success
    public void testUserMenu1() throws IOException, InterruptedException {
        String input = "1\n4\n3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testUserMenu2() throws IOException, InterruptedException {
        String input = "1\n1\nUSD\nAUD\n100\n4\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testUserMenu3() throws IOException, InterruptedException {
        String input = "1\n2\n4\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testUserMenu4() throws IOException, InterruptedException {
        String input = "1\n3\nUSD\nAUD\n2024-09-14\n2024-09-15\n4\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }


    @Test
    public void testUserMenu6() throws IOException, InterruptedException {
        String input = "1\n3\nUSD\nAUD\nabcdefg\n4\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testAdminMenu1() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n1\nusd\naud\n100\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testAdminMenu2() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n2\nVND\n100\n100\n100\n100\n100\n100\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testAdminMenu3() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n3\nUSD\nAUD\n10\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }


    @Test
    public void testAdminMenu5() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n5\nUSD\nAUD\n2024-09-14\n2024-09-15\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }
    //invalid
    @Test
    public void testAdminMenu6() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n1\n1\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testAdminMenu7() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n2";
    }
    @Test
    public void testAdminMenu8() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n3\ns\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testAdminMenu9() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n5\ns\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testAdminMenu10() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n5\ns\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testLogin() throws IOException, InterruptedException {
        String input = "2\nadmin\n12345678\n5\nUSD\nAUD\ns\n6\n3";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

    @Test
    public void testExitProgram() throws IOException, InterruptedException {
        String input = "3\n";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);
        App.main(new String[0]);
    }

}