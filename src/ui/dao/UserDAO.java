package dao;

import db.DBConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User authentication and profile management.
 */
public class UserDAO {

    /**
     * Authenticates user against credentials in database.
     * 
     * @param username user login username
     * @param password user login password
     * @return User object if credentials valid, null otherwise
     */
    public User authenticate(String username, String password) {
        String sql = "SELECT user_id, username, password, full_name, role, created_at FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("full_name"),
                            rs.getString("role"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("UserDAO.authenticate error: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates user password after old password verification.
     * 
     * @param userId user ID to update
     * @param newPassword new password string
     * @return true if updated successfully
     */
    public boolean changePassword(int userId, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setInt(2, userId);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("UserDAO.changePassword error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Verifies if the provided current password matches the user's stored password.
     */
    public boolean verifyCurrentPassword(int userId, String currentPassword) {
        String sql = "SELECT user_id FROM users WHERE user_id = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setString(2, currentPassword);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("UserDAO.verifyCurrentPassword error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves user by ID.
     */
    public User getUserById(int userId) {
        String sql = "SELECT user_id, username, password, full_name, role, created_at FROM users WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("full_name"),
                            rs.getString("role"),
                            rs.getTimestamp("created_at")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("UserDAO.getUserById error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Returns list of all registered users.
     */
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT user_id, username, password, full_name, role, created_at FROM users ORDER BY user_id";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at")
                ));
            }
        } catch (SQLException e) {
            System.err.println("UserDAO.getAllUsers error: " + e.getMessage());
        }
        return list;
    }
}
