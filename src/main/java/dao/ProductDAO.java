package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    // Constructor accepting Connection object
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a new product
    public void addProduct(Product product) throws SQLException{
        String query = "INSERT INTO Products (item_name, item_type, item_description, seller_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getItemName());
            stmt.setString(2, product.getItemType());
            stmt.setString(3, product.getItemDescription());
            stmt.setInt(4, product.getSellerId());
            stmt.executeUpdate();
        } 
    }

    // Get a product by ID
    public Product getProduct(int itemId) throws SQLException {
        String query = "SELECT * FROM Products WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_type"),
                        rs.getString("item_description"),
                        rs.getInt("seller_id"));
            }
        }
        return null;
    }

    // Update a product
    public void updateProduct(Product product) throws SQLException {
        String query = "UPDATE Products SET item_name = ?, item_type = ?, item_description = ?, seller_id = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getItemName());
            stmt.setString(2, product.getItemType());
            stmt.setString(3, product.getItemDescription());
            stmt.setInt(4, product.getSellerId());
            stmt.setInt(5, product.getItemId());
            stmt.executeUpdate();
        }
    }

    // Delete a product
    public void deleteProduct(int itemId) throws SQLException {
        String query = "DELETE FROM Products WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        }
    }

    // Get all products for a specific seller
    public List<Product> getProductsBySeller(int sellerId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM Products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sellerId);
            try (ResultSet resultSet = stmt.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("item_id"),
                        resultSet.getString("item_name"),
                        resultSet.getString("item_type"),
                        resultSet.getString("item_description"),
                        resultSet.getInt("seller_id")
                        );
                products.add(product);
            }
        } 
    } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Get all products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Statement stmt = connection.createStatement()) {
            try (ResultSet resultSet = stmt.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = new Product (
                    resultSet.getInt("item_id"),
                    resultSet.getString("item_name"),
                    resultSet.getString("item_type"),
                    resultSet.getString("item_description"),
                    resultSet.getInt("seller_id"));
                    products.add(product);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<Product> searchProductsByName(String itemName) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE item_name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "%" + itemName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_type"),
                        rs.getString("item_description"),
                        rs.getInt("seller_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
