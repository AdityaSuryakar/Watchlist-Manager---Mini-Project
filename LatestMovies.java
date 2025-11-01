package com.project;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LatestMovies extends JPanel {
    private JTextArea listArea;
    private String currentCategory;

    // Constructor now accepts the category to load
    public LatestMovies(String category) {
        this.currentCategory = category;
        setLayout(new BorderLayout());
        setOpaque(false); // Panel transparent

        // Dynamically set title based on category
        String titleText = "🔥 " + (category.equals("All") ? "Search Results" : category + " Movies");
        JLabel title = new JLabel(titleText, JLabel.LEFT);
        title.setFont(new Font("Poppins", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        add(title, BorderLayout.NORTH);

        listArea = new JTextArea(8, 30);
        listArea.setFont(new Font("Poppins", Font.PLAIN, 16));
        listArea.setEditable(false);
        listArea.setForeground(Color.WHITE);
        listArea.setOpaque(true);
        listArea.setBackground(new Color(0, 0, 0, 150)); // Semi-transparent black 
        
        JScrollPane scrollPane = new JScrollPane(listArea);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        loadMoviesByCategory();
    }

    private void loadMoviesByCategory() {
        listArea.setText("Loading " + currentCategory + " movies...");
        
        String sql;
        if (currentCategory.equals("All")) {
            // Placeholder for 'All Recs' - could show top 10 overall
             sql = "SELECT title, rating FROM movies ORDER BY rating DESC LIMIT 10"; 
        } else if (currentCategory.equals("Featured")) {
             // Placeholder logic - just show Latest for now
             sql = "SELECT title, rating FROM movies WHERE category = 'Latest' ORDER BY rating DESC LIMIT 10";
        } else {
             // Fetches movies where category matches the button text
             sql = "SELECT title, rating FROM movies WHERE category = ? ORDER BY rating DESC LIMIT 10"; 
        }
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            if (!currentCategory.equals("All") && !currentCategory.equals("Featured")) {
                ps.setString(1, currentCategory);
            }
            ResultSet rs = ps.executeQuery();
            
            listArea.setText("");
            boolean found = false;
            while (rs.next()) {
                listArea.append("🎬 " + rs.getString("title") + " - ⭐ " + rs.getFloat("rating") + "\n");
                found = true;
            }
            if (!found) {
                listArea.setText("No '" + currentCategory + "' movies found.");
            }
        } catch (Exception e) {
            listArea.setText("Error loading movies.");
            e.printStackTrace();
        }
    }
}
