package CurrencyExchange;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.util.*;
import java.io.*;

public class DatabaseService {
    private final String configPath;
    private final String currenciesHistoryPath;
    private final String conversionHistoryPath;
    private final String popularCurrenciesPath;

    public DatabaseService(String configPath, String conversionHistoryPath, String currenciesHistoryPath, String popularCurrenciesPath){
        this.configPath = configPath;
        this.currenciesHistoryPath = currenciesHistoryPath;
        this.conversionHistoryPath = conversionHistoryPath;
        this.popularCurrenciesPath = popularCurrenciesPath;
    }

    private String echo(String input) {
        if (input.startsWith("\"") && input.endsWith("\"")) {
            return input.substring(1, input.length() - 1);
        }
        return input;
    }
    private void formattingCorrectForm(String[] plainFormat) {
        for(int i = 0; i < plainFormat.length; i++) {
            plainFormat[i] = echo(plainFormat[i]);
        }
    }


    public ArrayList<String> getAllCurrencies() {
        ArrayList<String> currencies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(configPath))) {
            // Read the first line (header) from the CSV file
            String headerLine = br.readLine();
            if (headerLine != null) {
                // Split the header line to extract currency codes
                String[] headers = headerLine.split(",");
                formattingCorrectForm(headers);
                // Add each currency code from the second element onwards (ignoring "From/To")
                currencies.addAll(Arrays.asList(headers).subList(1, headers.length));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencies;
    }


    public HashMap<String, HashMap<String, Double>> getRatesFromData() {
        HashMap<String, HashMap<String, Double>> ratesMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.configPath))) {
            String line = br.readLine();  // Read the header line (From/To, USD, AUD, GBP, etc.)
            String[] headers = line.split(",");  // Split the header to get currency codes
            formattingCorrectForm(headers);

            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Split the line by commas
                formattingCorrectForm(values);
                String fromCurrency = values[0];    // The first value is the "From" currency

                HashMap<String, Double> toCurrencies = getStringDoubleHashMap(values, headers);

                // Add the "From" currency and its corresponding "To" currency rates to the main map
                ratesMap.put(fromCurrency, toCurrencies);
            }

        } catch (IOException e) {
            e.printStackTrace();  // Handle any exceptions (e.g., file not found)
        }

        return ratesMap;
    }

    private HashMap<String, Double> getStringDoubleHashMap(String[] values, String[] headers) {
        HashMap<String, Double> toCurrencies = new HashMap<>();
        for (int i = 1; i < values.length; i++) {
            String toCurrency = headers[i];  // Get the "To" currency from the headers

            // Ignore missing or invalid values
            if (!values[i].isEmpty() && !values[i].equals("-")) {
                double rate = Double.parseDouble(values[i]);
                toCurrencies.put(toCurrency, rate);  // Add the exchange rate to the map
            }
        }
        return toCurrencies;
    }


    public void addRowToCSV(ArrayList<String> newRow) {
        newRow.replaceAll(this::echo);
        try (FileWriter writer = new FileWriter(this.configPath, true)) {
            String row = String.join(",", newRow);
            writer.append(row).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addColumnToCSV(String newColumnHeader, List<String> newColumnValues) {
        ArrayList<String[]> csvData = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.configPath))) {
            String line;
            int rowIndex = 0;

            // Read the existing CSV content
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");

                // For the first row (header), append the new column header
                if (rowIndex == 0) {
                    row = Arrays.copyOf(row, row.length + 1);
                    row[row.length - 1] = newColumnHeader;
                } else {
                    // Check if there is a value for the new column in newColumnValues
                    String newValue = (rowIndex - 1 < newColumnValues.size()) ? newColumnValues.get(rowIndex - 1) : "";
                    row = Arrays.copyOf(row, row.length + 1);
                    row[row.length - 1] = newValue; // Add the new column value or empty string if index is out of bounds
                }

                csvData.add(row);
                rowIndex++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        // Write the updated content back to the CSV file (overwrite mode)
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.configPath))) {
            for (String[] row : csvData) {
                formattingCorrectForm(row);
                writer.println(String.join(",", row));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateCurrenciesHistoryPath(String message) {
        try (FileWriter writer = new FileWriter(this.currenciesHistoryPath, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateRate(Integer row, Integer col, Double newRate) throws IOException {
        File inputFile = new File(this.configPath);

        CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
        List<String[]> csvBody = reader.readAll();
        csvBody.get(row)[col] = String.format("%.2f", newRate);
        csvBody.get(col)[row] = String.format("%.2f", 1.0/newRate);
        reader.close();
        CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
        writer.writeAll(csvBody);
        writer.flush();
        writer.close();

    }


    public Map<String, ArrayList<String>> retrieveConversionRateData() {
        Map<String, ArrayList<String>> conversionData = new HashMap<>();

        // Try reading the file
        try (BufferedReader br = new BufferedReader(new FileReader(this.conversionHistoryPath))) {
            String line;
            boolean isFirstLine = true; // To skip the header line

            // Read each line from the file
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    // Skip the header line
                    isFirstLine = false;
                    continue;
                }

                // Split the line by comma (CSV format)
                String[] fields = line.split(",");

                // Ensure the line has the correct number of elements (7 fields: Date, Rate, From, To, Amount, Result, Admin)
                if (fields.length == 7) {
                    String date = fields[0].trim();  // Use date as the key

                    // Create a single string for the details by concatenating all fields except the date
                    String details = String.join(", ", Arrays.copyOfRange(fields, 1, fields.length));

                    // If the date already exists in the map, add the new details to the existing list
                    if (conversionData.containsKey(date)) {
                        conversionData.get(date).add(details);
                    } else {
                        // If the date doesn't exist, create a new list and add the details
                        ArrayList<String> recordList = new ArrayList<>();
                        recordList.add(details);
                        conversionData.put(date, recordList);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception (e.g., file not found)
        }

        for (String i : conversionData.keySet()) {
            System.out.println(conversionData.get(i));
        }
        return conversionData;
    }


    public void pushingToConversionRate(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.conversionHistoryPath, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println(Drawing.ANSI_RED + "An error occurred while writing to the file: " + e.getMessage() + Drawing.ANSI_RESET);
        }
    }


    public void writePopularCurrenciesToDatabase(String[] popularCurrencies) {
        try (BufferedWriter writer =  new BufferedWriter(new FileWriter(this.popularCurrenciesPath))) {
            writer.write(String.join(",", popularCurrencies));
            System.out.println(Drawing.ANSI_GREEN + "Successfully wrote popular currencies to file." + Drawing.ANSI_RESET);
        } catch (IOException e) {
            System.out.println(Drawing.ANSI_RED + "Error while writing popular currencies." + Drawing.ANSI_RESET);
            e.printStackTrace();
        }
    }


    public List<RateUpdateSnapshot> loadCurrenciesHistory() {
        List<RateUpdateSnapshot> rateUpdates = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.currenciesHistoryPath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String fromCurrency = parts[8];
                String toCurrency = parts[10];
                double rate = Double.parseDouble(parts[14]);
                rateUpdates.add(new RateUpdateSnapshot(fromCurrency, toCurrency, rate));
            }
        } catch (IOException e) {
            System.out.println(Drawing.ANSI_RED + "Error while reading currencies history file." + Drawing.ANSI_RESET);
            e.printStackTrace();
        }
        return rateUpdates;
    }
}