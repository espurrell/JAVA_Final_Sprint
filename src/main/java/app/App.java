package app;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import dao.UserDAO;
import menus.ConsoleMenu;
import service.UserService;
import dao.ProductDAO;
import service.ProductService;


public class App { // Main class
    private static Connection connection;

    public static void main(String[] args) { // Main method
        // Connect to the database
        connectToDatabase();
        Scanner scanner = new Scanner(System.in);

        // Create DAO and Service instances
        UserDAO userDAO = new UserDAO(connection);
        UserService userService = new UserService(userDAO);

        ProductDAO productDAO = new ProductDAO(connection);
        ProductService productService = new ProductService(productDAO);

        // Create and start the menu
        ConsoleMenu consoleMenu = new ConsoleMenu(scanner, userService, productService);
        consoleMenu.start();

        // Close resources
        closeConnection();
        scanner.close();
    }

    private static void connectToDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
            System.exit(1);
        }
    }

    private static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the connection: " + e.getMessage());
            }
        }
    }
}