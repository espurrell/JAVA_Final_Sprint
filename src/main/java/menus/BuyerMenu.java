package menus;

import service.ProductService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import model.Product;

public class BuyerMenu {
    private Scanner scanner;
    private ProductService productService;

    public BuyerMenu(Scanner scanner, ProductService productService) {
        this.scanner = scanner;
        this.productService = productService;
    }

    // Static method to display the menu
    public static void displayMenu(Scanner scanner, ProductService productService) {
        BuyerMenu menu = new BuyerMenu(scanner, productService);
        menu.start();
    }

    public void start() {
        System.out.println("Welcome, buyer!");
        System.out.println("1. View products");
        System.out.println("2. Search products");
        System.out.println("3. Exit");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                viewProducts();
                break;
            case 2:
                searchProducts();

                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }

    private void viewProducts() {
        productService.getAllProducts().forEach(System.out::println);
    }

    private void searchProducts() {
        System.out.println("Enter product name:");
        String itemName = scanner.nextLine();

            try {
                List<Product> products = productService.searchProducts(itemName);
                if (products.isEmpty()) {
                    System.out.println("No products found");
                } else {
                    products.forEach(System.out::println);
                }
            } catch (SQLException e) {
                System.out.println("Error searching for products: " + e.getMessage());
            }
        }
        // Close scanner in a shutdown hook or in your application's end
        @Override
        protected void finalize() throws Throwable {
        scanner.close(); // Don't forget to close the scanner when done
    }
}