package com.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ClearAll extends JPanel {
    public ClearAll() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setBackground(new Color(90, 0, 120)); // Match the header color for separation

        JButton clearBtn = new JButton("🗑️ Clear All from Watchlist");
        clearBtn.setBackground(Color.RED);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Poppins", Font.BOLD, 18));
        add(clearBtn);

        clearBtn.addActionListener(e -> clearWatchlist());
    }

    private void clearWatchlist() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to clear your entire watchlist?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DBConnection.getConnection();
                 Statement st = conn.createStatement()) {
                
                st.executeUpdate("DELETE FROM watchlist");
                JOptionPane.showMessageDialog(this, "Watchlist cleared!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Immediately refresh the YourWatchlist panel after clearing
                Container parent = getParent();
                if (parent != null) {
                    // Navigate up to the Main JFrame's content pane and find YourWatchlist
                    for (Component comp : ((Container)parent.getParent()).getComponents()) {
                        if (comp instanceof JPanel) {
                            for (Component innerComp : ((JPanel)comp).getComponents()) {
                                if (innerComp instanceof YourWatchlist) {
                                    ((YourWatchlist) innerComp).loadWatchlist();
                                    return;
                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to clear watchlist: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}