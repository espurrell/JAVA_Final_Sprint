package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;
import model.User;
import model.Buyer;
import model.Seller;

public class UserDAO {
    
    private Connection connection;

    public UserDAO(Connection connection2) {
        this.connection = connection2;
    }

    public void saveUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, hashedPassword);
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
                    if ("buyer".equals(role)) {
                        return new Buyer(
                                1,
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("email"));
                    } else if ("seller".equals(role)) {
                        return new Seller(
                                2,
                                resultSet.getString("username"),
                                resultSet.getString("password"),
                                resultSet.getString("email"));
                    }
                }
            }
        }
        return null;
    }

    // Additional methods for update and delete user

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET password = ?, email = ? WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getUsername());
            statement.executeUpdate();
        }
    }

    public void deleteUser(String username) throws SQLException {
        String query = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }

}
