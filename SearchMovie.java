package com.project;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SearchMovie extends JPanel {
    private JTextField searchField;
    private JTextArea resultArea;

    public SearchMovie() {
        setLayout(new BorderLayout());
        setOpaque(false); // No purple shade

        JLabel label = new JLabel("🔍 Search Movie/Webseries", JLabel.LEFT);
        label.setFont(new Font("Poppins", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        add(label, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inputPanel.setOpaque(false);
        
        searchField = new JTextField(30);
        searchField.setFont(new Font("Poppins", Font.PLAIN, 16));
        
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Poppins", Font.BOLD, 16));
        searchButton.setBackground(new Color(255, 0, 150)); 
        searchButton.setForeground(Color.WHITE);
        
        inputPanel.add(searchField);
        inputPanel.add(searchButton);
        add(inputPanel, BorderLayout.CENTER);

        resultArea = new JTextArea(8, 30);
        resultArea.setFont(new Font("Poppins", Font.PLAIN, 16));
        resultArea.setEditable(false);
        resultArea.setForeground(Color.WHITE);
        resultArea.setOpaque(true);
        resultArea.setBackground(new Color(0, 0, 0, 150)); 
        
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> searchMovie());
    }

    private void searchMovie() {
        resultArea.setText("");
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            resultArea.setText("Please enter a keyword to search.");
            return;
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT title, year, rating FROM movies WHERE title LIKE ?")) {
            
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            
            boolean found = false;
            while (rs.next()) {
                // Movie found in the database is shown on screen
                resultArea.append("🎥 " + rs.getString("title") + " (" + rs.getInt("year") + ") - ⭐ " + rs.getFloat("rating") + "\n");
                found = true;
            }
            if (!found) {
                resultArea.setText("No movies found matching '" + keyword + "' in the database.");
            }
        } catch (Exception e) {
            resultArea.setText("Error during search.");
            e.printStackTrace();
        }
    }
}