package dao;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(ProductDAO productDAO2) {
        this.connection = productDAO2;
    }

    // Add a new product
    public void addProduct(Product product) {
        String sql = "INSERT INTO Products (item_name, item_type, item_description, seller_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getItemName());
            stmt.setString(2, product.getItemType());
            stmt.setString(3, product.getItemDescription());
            stmt.setInt(4, product.getSellerId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get a product by ID
    public Product getProduct(int itemId) {
        String sql = "SELECT * FROM Products WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_type"),
                        rs.getString("item_description"),
                        rs.getInt("seller_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update a product
    public void updateProduct(Product product) {
        String sql = "UPDATE Products SET item_name = ?, item_type = ?, item_description = ?, seller_id = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getItemName());
            stmt.setString(2, product.getItemType());
            stmt.setString(3, product.getItemDescription());
            stmt.setInt(4, product.getSellerId());
            stmt.setInt(5, product.getItemId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a product
    public void deleteProduct(int itemId) {
        String sql = "DELETE FROM Products WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all products for a specific seller
    public List<Product> getProductsBySeller(int sellerId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products WHERE seller_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, sellerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_type"),
                        rs.getString("item_description"),
                        rs.getInt("seller_id")
                ));
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
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_type"),
                        rs.getString("item_description"),
                        rs.getInt("seller_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
