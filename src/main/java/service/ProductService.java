package service;

import dao.ProductDAO;
import model.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private ProductDAO productDAO;
    private Product[] products;

    public ProductService() {
        this.productDAO = new ProductDAO(null);
    }

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) {
        productDAO.addProduct(product);
    }

    public Product getProduct(int itemId) {
        return productDAO.getProduct(itemId);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int itemId) {
        productDAO.deleteProduct(itemId);
    }

    public List<Product> getProductsBySeller(int sellerId) {
        return productDAO.getProductsBySeller(sellerId);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Method to fetch a single product by ID
    public Product getItemById(int itemId) {
        return productDAO.getProduct(itemId);
    }

    // Method to search for products by name
    public Iterable<Product> searchProducts(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getItemName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

}
