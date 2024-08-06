package model;

import service.ProductService;
import model.Product;
import java.util.List;
import java.sql.Connection;

// Inherit from the User class
public class Buyer extends User {

    private ProductService productService;

    // Default constructor
    public Buyer(Connection connection) {
        super(); // Call the parent class constructor
        this.setRole("buyer");
        this.productService = new ProductService(connection); // Instantiate ProductService
    }

    // Parameterized constructor
    public Buyer(int userId, String username, String password, String email, Connection connection) {
        super(userId, username, password, email, "buyer");
        this.productService = new ProductService(connection); // Instantiate ProductService
    }

    // Method to browse products
    public void browseProducts() {

        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Available products:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Method to view product details
    public void viewProductDetails(int productId) {
        // Fetch the product details using ProductService
        Product product = productService.getItemById(productId);

        // Check if the product exists
        if (product != null) {
            System.out.println("Product Details:");
            System.out.println("ID: " + product.getItemId());
            System.out.println("Name: " + product.getItemName());
            System.out.println("Seller ID: " + product.getSellerId());
        } else {
            System.out.println("No product found with ID: " + productId);
        }
    }

    // Optional: Override methods from User class if needed
    @Override
    public String toString() {
        return "Buyer{" +
                "userId=" + getUserId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}
