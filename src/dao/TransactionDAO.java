package dao;

import java.util.List;

import model.Transaction;

public interface TransactionDAO {
	void addTransaction(Transaction transaction);
    List<Transaction> getTransactionsByUserId(int userId);
    List<Transaction> getAllTransactions();
    double calculateTotalCost();
}
