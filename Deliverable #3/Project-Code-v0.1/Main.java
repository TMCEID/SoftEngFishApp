
/*
 * Enhanced Main Panel with Role Selection and Custom Homescreens
 */

import java.awt.CardLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends javax.swing.JPanel {

        private CardLayout cardLayout;
        private final String INTRO_PANEL = "introPanel";
        private final String CHEF_PANEL = "chefPanel";
        private final String FISHERMAN_PANEL = "fishermanPanel";
        private final String FOOD_LOVER_PANEL = "foodLoverPanel";
        private final String ORDER_INGREDIENTS_PANEL = "orderIngredientsPanel";
        private final String LOG_FISH_PANEL = "logfishPanel";

        // User data
        private String userName = "";
        private String userRole = "";

        // Create panels for different sections
        private javax.swing.JPanel contentPanel;
        private javax.swing.JPanel introPanel;
        private javax.swing.JPanel chefPanel;
        private javax.swing.JPanel fishermanPanel;
        private javax.swing.JPanel foodLoverPanel;

        // Intro panel components
        private javax.swing.JTextField nameField;
        private javax.swing.JComboBox<String> roleComboBox;
        private javax.swing.JButton loginButton;
        private javax.swing.JLabel welcomeLabel;
        private javax.swing.JLabel logoLabel;

        /**
         * Creates new form Main
         */
        public Main() {
                initComponents();
                setupCardLayout();
        }

        /**
         * Sets up the card layout for panel switching
         */
        private void setupCardLayout() {
                // Initialize card layout and content panel
                cardLayout = new CardLayout();
                contentPanel = new javax.swing.JPanel();
                contentPanel.setLayout(cardLayout);

                // Create intro panel with role selection
                createIntroPanel();

                // Create role-specific panels
                createChefPanel();
                createFishermanPanel();
                createFoodLoverPanel();

                // Add existing panels if they exist
                try {
                        OrderIngredientsPanel orderIngredientsPanel = new OrderIngredientsPanel(cardLayout,
                                        contentPanel);
                        contentPanel.add(orderIngredientsPanel, ORDER_INGREDIENTS_PANEL);
                } catch (Exception e) {
                        System.out.println("OrderIngredientsPanel not available");
                }

                try {
                        LogFishPanel logFishPanel = new LogFishPanel(cardLayout, contentPanel);
                        contentPanel.add(logFishPanel, LOG_FISH_PANEL);
                } catch (Exception e) {
                        System.out.println("LogFishPanel not available");
                }

                // Add panels to the card layout
                contentPanel.add(introPanel, INTRO_PANEL);
                contentPanel.add(chefPanel, CHEF_PANEL);
                contentPanel.add(fishermanPanel, FISHERMAN_PANEL);
                contentPanel.add(foodLoverPanel, FOOD_LOVER_PANEL);

                // Set the main layout to show the content panel
                javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(this);
                this.setLayout(mainLayout);
                mainLayout.setHorizontalGroup(
                                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
                mainLayout.setVerticalGroup(
                                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

                // Show the intro panel first
                cardLayout.show(contentPanel, INTRO_PANEL);
        }

        /**
         * Creates the intro panel with role selection
         */
        private void createIntroPanel() {
                introPanel = new javax.swing.JPanel();

                // Logo
                logoLabel = new javax.swing.JLabel();
                try {
                        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GReel_final_png.png")));
                } catch (Exception e) {
                        logoLabel.setText("GReel Logo");
                }

                // Welcome message
                welcomeLabel = new javax.swing.JLabel();
                welcomeLabel.setFont(new java.awt.Font("Arial", 1, 18));
                welcomeLabel.setForeground(new java.awt.Color(87, 3, 3));
                welcomeLabel.setText(
                                "<html>Welcome to GReel,<br>a fisherman's handyman.<br>Choose your role to get started!</html>");

                // Name input
                javax.swing.JLabel nameLabel = new javax.swing.JLabel("Enter your name:");
                nameLabel.setFont(new java.awt.Font("Arial", 0, 14));
                nameField = new javax.swing.JTextField(20);
                nameField.setFont(new java.awt.Font("Arial", 0, 14));

                // Role selection
                javax.swing.JLabel roleLabel = new javax.swing.JLabel("Select your role:");
                roleLabel.setFont(new java.awt.Font("Arial", 0, 14));
                String[] roles = { "Choose Role...", "Chef", "Fisherman", "Food Lover" };
                roleComboBox = new javax.swing.JComboBox<>(roles);
                roleComboBox.setFont(new java.awt.Font("Arial", 0, 14));

                // Login button
                loginButton = new javax.swing.JButton("Login");
                loginButton.setBackground(new java.awt.Color(87, 3, 3));
                loginButton.setForeground(new java.awt.Color(255, 255, 255));
                loginButton.setFont(new java.awt.Font("Arial", 1, 14));
                loginButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                                handleLogin();
                        }
                });

                // Layout for intro panel
                javax.swing.GroupLayout introLayout = new javax.swing.GroupLayout(introPanel);
                introPanel.setLayout(introLayout);

                introLayout.setHorizontalGroup(
                                introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                                .addComponent(logoLabel)
                                                .addComponent(welcomeLabel)
                                                .addGroup(introLayout.createSequentialGroup()
                                                                .addGroup(introLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(roleLabel))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(introLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(nameField)
                                                                                .addComponent(roleComboBox, 0,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)))
                                                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE));

                introLayout.setVerticalGroup(
                                introLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(introLayout.createSequentialGroup()
                                                                .addGap(50, 50, 50)
                                                                .addComponent(logoLabel)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(welcomeLabel)
                                                                .addGap(50, 50, 50)
                                                                .addGroup(introLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(nameField,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(introLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(roleLabel)
                                                                                .addComponent(roleComboBox,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(30, 30, 30)
                                                                .addComponent(loginButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                40,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }

        /**
         * Creates the Chef home panel
         */
        private void createChefPanel() {
                chefPanel = new javax.swing.JPanel();
                chefPanel.setBackground(new java.awt.Color(245, 245, 245));

                // Header
                javax.swing.JLabel chefHeaderLabel = new javax.swing.JLabel();
                chefHeaderLabel.setFont(new java.awt.Font("Arial", 1, 24));
                chefHeaderLabel.setForeground(new java.awt.Color(87, 3, 3));
                chefHeaderLabel.setText("Chef Dashboard");

                // Welcome message
                javax.swing.JLabel chefWelcomeLabel = new javax.swing.JLabel();
                chefWelcomeLabel.setFont(new java.awt.Font("Arial", 0, 16));
                chefWelcomeLabel.setText("Welcome, Chef!");

                // Profile section
                javax.swing.JPanel profilePanel = createProfilePanel();

                // Chef-specific buttons
                javax.swing.JButton recipesButton = createFeatureButton("Recipes", "/image (2).png");
                javax.swing.JButton orderIngredientsButton = createFeatureButton("Order Ingredients", "/image (3).png");
                orderIngredientsButton.addActionListener(e -> cardLayout.show(contentPanel, ORDER_INGREDIENTS_PANEL));

                javax.swing.JButton dishPrepareButton = createFeatureButton("Dish Prepare", "/image (2).png");

                // Layout for chef panel
                javax.swing.GroupLayout chefLayout = new javax.swing.GroupLayout(chefPanel);
                chefPanel.setLayout(chefLayout);

                chefLayout.setHorizontalGroup(
                                chefLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(chefLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(chefLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(chefHeaderLabel)
                                                                                .addComponent(chefWelcomeLabel)
                                                                                .addComponent(profilePanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(chefLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(recipesButton)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(orderIngredientsButton)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(dishPrepareButton)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                chefLayout.setVerticalGroup(
                                chefLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(chefLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(chefHeaderLabel)
                                                                .addGap(10, 10, 10)
                                                                .addComponent(chefWelcomeLabel)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(profilePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addGroup(chefLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(recipesButton)
                                                                                .addComponent(orderIngredientsButton)
                                                                                .addComponent(dishPrepareButton))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }

        /**
         * Creates the Fisherman home panel
         */
        private void createFishermanPanel() {
                fishermanPanel = new javax.swing.JPanel();
                fishermanPanel.setBackground(new java.awt.Color(230, 240, 250));

                // Header
                javax.swing.JLabel fishermanHeaderLabel = new javax.swing.JLabel();
                fishermanHeaderLabel.setFont(new java.awt.Font("Arial", 1, 24));
                fishermanHeaderLabel.setForeground(new java.awt.Color(87, 3, 3));
                fishermanHeaderLabel.setText("Fisherman Dashboard");

                // Welcome message
                javax.swing.JLabel fishermanWelcomeLabel = new javax.swing.JLabel();
                fishermanWelcomeLabel.setFont(new java.awt.Font("Arial", 0, 16));
                fishermanWelcomeLabel.setText("Welcome, Fisherman!");

                // Profile section
                javax.swing.JPanel profilePanel = createProfilePanel();

                // Fisherman-specific buttons
                javax.swing.JButton weatherButton = createFeatureButton("Weather Report", "/image (1).png");
                javax.swing.JButton fishingLogButton = createFeatureButton("Fishing Log", "/image (6).png");
                fishingLogButton.addActionListener(e -> cardLayout.show(contentPanel, LOG_FISH_PANEL));

                javax.swing.JButton baitSearchButton = createFeatureButton("Search Bait", "/image (4).png");

                // Layout for fisherman panel
                javax.swing.GroupLayout fishermanLayout = new javax.swing.GroupLayout(fishermanPanel);
                fishermanPanel.setLayout(fishermanLayout);

                fishermanLayout.setHorizontalGroup(
                                fishermanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(fishermanLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(fishermanLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(fishermanHeaderLabel)
                                                                                .addComponent(fishermanWelcomeLabel)
                                                                                .addComponent(profilePanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(fishermanLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(weatherButton)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(fishingLogButton)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(baitSearchButton)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                fishermanLayout.setVerticalGroup(
                                fishermanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(fishermanLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(fishermanHeaderLabel)
                                                                .addGap(10, 10, 10)
                                                                .addComponent(fishermanWelcomeLabel)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(profilePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addGroup(fishermanLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(weatherButton)
                                                                                .addComponent(fishingLogButton)
                                                                                .addComponent(baitSearchButton))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }

        /**
         * Creates the Food Lover home panel
         */
        private void createFoodLoverPanel() {
                foodLoverPanel = new javax.swing.JPanel();
                foodLoverPanel.setBackground(new java.awt.Color(255, 245, 230));

                // Header
                javax.swing.JLabel foodLoverHeaderLabel = new javax.swing.JLabel();
                foodLoverHeaderLabel.setFont(new java.awt.Font("Arial", 1, 24));
                foodLoverHeaderLabel.setForeground(new java.awt.Color(87, 3, 3));
                foodLoverHeaderLabel.setText("Food Lover Dashboard");

                // Welcome message
                javax.swing.JLabel foodLoverWelcomeLabel = new javax.swing.JLabel();
                foodLoverWelcomeLabel.setFont(new java.awt.Font("Arial", 0, 16));
                foodLoverWelcomeLabel.setText("Welcome, Food Lover!");

                // Profile section
                javax.swing.JPanel profilePanel = createProfilePanel();

                // Food Lover-specific buttons
                javax.swing.JButton orderFoodButton = createFeatureButton("Order Food",
                                "/salmon-plate-cartoon-illustration_1220412-671 (1)-Photoroom.png");
                javax.swing.JButton rateFoodButton = createFeatureButton("Rate Food", "/image-Photoroom (1).png");
                javax.swing.JButton cookbookButton = createFeatureButton("Cookbook", "/image (5).png");

                // Layout for food lover panel
                javax.swing.GroupLayout foodLoverLayout = new javax.swing.GroupLayout(foodLoverPanel);
                foodLoverPanel.setLayout(foodLoverLayout);

                foodLoverLayout.setHorizontalGroup(
                                foodLoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(foodLoverLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addGroup(foodLoverLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(foodLoverHeaderLabel)
                                                                                .addComponent(foodLoverWelcomeLabel)
                                                                                .addComponent(profilePanel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGroup(foodLoverLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(orderFoodButton)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(rateFoodButton)
                                                                                                .addGap(30, 30, 30)
                                                                                                .addComponent(cookbookButton)))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));

                foodLoverLayout.setVerticalGroup(
                                foodLoverLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(foodLoverLayout.createSequentialGroup()
                                                                .addGap(30, 30, 30)
                                                                .addComponent(foodLoverHeaderLabel)
                                                                .addGap(10, 10, 10)
                                                                .addComponent(foodLoverWelcomeLabel)
                                                                .addGap(30, 30, 30)
                                                                .addComponent(profilePanel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(50, 50, 50)
                                                                .addGroup(foodLoverLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(orderFoodButton)
                                                                                .addComponent(rateFoodButton)
                                                                                .addComponent(cookbookButton))
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }

        /**
         * Creates a profile panel with user info and logout
         */
        private javax.swing.JPanel createProfilePanel() {
                javax.swing.JPanel profilePanel = new javax.swing.JPanel();
                profilePanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Profile"));

                javax.swing.JLabel userInfoLabel = new javax.swing.JLabel();
                userInfoLabel.setFont(new java.awt.Font("Arial", 0, 14));

                javax.swing.JButton logoutButton = new javax.swing.JButton("Logout");
                logoutButton.setBackground(new java.awt.Color(87, 3, 3));
                logoutButton.setForeground(new java.awt.Color(255, 255, 255));
                logoutButton.addActionListener(e -> {
                        userName = "";
                        userRole = "";
                        nameField.setText("");
                        roleComboBox.setSelectedIndex(0);
                        cardLayout.show(contentPanel, INTRO_PANEL);
                });

                javax.swing.GroupLayout profileLayout = new javax.swing.GroupLayout(profilePanel);
                profilePanel.setLayout(profileLayout);

                profileLayout.setHorizontalGroup(
                                profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(profileLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(userInfoLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(logoutButton)
                                                                .addContainerGap()));

                profileLayout.setVerticalGroup(
                                profileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(profileLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(profileLayout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(userInfoLabel)
                                                                                .addComponent(logoutButton))
                                                                .addContainerGap()));

                return profilePanel;
        }

        /**
         * Creates a feature button with icon and text
         */
        private javax.swing.JButton createFeatureButton(String text, String iconPath) {
                javax.swing.JButton button = new javax.swing.JButton(text);
                button.setFont(new java.awt.Font("Arial", 0, 12));
                button.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
                button.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
                button.setBorder(null);
                button.setPreferredSize(new java.awt.Dimension(120, 80));

                try {
                        button.setIcon(new javax.swing.ImageIcon(getClass().getResource(iconPath)));
                } catch (Exception e) {
                        // Icon not found, button will show text only
                }

                return button;
        }

        /**
         * Handles the login process
         */
        private void handleLogin() {
                String name = nameField.getText().trim();
                String role = (String) roleComboBox.getSelectedItem();

                if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Please enter your name.", "Name Required",
                                        JOptionPane.WARNING_MESSAGE);
                        return;
                }

                if (role == null || role.equals("Choose Role...")) {
                        JOptionPane.showMessageDialog(this, "Please select a role.", "Role Required",
                                        JOptionPane.WARNING_MESSAGE);
                        return;
                }

                userName = name;
                userRole = role;

                // Update welcome messages in all panels
                updateWelcomeMessages();

                // Navigate to appropriate panel based on role
                switch (role) {
                        case "Chef":
                                cardLayout.show(contentPanel, CHEF_PANEL);
                                break;
                        case "Fisherman":
                                cardLayout.show(contentPanel, FISHERMAN_PANEL);
                                break;
                        case "Food Lover":
                                cardLayout.show(contentPanel, FOOD_LOVER_PANEL);
                                break;
                }
        }

        /**
         * Updates welcome messages with user name
         */
        private void updateWelcomeMessages() {
                // Update welcome labels in each panel with the user's name
                java.awt.Component[] components;

                // Update chef panel
                components = chefPanel.getComponents();
                for (java.awt.Component comp : components) {
                        if (comp instanceof javax.swing.JLabel) {
                                javax.swing.JLabel label = (javax.swing.JLabel) comp;
                                if (label.getText().startsWith("Welcome, Chef")) {
                                        label.setText("Welcome, Chef " + userName + "!");
                                }
                        }
                }

                // Update fisherman panel
                components = fishermanPanel.getComponents();
                for (java.awt.Component comp : components) {
                        if (comp instanceof javax.swing.JLabel) {
                                javax.swing.JLabel label = (javax.swing.JLabel) comp;
                                if (label.getText().startsWith("Welcome, Fisherman")) {
                                        label.setText("Welcome, Fisherman " + userName + "!");
                                }
                        }
                }

                // Update food lover panel
                components = foodLoverPanel.getComponents();
                for (java.awt.Component comp : components) {
                        if (comp instanceof javax.swing.JLabel) {
                                javax.swing.JLabel label = (javax.swing.JLabel) comp;
                                if (label.getText().startsWith("Welcome, Food Lover")) {
                                        label.setText("Welcome, Food Lover " + userName + "!");
                                }
                        }
                }
        }

        /**
         * This method is called from within the constructor to initialize the form.
         */
        private void initComponents() {
                // This method is intentionally minimal since we're building the UI
                // programmatically
                // in setupCardLayout() method
        }

        /**
         * Method to add a new panel to the card layout
         */
        public void addPanel(javax.swing.JPanel panel, String name) {
                contentPanel.add(panel, name);
        }

        /**
         * Method to switch to a specific panel
         */
        public void showPanel(String name) {
                cardLayout.show(contentPanel, name);
        }

        /**
         * Get the current user's name
         */
        public String getUserName() {
                return userName;
        }

        /**
         * Get the current user's role
         */
        public String getUserRole() {
                return userRole;
        }
}
 