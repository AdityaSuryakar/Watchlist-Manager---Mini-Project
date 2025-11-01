package com.project;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Main extends JFrame {
    
    private static final String BACKGROUND_URL = "https://www.theindianwire.com/wp-content/uploads/2023/01/OTT-1024x768.jpg";
    
    private JPanel centerPanel; 

    public Main() {
        setTitle("🎬 CineVerse Watchlist Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        JPanel mainContent = new JPanel(new BorderLayout());
        mainContent.setBackground(Color.BLACK);
        setContentPane(mainContent);
        
        loadRemoteImage(mainContent);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(true);
        topPanel.setBackground(new Color(90, 0, 120, 220)); 
        
        JLabel headerTitle = new JLabel("  CineVerse", JLabel.LEFT);
        headerTitle.setFont(new Font("Poppins", Font.BOLD, 30));
        headerTitle.setForeground(Color.WHITE);
        topPanel.add(headerTitle, BorderLayout.WEST);
        
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        navPanel.setOpaque(false);
        
        navPanel.add(createNavButton("✨", "Featured", new Color(255, 0, 150))); 
        navPanel.add(createNavButton("Latest", "Latest", new Color(0, 150, 255))); 
        navPanel.add(createNavButton("Top Rated", "Top Rated", new Color(0, 200, 100))); 
        navPanel.add(createNavButton("Web Series", "Web Series", new Color(255, 100, 0))); 
        navPanel.add(createNavButton("All Recs", "All", new Color(255, 255, 0))); 
        
        topPanel.add(navPanel, BorderLayout.EAST);
        mainContent.add(topPanel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 1, 15, 15)); 
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); 
        centerPanel.setOpaque(false); 

        centerPanel.add(new AddToWatchlist(this)); // Pass Main reference for global refresh
        centerPanel.add(new YourWatchlist());
        centerPanel.add(new LatestMovies("Latest")); // Default load on startup

        mainContent.add(centerPanel, BorderLayout.CENTER);
        
        JLabel footer = new JLabel("© 2025 CineVerse by Aditya | Yash | Amitabh| Made with ❤ and Creativity", JLabel.CENTER);
        footer.setFont(new Font("Poppins", Font.PLAIN, 14));
        footer.setForeground(new Color(255, 255, 255, 180));
        footer.setOpaque(true);
        footer.setBackground(new Color(0, 0, 0, 180)); 
        mainContent.add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }
    
    private JButton createNavButton(String text, String category, Color background) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Poppins", Font.BOLD, 14));
        btn.setForeground(Color.WHITE);
        btn.setBackground(background);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Attach action listener to change the LatestMovies panel content
        btn.addActionListener(e -> handleNavClick(category));
        
        return btn;
    }
    
    // Method to handle dynamic switching of content in the LatestMovies slot
    private void handleNavClick(String category) {
        // Find the index of the LatestMovies component (it's the 3rd component, index 2)
        centerPanel.remove(2); 
        
        // Add a new component (or reuse logic) based on the category
        if ("All".equals(category)) {
             // If "All" is clicked, show a standard search panel
             centerPanel.add(new SearchMovie()); 
        } else if ("Featured".equals(category)) {
             // Placeholder for Featured section (can be implemented as LatestMovies too)
             centerPanel.add(new LatestMovies("Latest")); 
        } else {
             // For "Latest", "Top Rated", "Web Series"
             centerPanel.add(new LatestMovies(category)); 
        }
        
        centerPanel.revalidate();
        centerPanel.repaint();
    }


    // Loads the image from the web and scales it to fit the frame
    private void loadRemoteImage(Container container) {
        new SwingWorker<ImageIcon, Void>() {
            @Override
            protected ImageIcon doInBackground() throws Exception {
                URL url = new URL(BACKGROUND_URL);
                return new ImageIcon(url);
            }

            @Override
            protected void done() {
                try {
                    ImageIcon originalIcon = get();
                    Image originalImage = originalIcon.getImage();
                    
                    // Create a scaled image icon that covers the entire frame
                    Image scaledImage = originalImage.getScaledInstance(
                        getWidth(), 
                        getHeight(), 
                        Image.SCALE_SMOOTH
                    );
                    ImageIcon scaledIcon = new ImageIcon(scaledImage);
                    
                    JLabel background = new JLabel(scaledIcon);
                    background.setLayout(new BorderLayout());
                    
                    // Transfer existing components to the new background pane
                    Container oldContent = container;
                    Component[] components = oldContent.getComponents();
                    
                    // Note: We transfer by index since BorderLayout constraints are hard to get
                    if (components.length == 3) {
                        background.add(components[0], BorderLayout.NORTH);
                        background.add(components[1], BorderLayout.CENTER);
                        background.add(components[2], BorderLayout.SOUTH);
                    }
                    
                    // Set the new content pane
                    setContentPane(background);
                    revalidate();
                    repaint();
                    
                } catch (Exception e) {
                    System.err.println("Failed to load or scale background image. Using solid background.");
                }
            }
        }.execute();
    }
    
    // Global refresh method (called by AddToWatchlist and ClearAll logic)
    public void refreshWatchlist() {
        for (Component comp : centerPanel.getComponents()) {
            if (comp instanceof YourWatchlist) {
                ((YourWatchlist) comp).loadWatchlist();
                break;
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}