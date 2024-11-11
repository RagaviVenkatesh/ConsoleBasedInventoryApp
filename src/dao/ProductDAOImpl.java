package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import utility.DBConnection;

public class ProductDAOImpl implements ProductDAO{
	@Override
    public void addProduct(Product product) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Product (productId, productName, minSellQuantity, price, quantityAvailable) VALUES (?, ?, ?, ?, ?)")) {
            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProductName());
            stmt.setInt(3, product.getMinSellQuantity());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantityAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product findProductById(int productId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Product WHERE productId = ?")) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("productId"), rs.getString("productName"),
                        rs.getInt("minSellQuantity"), rs.getDouble("price"), rs.getInt("quantityAvailable"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getInventory() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Product")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("productId"), rs.getString("productName"),
                        rs.getInt("minSellQuantity"), rs.getDouble("price"), rs.getInt("quantityAvailable")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void updateProductQuantity(int productId, int quantity) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Product SET quantityAvailable = ? WHERE productId = ?")) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateProduct(Product product) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE Product SET productName = ?, minSellQuantity = ?, price = ?, quantityAvailable = ? WHERE productId = ?"))
            		 {

            stmt.setString(1, product.getProductName());
            stmt.setInt(2, product.getMinSellQuantity());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantityAvailable());
            stmt.setInt(5, product.getProductId());
            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Product updated: " + product.getProductName());
            } else {
                System.out.println("Product with ID " + product.getProductId() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM Product WHERE productId = ?")) {

            stmt.setInt(1, productId);
            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Product deleted with ID: " + productId);
            } else {
                System.out.println("Product with ID " + productId + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
