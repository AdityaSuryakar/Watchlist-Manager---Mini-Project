package com.project;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class YourWatchlist extends JPanel {
    private JTextArea listArea;

    public YourWatchlist() {
        setLayout(new BorderLayout());
        setOpaque(false); // Panel transparent

        JLabel title = new JLabel("📺 Your Watchlist", JLabel.LEFT);
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        add(title, BorderLayout.NORTH);

        listArea = new JTextArea(10, 40);
        listArea.setFont(new Font("Poppins", Font.PLAIN, 16));
        listArea.setEditable(false);
        listArea.setForeground(Color.WHITE);
        
        // FIX: Use a semi-opaque background only for the JTextArea content 
        // to ensure text is visible over the complex background image.
        listArea.setOpaque(true); 
        listArea.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black 
        
        JScrollPane scrollPane = new JScrollPane(listArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        loadWatchlist();
    }

    public void loadWatchlist() {
        listArea.setText("Loading watchlist...\n");
        // ... (DB logic remains the same)
        // ...
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT title FROM watchlist ORDER BY added_on DESC")) {
            
            listArea.setText(""); 
            boolean hasItems = false;
            while (rs.next()) {
                listArea.append("🎬 " + rs.getString("title") + "\n");
                hasItems = true;
            }
            if (!hasItems) {
                 listArea.setText("No movies added yet.");
            }
        } catch (Exception e) {
            listArea.setText("Error loading watchlist.");
            e.printStackTrace();
        }
    }
}