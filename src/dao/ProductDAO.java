package dao;

import java.util.List;

import model.Product;

public interface ProductDAO {
	void addProduct(Product product);
    Product findProductById(int productId);
    List<Product> getInventory();
    void updateProductQuantity(int productId, int quantity);
    void updateProduct(Product product);  // new method
    void deleteProduct(int productId);    // new method
}
