package service;

import dao.ProductDAO;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO;
    private Connection connection;
    private Product[] products;

    public ProductService(Connection connection) {
        this.connection = connection;
        this.productDAO = new ProductDAO(connection);
    }

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) throws SQLException {
        productDAO.addProduct(product);
    }

    public Product getProduct(int itemId) throws SQLException {
        return productDAO.getProduct(itemId);
    }

    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int itemId) throws SQLException {
        productDAO.deleteProduct(itemId);
    }

    public List<Product> getProductsBySeller(int sellerId) {
        return productDAO.getProductsBySeller(sellerId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Method to fetch a single product by ID
    public Product getItemById(int itemId) {
        return productDAO.getProduct(itemId);
    }

    // Method to search for products by name
    public List<Product> searchProducts(String itemName) throws SQLException {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be null or empty");
        }

        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products WHERE itemName LIKE ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {statement.setString(1, "%" + itemName + "%");
            try (ResultSet resultsSet = statement.executeQuery()) {
                while (resultsSet.next()) {
                    Product product = new Product(
                            resultsSet.getInt("itemId"),
                            resultsSet.getString("itemName"),
                            resultsSet.getString("itemType"),
                            resultsSet.getString("itemDescription"),
                            resultsSet.getInt("sellerId")
                    );
                    products.add(product);
                }
        }
        
    }
    return products;

}}
