import model.*;
import service.*;
import dao.*;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class App { // Main class
    private static Connection connection;

    public static void main(String[] args) { // Main method
        // Connect to the database
        connectToDatabase();
        Scanner scanner = new Scanner(System.in);
        // Create a new UserService object
        UserService userService = new UserService(new UserDAO(connection));
        ProductService productService = new ProductService(new ProductDAO(connection));
        // Call the mainMenu method
        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Registration logic
                    registerUser(scanner, userService);
                    break;
                case 2:
                    // Login logic
                    User user = loginUser(scanner, userService);
                    if (user != null) {
                        if (user.getRole().equals("buyer")) {
                            buyerMenu(scanner, productService);
                        } else if (user.getRole().equals("seller")) {
                            sellerMenu(scanner, productService, user);
                        } else if (user.getRole().equals("admin")) {
                            adminMenu(scanner, userService, productService);
                        }
                    } else {
                        System.out.println("Invalid credentials!");
                    }
                    break;
                case 3:
                    closeConnection();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    // Method to connect to the database
    private static void registerUser(Scanner scanner, UserService userService) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        System.out.println("Enter email:");
        String email = scanner.nextLine();
        System.out.println("Enter role (buyer, seller, admin):");
        String role = scanner.nextLine();

        // Create a new User object based on the role
        User user;
        switch (role) {
            case "buyer":
                user = new Buyer();
                break;
            case "seller":
                user = new Seller();
                break;
            case "admin":
                user = new Admin();
                break;
            default:
                System.out.println("Invalid role!");
                return;
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        userService.registerUser(user);
        System.out.println("User registered successfully!");
    }

// Method to close the connection
private static User loginUser(Scanner scanner, UserService userService) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner
