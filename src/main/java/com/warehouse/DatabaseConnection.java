package com.warehouse;
import com.warehouse.DatabaseConnection; // Adjust the path if needed

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12741091";
    private static final String USER = "sql12741091";
    private static final String PASSWORD = "krzpU6TWyN";

    // Static method to get a connection to the database
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found. Make sure you have added it to your library.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Connection to database failed. Please check your URL, user, and password.");
            e.printStackTrace();
        }
        return conn;
    }

    // Method to test the database connection
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection established successfully!");
            } else {
                System.out.println("Failed to establish connection.");
            }
        } catch (SQLException e) {
            System.err.println("An error occurred while trying to close the connection.");
            e.printStackTrace();
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        testConnection(); // Call the test method
    }
}
