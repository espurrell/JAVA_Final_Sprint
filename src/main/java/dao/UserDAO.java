package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Buyer;
import model.Seller;

public class UserDAO {

    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword()); // Assume the password is already hashed
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.executeUpdate();
        }
    }

    public User getUserByUsername(String username) throws SQLException {
            String query = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String role = resultSet.getString("role");
                        int userId = resultSet.getInt("user_id");
                        String userPassword = resultSet.getString("password");
                        String email = resultSet.getString("email");

                        if ("buyer".equalsIgnoreCase(role)) {
                            return new Buyer(userId, username, userPassword, email, connection);
                        } else if ("seller".equalsIgnoreCase(role)) {
                            return new Seller(userId, username, userPassword, email);
                        } else {
                            return new User(userId, username, userPassword, email, role);
                        }
                    }
                }
            }
            return null;
        }

    public void updateUser(User user) throws SQLException {
            String query = "UPDATE users SET password = ?, email = ? WHERE username = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, user.getPassword()); // Assume the password is already hashed
                statement.setString(2, user.getEmail());
                statement.setString(3, user.getUsername());
                statement.executeUpdate();
            }
        }

    public void deleteUser(int userId) throws SQLException {
            String query = "DELETE FROM users WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                int rowsAffected = statement.executeUpdate();
        if (rowsAffected == 0) {
            throw new SQLException("No user found with ID: " + userId);
        }
            }
        }

        public User getUserById(int userId) throws SQLException {
            String query = "SELECT * FROM users WHERE user_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        String role = resultSet.getString("role");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        String email = resultSet.getString("email");
    
                        if ("buyer".equalsIgnoreCase(role)) {
                            return new Buyer(userId, username, password, email, connection);
                        } else if ("seller".equalsIgnoreCase(role)) {
                            return new Seller(userId, username, password, email);
                        } else {
                            return new User(userId, username, password, email, role);
                        }
                    }
                }
            }
         return null;
    }

     public List<User> getAllUsers() throws SQLException { List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
         try (PreparedStatement statement = connection.prepareStatement(query);
              ResultSet resultSet = statement.executeQuery()) {
             while (resultSet.next()) {
                 String role = resultSet.getString("role");
                 User user;
                 if ("buyer".equalsIgnoreCase(role)) {
                     user = new Buyer(
                             resultSet.getInt("user_id"),
                             resultSet.getString("username"),
                             resultSet.getString("password"),
                             resultSet.getString("email"), connection);
                 } else if ("seller".equalsIgnoreCase(role)) {
                     user = new Seller(
                             resultSet.getInt("user_id"),
                             resultSet.getString("username"),
                             resultSet.getString("password"),
                             resultSet.getString("email"));
                 } else {
                     user = new User(
                             resultSet.getInt("user_id"),
                             resultSet.getString("username"),
                             resultSet.getString("password"),
                             resultSet.getString("email"),
                             resultSet.getString("role"));
                 }
                 users.add(user);
             }
         } 
         return users;
     }
     }
