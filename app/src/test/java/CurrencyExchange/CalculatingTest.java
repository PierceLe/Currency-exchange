 package CurrencyExchange;

 import org.junit.jupiter.api.Test;

 import java.io.*;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.ArrayList;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.*;


 public class CalculatingTest {

     @Test
     public void testConstructor(){
         Calculating cal = new Calculating();
         assertNotNull(cal);
     }

     @Test
     public void posTrend1(){
         Calculating cal = new Calculating();
         List<RateUpdateSnapshot> updates = new ArrayList<>();
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.67));
         updates.add(new RateUpdateSnapshot("EUR", "VND", 2.4));
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.75));
         updates.add(new RateUpdateSnapshot("VND", "USD", 1.2));
         updates.add(new RateUpdateSnapshot("AUD", "VND", 1.76));
         assertEquals(1,cal.trend(updates, "AUD", "USD"));
     }

     @Test
     public void posTrend2(){
         Calculating cal = new Calculating();
         List<RateUpdateSnapshot> updates = new ArrayList<>();
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.67));
         updates.add(new RateUpdateSnapshot("EUR", "VND", 2.4));
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.67));
         updates.add(new RateUpdateSnapshot("VND", "USD", 1.2));
         updates.add(new RateUpdateSnapshot("AUD", "VND", 1.76));
         assertEquals(0,cal.trend(updates, "AUD", "USD"));
     }

     @Test
     public void posTrend3(){
         Calculating cal = new Calculating();
         List<RateUpdateSnapshot> updates = new ArrayList<>();
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.67));
         updates.add(new RateUpdateSnapshot("EUR", "VND", 2.4));
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.55));
         updates.add(new RateUpdateSnapshot("VND", "USD", 1.2));
         updates.add(new RateUpdateSnapshot("AUD", "VND", 1.76));
         assertEquals(-1,cal.trend(updates, "AUD", "USD"));
     }

     @Test
     public void negTrend(){
         Calculating cal = new Calculating();
         List<RateUpdateSnapshot> updates = new ArrayList<>();
         updates.add(new RateUpdateSnapshot("EUR", "VND", 2.4));
         updates.add(new RateUpdateSnapshot("VND", "USD", 1.2));
         updates.add(new RateUpdateSnapshot("AUD", "VND", 1.76));
         assertEquals(0,cal.trend(updates, "AUD", "USD"));
     }

     @Test
     public void edgeTrend(){
         Calculating cal = new Calculating();
         List<RateUpdateSnapshot> updates = new ArrayList<>();
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.67));
         updates.add(new RateUpdateSnapshot("EUR", "VND", 2.4));
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.75));
         updates.add(new RateUpdateSnapshot("VND", "USD", 1.2));
         updates.add(new RateUpdateSnapshot("AUD", "VND", 1.76));
         updates.add(new RateUpdateSnapshot("AUD", "USD", 0.75));
         updates.add(new RateUpdateSnapshot("AUD", "VND", 1.76));
         updates.add(new RateUpdateSnapshot("Can", "VND", 3.76));
         assertEquals(0,cal.trend(updates, "AUD", "USD"));
     }

     @Test
     public void positiveMin1(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         assertEquals(0.65, cal.min(data));
     }

     @Test
     public void positiveMin2(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         assertEquals(1.0, cal.min(data));
     }

     @Test
     public void negativeMin(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         assertEquals(0.00, cal.min(data));
     }

     @Test
     public void edgeMin1(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         data.add(2.0);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         data.add(2.34);
         assertEquals(0.65, cal.min(data));
     }

     @Test
     public void edgeMin2(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(0.65);
         data.add(3.0);
         data.add(2.0);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         data.add(2.34);
         assertEquals(0.65, cal.min(data));
     }

     @Test
     public void positiveMax1(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.23);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         assertEquals(3.23, cal.max(data));
     }

     @Test
     public void positiveMax2(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         assertEquals(3.0, cal.max(data));
     }

     @Test
     public void negativeMax(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         assertEquals(0.00, cal.max(data));
     }

     @Test
     public void edgeMax1(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(5.23);
         data.add(3.0);
         data.add(2.0);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         data.add(2.34);
         data.add(5.23);
         assertEquals(5.23, cal.max(data));
     }

     @Test
     public void edgeMax2(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(0.65);
         data.add(2.0);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         data.add(2.34);
         assertEquals(2.34, cal.max(data));
     }

     @Test
     public void positiveStandardDeviation(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         data.add(3.2);
         data.add(4.7);
         Double expected = (1-2.78)*(1-2.78) + (2-2.78)*(2-2.78)+ (3-2.78)*(3-2.78)+ (3.2-2.78)*(3.2-2.78)+ (4.7-2.78)*(4.7-2.78);
         expected /= 5;
         assertEquals(Math.sqrt(expected), cal.standardDeviation(data));
     }

     @Test
     public void negativeStandardDeviation(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         assertEquals(0.00, cal.standardDeviation(data));
     }



     @Test
     public void positiveMean(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         data.add(3.2);
         data.add(4.7);
         assertEquals(2.78, cal.mean(data));
     }

     @Test
     public void negativeMean(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         assertEquals(0.00, cal.mean(data));
     }

     @Test
     public void positiveMedian1(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.23);
         data.add(0.65);
         data.add(1.0273);
         data.add(2.34);
         assertEquals((2.0 + 1.0273)/2, cal.median(data));
     }

     @Test
     public void positiveMedian2(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(3.0);
         assertEquals(2.0, cal.median(data));
     }

     @Test
     public void negMedian(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         assertEquals(0.00, cal.median(data));
     }

     @Test
     public void edgeMedian(){
         Calculating cal = new Calculating();
         ArrayList<Double> data = new ArrayList<>();
         data.add(1.0);
         data.add(2.0);
         data.add(2.0);
         data.add(3.23);
         data.add(3.0);
         assertEquals(2.0, cal.median(data));
     }

     @Test
     // positive test exchange currencies for both admin and user
     public void testExchangeCurrencies1() throws IOException, InterruptedException {
         String input = "1\n1\nusd\naud\n100\n4\n2\nadmin\n12345678\n1\nusd\naud\n20\n6\n3\n";
         ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
         System.setIn(byteArrayInputStream);
         App.main(new String[0]);
     }


 }
