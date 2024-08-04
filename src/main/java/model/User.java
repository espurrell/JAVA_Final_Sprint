package model; // Package for the class

public class User { // Class for the User object
    private int userId;
    private String username;
    private String password;
    private String email;
    private String role;

    // Constructors

    public User() {

    }

    // Parameterized constructor
    public User(int userId, String username, String password, String email, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Methods to handle actions based on user roles
    public void handleBuyer() {
        // Logic for buyer actions, e.g., browsing products, adding to cart, etc.
        System.out.println("Handling buyer actions for " + username);
        // Implement buyer-specific functionality here
    }

    public void handleSeller() {
        // Logic for seller actions, e.g., adding products, viewing sales, etc.
        System.out.println("Handling seller actions for " + username);
        // Implement seller-specific functionality here
    }

    public void handleAdmin() {
        // Logic for admin actions, e.g., managing users, products, etc.
        System.out.println("Handling admin actions for " + username);
        // Implement admin-specific functionality here
    }

    // Optional: Override toString for debugging

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
