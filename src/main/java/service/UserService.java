package service;

import java.sql.Connection;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDAO;
import model.User;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) throws SQLException {
        try {
        // Password encryption using Bcrypt
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.saveUser(user);
    } catch (SQLException e) {
        System.out.println("Error registering user: " + e.getMessage());
    }
    }

    public User loginUser(String username, String password) throws SQLException {
        try {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
    } catch (SQLException e) {
        System.out.println("Error logging in: " + e.getMessage());
    }
        return null;
    }
}
