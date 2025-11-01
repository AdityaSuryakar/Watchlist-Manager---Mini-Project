package com.project;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddToWatchlist extends JPanel {
    private JTextField titleField;
    private Main mainFrame; // Reference to the main frame

    // Constructor accepts Main reference
    public AddToWatchlist(Main mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout()); 
        // Background removed to show image, but opacity set to false
        setOpaque(false); 

        // Title on top left
        JLabel label = new JLabel("  Add to Watchlist", JLabel.LEFT);
        label.setFont(new Font("Poppins", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 0));
        add(label, BorderLayout.NORTH);
        
        // Input and Buttons in the center area
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        inputPanel.setOpaque(false); // Panel transparent
        
        titleField = new JTextField(30); 
        titleField.setFont(new Font("Poppins", Font.PLAIN, 16));
        titleField.setToolTipText("Enter movie or web series name...");

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Poppins", Font.BOLD, 16));
        addButton.setBackground(new Color(255, 0, 150)); 
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        
        JButton clearBtn = new JButton("Clear All");
        clearBtn.setFont(new Font("Poppins", Font.BOLD, 16));
        clearBtn.setBackground(new Color(255, 0, 150)); 
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFocusPainted(false);
        
        inputPanel.add(titleField);
        inputPanel.add(addButton);
        inputPanel.add(clearBtn);
        
        add(inputPanel, BorderLayout.CENTER);

        addButton.addActionListener(e -> addMovie());
        clearBtn.addActionListener(e -> clearWatchlist());
    }

    private void addMovie() {
        String title = titleField.getText().trim();
        // ... (DB logic remains the same)
        // ...
        String sql = "INSERT INTO watchlist (title, added_on) VALUES (?, NOW())";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, title);
            ps.executeUpdate();
            titleField.setText("");
            
            // Use the global refresh method
            mainFrame.refreshWatchlist();
            JOptionPane.showMessageDialog(this, "'" + title + "' added to watchlist!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void clearWatchlist() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear your entire watchlist?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getConnection();
                 Statement st = conn.createStatement()) {
                
                st.executeUpdate("DELETE FROM watchlist");
                JOptionPane.showMessageDialog(this, "Watchlist cleared!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Use the global refresh method
                mainFrame.refreshWatchlist(); 

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to clear watchlist: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}