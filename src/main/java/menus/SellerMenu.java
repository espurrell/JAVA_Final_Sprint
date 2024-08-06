package menus;

import service.ProductService;
import service.UserService;

import java.sql.SQLException;
import java.util.Scanner;

import model.Product;
import model.Seller;
import model.User;

public class SellerMenu {
    private Scanner scanner;
    private ProductService productService;
    private Seller seller;

    public SellerMenu(Scanner scanner, Seller seller, ProductService productService) {
        this.scanner = scanner;
        this.seller = seller;
        this.productService = productService;
    }

    // Static method to display the menu
    public static void displayMenu(Scanner scanner, Seller seller, ProductService productService) {
        SellerMenu menu = new SellerMenu(scanner, seller, productService);
        menu.start();
    }

    public void start() 
    {
        while (true) {
            System.out.println("1. Add a product");
            System.out.println("2. View all Seller products");
            System.out.println("3. Update a product");
            System.out.println("4. Delete a product");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    viewProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private void addProduct() {
        System.out.println("Enter product name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter product type:");
        String itemType = scanner.nextLine();
        System.out.println("Enter product description:");
        String itemDescription = scanner.nextLine();

        Product product = new Product(0, itemName, itemType, itemDescription, seller.getUserId());
        try {
            productService.addProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void viewProducts() {
        productService.getProductsBySeller(seller.getUserId()).forEach(System.out::println);
    }

    private void updateProduct() {
        System.out.println("Enter product ID:");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter new product name:");
        String itemName = scanner.nextLine();
        System.out.println("Enter new product type:");
        String itemType = scanner.nextLine();
        System.out.println("Enter new product description:");
        String itemDescription = scanner.nextLine();

        Product product = new Product(itemId, itemName, itemType, itemDescription, seller.getUserId());
        try {
            productService.updateProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct() {
        System.out.println("Enter product ID:");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {
            productService.deleteProduct(itemId);
            System.out.println("Product deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
