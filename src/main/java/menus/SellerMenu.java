package menus;

import service.ProductService;
import java.util.Scanner;
import model.Seller;
import model.User;

public class SellerMenu {
    private Scanner scanner;
    private ProductService productService;
    private Seller seller;

    public SellerMenu(Scanner scanner, ProductService productService, Seller seller) {
        this.scanner = scanner;
        this.productService = productService;
        this.seller = seller;
    }

    public void start() {
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

        productService.addProduct(itemName, itemType, itemDescription, seller.getUserId());
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

        productService.updateProduct(itemId, itemName, itemType, itemDescription, seller.getUserId());
    }

    private void deleteProduct() {
        System.out.println("Enter product ID:");
        int itemId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        productService.deleteProduct(itemId, seller.getUserId());
    }
}
