package service;

import java.util.List;

import dao.ProductDAO;
import dao.TransactionDAO;
import model.Product;
import model.Transaction;
import utility.TransactionLogger;

public class AgentService {
    private ProductDAO productDAO;
    private TransactionDAO transactionDAO;

    public AgentService(ProductDAO productDAO, TransactionDAO transactionDAO) {
        this.productDAO = productDAO;
        this.transactionDAO = transactionDAO;
    }

    // Buy or sell a product
    public void processTransaction(int userId, int productId, String transactionType, int quantity) {
        Product product = productDAO.findProductById(productId);
        
        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        if ("Sell".equalsIgnoreCase(transactionType)) {
            // Check quantity for selling
            if (quantity > product.getQuantityAvailable()) {
                System.out.println("Product not available in sufficient quantity.");
                return;
            } else if (quantity < product.getMinSellQuantity()) {
                System.out.println("Quantity less than minimum sell quantity.");
                return;
            }
            // Update quantity after sale
            productDAO.updateProductQuantity(productId, product.getQuantityAvailable() - quantity);
        } else if ("Buy".equalsIgnoreCase(transactionType)) {
            // Update quantity after purchase
            productDAO.updateProductQuantity(productId, product.getQuantityAvailable() + quantity);
        } else {
            System.out.println("Invalid transaction type.");
            return;
        }

        // Calculate total cost and create transaction record
        double totalCost = product.getPrice() * quantity;
        Transaction transaction = new Transaction(0, userId, productId, product.getProductName(),
                                                  transactionType, quantity, product.getPrice(),
                                                  totalCost);
        transactionDAO.addTransaction(transaction);
        System.out.println("Transaction successful: " + transactionType + " - Total Cost: " + totalCost);
        
        // Log transaction to file
        TransactionLogger.logTransaction(transaction);
    }

    // Shows all transactions for a specific user, ordered by latest first
    public void showTransactionHistory(int userId) {
        List<Transaction> transactions = transactionDAO.getTransactionsByUserId(userId);
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println("Product ID: " + transaction.getProductId() +
                               ", Name: " + transaction.getProductName() +
                               ", Type: " + transaction.getTransactionType() +
                               ", Quantity: " + transaction.getQuantity() +
                               ", Cost per unit: " + transaction.getCost() +
                               ", Total Cost: " + transaction.getTotalCost());
        }
    }
}

