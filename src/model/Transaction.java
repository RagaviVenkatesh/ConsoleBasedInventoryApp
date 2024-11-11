package model;

public class Transaction {
	private int transactionId;
    private int userId;
    private int productId;
    private String productName;
    private String transactionType; // "Buy" or "Sell"
    private int quantity;
    private double cost;
    private double totalCost;

    // Constructor
    public Transaction(int transactionId, int userId, int productId, String productName,
                       String transactionType, int quantity,double cost, double totalCost) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.productId = productId;
        this.productName = productName;
        this.transactionType = transactionType;
        this.quantity = quantity;
        this.cost = cost;
        this.totalCost = totalCost;
    }

    public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	// Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }

}
