



import java.util.ArrayList;
import java.util.List;

public class Seller extends User {
    // Additional attributes specific to a Seller
    private String storeName;
    private List<Product> products;

    // Constructor
    public Seller(String username, String password, String email) {
        super(username, password, email, "seller");
        this.storeName = "Vintique";
        this.products = new ArrayList<>();
    }

    // Getters and Setters
    public String getStoreName() {
        return storeName;
    }

    // No setter for storeName as it's fixed to "Vintique"

    public List<Product> getProducts() {
        return products;
    }

    // Additional methods for Seller
    public void addProduct(Product product) {
        // Logic to add a product
        products.add(product);
    }

    public void removeProduct(int productId) {
        // Logic to remove a product
        products.removeIf(product -> product.getId() == productId);
    }

    public void updateProduct(Product product) {
        // Logic to update a product
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                products.set(i, product);
                break;
            }
        }
    }
}
