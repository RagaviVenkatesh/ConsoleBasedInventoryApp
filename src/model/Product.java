package model;

public class Product {
	private int productId;
    private String productName;
    private int minSellQuantity;
    private double price;
    private int quantityAvailable;

    // Constructor
    public Product(int productId, String productName, int minSellQuantity, double price, int quantityAvailable) {
        this.productId = productId;
        this.productName = productName;
        this.minSellQuantity = minSellQuantity;
        this.price = price;
        this.quantityAvailable = quantityAvailable;
    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getMinSellQuantity() { return minSellQuantity; }
    public void setMinSellQuantity(int minSellQuantity) { this.minSellQuantity = minSellQuantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getQuantityAvailable() { return quantityAvailable; }
    public void setQuantityAvailable(int quantityAvailable) { this.quantityAvailable = quantityAvailable; }
}
