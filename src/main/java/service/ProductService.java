package service;

import dao.ProductDAO;
import model.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService(Connection connection) {
        this.productDAO = new ProductDAO(connection);
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public Product getProduct(int itemId) {
        return productDAO.getProduct(itemId);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int itemId) {
        productDAO.deleteProduct(itemId);
    }

    public List<Product> getProductsBySeller(int sellerId) {
        return productDAO.getProductsBySeller(sellerId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }
}
