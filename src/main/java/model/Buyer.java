package model;

public class Buyer extends User {
    // Additional attributes specific to a Buyer
    private String shippingAddress;
    private String paymentMethod;

    // Constructor
    public Buyer(String username, String password, String email, String shippingAddress, String paymentMethod) {
        super(username, password, email, "buyer");
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
    }

    // Getters and Setters
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Additional methods for Buyer
    public void browseProducts() {
        // Logic to browse products
    }

    public void purchaseProduct(int productId) {
        // Logic to purchase a product
    }
}

