package menus;

import service.ProductService;
import service.UserService;
import java.util.List;
import java.util.Scanner;
import model.User;
import model.Product;
import model.Seller;

public class AdminMenu {
    private Scanner scanner;
    private UserService userService;
    private ProductService productService;

    public AdminMenu(Scanner scanner, UserService userService, ProductService productService) {
        this.scanner = scanner;
        this.userService = userService;
        this.productService = productService;
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
        List<User> users = userService.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    // Method to delete a user
    private void deleteUser() {
        System.out.println("Enter the ID of the user to delete:");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        userService.deleteUser(userId);
        System.out.println("User deleted successfully.");
    }

    // Method to view all products with seller information
    private void viewAllProducts() {
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            // Fetch the seller's name based on sellerId
            User seller = userService.getUserById(product.getSellerId());
            String sellerName = (seller instanceof Seller) ? seller.getUsername() : "Unknown Seller";
            System.out.println(String.format("Product: %s, Type: %s, Description: %s, Seller: %s",
                product.getItemName(), product.getItemType(), product.getItemDescription(), sellerName));
        }
    }
}
