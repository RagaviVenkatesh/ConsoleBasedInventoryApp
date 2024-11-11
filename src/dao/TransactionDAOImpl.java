package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Transaction;
import utility.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO Transaction (productId, productName, transactionType, quantity, cost, totalCost, userId) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getProductId());
            stmt.setString(2, transaction.getProductName());
            stmt.setString(3, transaction.getTransactionType());
            stmt.setInt(4, transaction.getQuantity());
            stmt.setDouble(5, transaction.getCost());
            stmt.setDouble(6, transaction.getTotalCost());
            stmt.setInt(7, transaction.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction WHERE userId = ? ORDER BY transactionId DESC"; // Latest first
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("transactionId"),
                    rs.getInt("userId"),
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("transactionType"),
                    rs.getInt("quantity"),
                    rs.getDouble("cost"),
                    rs.getDouble("totalCost")
                    
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction ORDER BY transactionId DESC"; // Latest first
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("transactionId"),
                    rs.getInt("userId"),
                    rs.getInt("productId"),
                    rs.getString("productName"),
                    rs.getString("transactionType"),
                    rs.getInt("quantity"),
                    rs.getDouble("cost"),
                    rs.getDouble("totalCost")
                    
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactions;
    }
    
    @Override
    public double calculateTotalCost() {
        double totalCost = 0;
        String sql = "SELECT SUM(totalCost) AS totalCost FROM Transaction";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                totalCost = rs.getDouble("totalCost");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return totalCost;
    }
}

