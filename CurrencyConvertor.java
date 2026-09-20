import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {

    // Map to store simulated exchange rates relative to USD (Base Currency)
    private static final Map<String, Double> exchangeRates = new HashMap<>();

    static {
        // Base Rates relative to 1 USD
        exchangeRates.put("USD", 1.0);       // US Dollar
        exchangeRates.put("INR", 83.25);     // Indian Rupee
        exchangeRates.put("EUR", 0.92);      // Euro
        exchangeRates.put("GBP", 0.79);      // British Pound
        exchangeRates.put("CAD", 1.35);      // Canadian Dollar
        exchangeRates.put("AUD", 1.52);      // Australian Dollar
        exchangeRates.put("JPY", 155.40);    // Japanese Yen
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("        CURRENCY CONVERTER SYSTEM         ");
        System.out.println("==========================================");

        // Display supported currencies
        System.out.println("Supported Currencies:");
        System.out.println(String.join(", ", exchangeRates.keySet()));
        System.out.println("==========================================");

        // 1. Select Base Currency
        String baseCurrency = getValidCurrency(scanner, "Enter base currency (e.g., USD, INR, EUR): ");

        // 2. Select Target Currency
        String targetCurrency = getValidCurrency(scanner, "Enter target currency (e.g., EUR, INR, USD): ");

        // 3. Input Amount
        System.out.print("Enter amount to convert: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid amount! Enter a numeric value: ");
            scanner.next();
        }
        double amount = scanner.nextDouble();

        while (amount < 0) {
            System.out.print("Amount cannot be negative. Enter a valid amount: ");
            amount = scanner.nextDouble();
        }

        // 4. Perform Currency Conversion
        double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);

        // 5. Display Results
        System.out.println("\n==========================================");
        System.out.println("            CONVERSION RESULT             ");
        System.out.println("==========================================");
        System.out.printf("Base Amount   : %.2f %s\n", amount, baseCurrency);
        System.out.printf("Converted     : %.2f %s\n", convertedAmount, targetCurrency);
        System.out.printf("Exchange Rate : 1 %s = %.4f %s\n", 
                baseCurrency, (exchangeRates.get(targetCurrency) / exchangeRates.get(baseCurrency)), targetCurrency);
        System.out.println("==========================================");

        scanner.close();
    }

    // Helper method to prompt and validate currency input
    private static String getValidCurrency(Scanner scanner, String prompt) {
        String currency;
        while (true) {
            System.out.print(prompt);
            currency = scanner.next().trim().toUpperCase();
            if (exchangeRates.containsKey(currency)) {
                return currency;
            }
            System.out.println("Unsupported currency! Available: " + exchangeRates.keySet());
        }
    }

    // Conversion logic using USD as cross-currency rate
    private static double convertCurrency(String from, String to, double amount) {
        double fromRate = exchangeRates.get(from);
        double toRate = exchangeRates.get(to);

        // Convert amount from source currency to USD, then USD to target currency
        double amountInUSD = amount / fromRate;
        return amountInUSD * toRate;
    }
}
