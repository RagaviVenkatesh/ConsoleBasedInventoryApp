package service;

import java.util.List;

import dao.ProductDAO;
import model.Product;

public class AdminService {
    private ProductDAO productDAO;

    public AdminService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

 // Adds a new product to the inventory
    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    // Updates an existing product in the inventory
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    // Deletes a product from the inventory
    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }


    // Displays all products in the inventory
    public void displayInventory() {
        List<Product> products = productDAO.getInventory();
        System.out.println("Inventory Details:");
        for (Product product : products) {
            double totalCost = product.getQuantityAvailable() * product.getPrice();
            System.out.println("Product ID: " + product.getProductId() +
                               ", Name: " + product.getProductName() +
                               ", Quantity Available: " + product.getQuantityAvailable() +
                               ", Price: " + product.getPrice() +
                               ", Total Cost: " + totalCost);
        }
    }
}

