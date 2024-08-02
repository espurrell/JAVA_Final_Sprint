package menus;
import service.ProductService;
import java.util.Scanner;

public class BuyerMenu {
    private Scanner scanner;
    private ProductService productService;

    public BuyerMenu(Scanner scanner, ProductService productService) {
        this.scanner = scanner;
        this.productService = productService;
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
        String name = scanner.nextLine();
        productService.searchProducts(name).forEach(System.out::println);
    }
}