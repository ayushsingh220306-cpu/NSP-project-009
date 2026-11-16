package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection provides centralized JDBC database connectivity
 * for the Student Scholarship & Fee Concession System.
 * 
 * Student: Ayush Singh (PIET25CR009)
 * Course: B.Tech II Year - III Semester Minor Project (NSP)
 */
public class DBConnection {

    // Database connection constants
    private static final String DB_URL = "jdbc:mysql://localhost:3306/scholarship_fee_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ayush@0308";

    // Static block to safely register the MySQL JDBC Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("CRITICAL: MySQL JDBC Driver not found in classpath.");
            e.printStackTrace();
        }
    }

    /**
     * Obtains a live connection to the MySQL database.
     * 
     * @return java.sql.Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    /**
     * Utility method to check whether database connection is active.
     * 
     * @return true if database responds, false otherwise
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }
}
