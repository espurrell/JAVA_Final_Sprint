package menus;

import service.ProductService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.User;
import model.Product;
import model.Seller;
import dao.UserDAO;

public class AdminMenu {
    private Scanner scanner;
    private UserService userService;
    private ProductService productService;

    public AdminMenu(Scanner scanner, UserService userService, ProductService productService) {
        this.scanner = scanner;
        this.userService = userService;
        this.productService = productService;
    }

    // Static method to display the menu
    public static void displayMenu(Scanner scanner, UserService userService, ProductService productService) {
        AdminMenu menu = new AdminMenu(scanner, userService, productService);
        menu.start();
    }

    public void start() {
        while (true) {
            System.out.println("1. View list of all users");
            System.out.println("2. Delete a user");
            System.out.println("3. View list of all products");
            System.out.println("4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    viewAllProducts();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Method to view all users
    private void viewAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        } } catch (SQLException e) {
            System.out.println("Error viewing users: " + e.getMessage());
        }
    }

    // Method to delete a user
    private void deleteUser() {
        System.out.println("Enter the ID of the user to delete:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        try {
        userService.deleteUser(userId);
        System.out.println("User deleted successfully.");
    } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    // Method to view all products with seller information
    private void viewAllProducts() {
        try {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            // Fetch the seller's name based on sellerId
            User seller = userService.getUserById(product.getSellerId());
            String sellerName = (seller instanceof Seller) ? seller.getUsername() : "Unknown Seller";
            System.out.println(String.format("Product: %s, Type: %s, Description: %s, Seller: %s",
                product.getItemName(), product.getItemType(), product.getItemDescription(), sellerName));
        } } catch (SQLException e) {
            System.out.println("Error viewing products: " + e.getMessage());
        }
    }
}
