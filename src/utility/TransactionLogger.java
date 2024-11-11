package utility;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.Transaction;

public class TransactionLogger {
    private static final String FILE_PATH = "transaction_history.txt";

    // Logs transaction details to a file
    public static void logTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String logEntry = formatTransaction(transaction);
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing transaction to file: " + e.getMessage());
        }
    }

    // Formats the transaction into a readable string format
    private static String formatTransaction(Transaction transaction) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = dtf.format(LocalDateTime.now());
        return String.format("[%s] Transaction ID: %d, User ID: %d, Product ID: %d, Product Name: %s, Type: %s, Quantity: %d, " +
                             "Cost per unit: %.2f, Total Cost: %.2f",
                             timestamp,
                             transaction.getTransactionId(),
                             transaction.getUserId(),
                             transaction.getProductId(),
                             transaction.getProductName(),
                             transaction.getTransactionType(),
                             transaction.getQuantity(),
                             transaction.getCost(),
                             transaction.getTotalCost());
    }
}

