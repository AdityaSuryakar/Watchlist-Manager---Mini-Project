package com.project;

import java.sql.*;
import javax.swing.JOptionPane;

public class DBConnection {
    // Ensure these match your MySQL configuration
    private static final String URL = "jdbc:mysql://localhost:3306/cineverse";
    private static final String USER = "root";
    private static final String PASSWORD = "manager";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Database Connection Failed! Check Server/Credentials and MySQL Connector.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }
}