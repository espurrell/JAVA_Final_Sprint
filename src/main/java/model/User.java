package model; // Package for the class

import java.util.Scanner;

import menus.AdminMenu;
import menus.BuyerMenu;
import menus.SellerMenu;
import service.ProductService;
import service.UserService;

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

    // 
    // 
    
    public void handleRole(ProductService productService, UserService userService) {
        switch (role.toLowerCase()) {
            case "buyer":
                BuyerMenu.displayMenu(new Scanner(System.in), productService);
                break;
            case "seller":
                SellerMenu.displayMenu(new Scanner(System.in), new Seller(userId, username, password, email), productService);
                break;
            case "admin":
                AdminMenu.displayMenu(new Scanner(System.in), userService, productService);
                break;
            default:
                System.out.println("Invalid role: " + role);
        }
    }

    // // Methods to handle actions based on user roles
    // public void handleBuyer(ProductService productService) {
    //     Scanner scanner = new Scanner(System.in);
            
    //             BuyerMenu.displayMenu(scanner, productService);
    //             System.out.println("Handling buyer actions for " + username);
    //         } 
        

    //     public void handleSeller(Seller seller, ProductService productService) {
    //         Scanner scanner = new Scanner(System.in);
    //         SellerMenu.displayMenu(scanner, seller, productService);
    //         System.out.println("Handling seller actions for " + username);
    //         }
        

    //     public void handleAdmin(UserService userService, ProductService productService) {
    //         Scanner scanner = new Scanner(System.in);
    //         AdminMenu.displayMenu(scanner, userService, productService);
    //         System.out.println("Handling admin actions for " + username);
    //         } 

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
