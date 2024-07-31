

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserDAO;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO2) {
        this.userDAO = new UserDAO(userDAO2);
    }

    public void registerUser(User user) throws SQLException {
        // Password encryption using Bcrypt
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.saveUser(user);
    }

    public User loginUser(String username, String password) throws SQLException {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}