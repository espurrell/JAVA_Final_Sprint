package model;

// Inherit from the User class
public class Admin extends User {

    // Default constructor
    public Admin() {
        super(); // Call the parent class constructor
        this.setRole("admin");
    }

    // Parameterized constructor
    public Admin(int userId, String username, String password, String email) {
        super(userId, username, password, email, "admin");
    }

    // Method to delete a user
    public void deleteUser(User user) {
        // Logic to delete user
        // This might involve interacting with the UserDAO to remove the user from the
        // database
        System.out.println("User " + user.getUsername() + " removed by Admin.");
    }

    // Method to view all users
    public void viewAllUsers() {
        // Logic to retrieve and display all users
        // This might involve calling a UserService method to fetch the data
        System.out.println("Admin viewing all users.");
    }

    // Method to view all products
    public void viewAllProducts() {
        // Logic to retrieve and display all products
        // This might involve calling a ProductService method to fetch the data
        System.out.println("Admin viewing all products.");
    }

    // Optional: Override methods from User class if needed
    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + getUserId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                '}';
    }
}