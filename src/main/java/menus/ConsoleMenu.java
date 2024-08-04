package menus;

import service.UserService;
import service.ProductService;
import model.Admin;
import model.Buyer;
import model.Seller;
import model.User;

import java.sql.SQLException;
import java.util.Scanner;

import org.mindrot.jbcrypt.BCrypt;

public class ConsoleMenu {
    private Scanner scanner;
    private UserService userService;
    private ProductService productService; 
    
    public ConsoleMenu(Scanner scanner, UserService userService, ProductService productService) {
        this.scanner = scanner;
        this.userService = userService;
        this.productService = productService;
    }

    public void start() {
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
            }   
        }
    }

    private void registerUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine(); // Add missing line to capture password
        System.out.println("Enter role (buyer, seller, admin):");
        String role = scanner.nextLine();

        User user = createUserByRole(username, password, email, role); // Fix constructor arguments
        if (user != null) {
            try {
                userService.registerUser(user); // Calls the UserService to save user
                System.out.println("User registered successfully!");
            } catch (SQLException e) {
                System.out.println("Error registering user: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid role!");
        }
    }

    private User createUserByRole(String username, String password, String email, String role) {
        switch (role.toLowerCase()) {
            case "buyer":
                return new Buyer(0, username, password, email);
            case "seller":
                return new Seller(0, username, password, email);
            case "admin":
                return new Admin(0, username, password, email);
            default:
                return null;
        }
    }

    private void loginUser() {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try {
            User user = userService.loginUser(username, password); // Moved logic to UserService
            if (user != null) {
                System.out.println("Login successful!");
                System.out.println("Welcome, " + user.getUsername() + "!");
                handleUserRole(user); // Handle based on role after login
            } else {
                System.out.println("Invalid username or password!");
            }
        } catch (SQLException e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
    }

    private void handleUserRole(User user) {
        switch (user.getRole().toLowerCase()) {
            case "buyer":
                handleBuyer(user);
                break;
            case "seller":
                handleSeller(user);
                break;
            case "admin":
                handleAdmin(user);
                break;
            default:
                System.out.println("Invalid role!");
        }
    }

    private void handleBuyer(User user) {
        System.out.println("Handling buyer actions for " + user.getUsername());
        // Add buyer-specific actions here
    }

    private void handleSeller(User user) {
        System.out.println("Handling seller actions for " + user.getUsername());
        // Add seller-specific actions here
    }

    private void handleAdmin(User user) {
        System.out.println("Handling admin actions for " + user.getUsername());
        // Add admin-specific actions here
    }
}
