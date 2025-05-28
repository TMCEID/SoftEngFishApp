import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Component;
import model.Recipe;
import model.Dish;
import model.SortStrategy;
import model.User;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JLabel;


public class MainAppPanel extends javax.swing.JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Main parentMain;  // Reference to main for controllers and user access
    /* use the public constants exposed by the shell Main */
    private final String INTRO_PANEL             = Main.INTRO_PANEL;
    private final String ORDER_INGREDIENTS_PANEL = Main.ORDER_INGREDIENTS_PANEL;
    private final String LOG_FISH_PANEL          = Main.LOG_FISH_PANEL;
    private final String WEATHER_REPORT_PANEL    = Main.WEATHER_REPORT_PANEL;
    private final String BAIT_SEARCH_PANEL       = Main.BAIT_SEARCH_PANEL;
    private final String VIEW_FRIENDS_PANEL      = Main.VIEW_FRIENDS_PANEL;
<<<<<<< HEAD
    private final String EDIT_PROFILE_PANEL = "editProfilePanel";
    private final String RATING_PANEL = Main.RATING_PANEL; // Added
=======
    private final String EDIT_PROFILE_PANEL = "editProfilePanel";  // Added
    //NEW
    private final String RECIPE_SEARCH_PANEL     = Main.RECIPE_SEARCH_PANEL;
>>>>>>> 7f04fd0be43b86b8239b485e44354acbe790dad7

    // Components
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButtonViewFriends;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel roleLabel;

    /**
     * Creates new MainAppPanel
     * @param cardLayout The CardLayout manager
     * @param contentPanel The parent content panel
     * @param parentMain Reference to main class for controllers and user access
     */
    public MainAppPanel(CardLayout cardLayout, JPanel contentPanel, Main parentMain) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.parentMain = parentMain;
        initComponents();
    }

    /**
     * Initialize the components
     */
    private void initComponents() {



        // Initialize components
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButtonViewFriends = new javax.swing.JButton();
        // Configure profile image
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("image.png")));


        usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 14));

        phoneLabel = new JLabel("Phone: ");
        phoneLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));

        roleLabel = new JLabel("Role: ");
        roleLabel.setFont(new java.awt.Font("SansSerif", java.awt.Font.ITALIC, 13));

        // Configure profile buttons
        jButton2.setBackground(new java.awt.Color(87, 3, 3));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icon.png")));
        jButton2.setText("Edit profile");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(87, 3, 3));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icon (1).png")));
        jButton3.setText("Add friends");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        // View friends button
        jButtonViewFriends.setBackground(new java.awt.Color(87, 3, 3));
        jButtonViewFriends.setForeground(new java.awt.Color(255, 255, 255));
        jButtonViewFriends.setText("View friends");
        jButtonViewFriends.addActionListener(this::jButtonViewFriendsActionPerformed);

        jButton4.setBackground(new java.awt.Color(87, 3, 3));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icon (2).png")));
        jButton4.setText("Logout");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        // Configure feature buttons
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("image (1).png")));
        jButton5.setText("WeatherReport");
        jButton5.setBorder(null);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                for (Component comp : contentPanel.getComponents()) {
                    if (comp instanceof WeatherReportPanel) {
                        WeatherReportPanel panel = (WeatherReportPanel) comp;
                        panel.showWeatherReport();
                        cardLayout.show(contentPanel, WEATHER_REPORT_PANEL);
                        break;
                    }
                }
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("image (2).png")));
        jButton6.setText("Recipes");
        jButton6.setText("Search recipes");
        jButton6.setBorder(null);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("image (3).png")));
        jButton7.setText("Order ingredients");
        jButton7.setBorder(null);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardLayout.show(contentPanel, ORDER_INGREDIENTS_PANEL);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("image (4).png")));
        jButton8.setText("Baits");
        jButton8.setBorder(null);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        /*  open the bait search screen */
                jButton8.addActionListener(evt ->
                          cardLayout.show(contentPanel, BAIT_SEARCH_PANEL));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("image (5).png")));
        jButton9.setText("Recipe Maker");
        jButton9.setBorder(null);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("image (6).png")));
        jButton10.setText("Log Fish");
        jButton10.setBorder(null);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardLayout.show(contentPanel, LOG_FISH_PANEL);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("image-Photoroom (1).png")));
        jButton11.setText("Rate food experience");
        jButton11.setBorder(null);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/salmon-plate-cartoon-illustration_1220412-671 (1)-Photoroom.png")));
        jButton12.setText("Order food");
        jButton12.setBorder(null);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        // Layout setup
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(usernameLabel)
                                        .addComponent(phoneLabel)
                                        .addComponent(roleLabel)
                                        .addComponent(jButton2)
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonViewFriends, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(69, 69, 69))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton8)
                                        .addComponent(jButton5))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton9)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton10))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jButton6)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton7))))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addComponent(jButton11)
                                .addGap(27, 27, 27)
                                .addComponent(jButton12))
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(usernameLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(phoneLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(roleLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButtonViewFriends)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4)))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton6)
                                                        .addComponent(jButton5))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButton10)
                                                        .addComponent(jButton9)
                                                        .addComponent(jButton8)))
                                        .addComponent(jButton7))
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButton11)
                                        .addComponent(jButton12))
                                .addContainerGap(355, Short.MAX_VALUE))
        );
    }
<<<<<<< HEAD
    /**
     * Update the profile display with current user information
     */
    public void updateProfileDisplay() {
        User currentUser = parentMain.getCurrentUser();
        if (currentUser != null && currentUser.getProfileImagePath() != null) {
            try {
                // Update the profile image (jLabel3) with the user's selected image
                ImageIcon profileIcon = new ImageIcon(
                        new ImageIcon(currentUser.getProfileImagePath())
                                .getImage()
                                .getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_SMOOTH)
                );
                jLabel3.setIcon(profileIcon);
            } catch (Exception e) {
                // Keep the default image if there's an error loading the custom image
                System.err.println("Error loading profile image: " + e.getMessage());
            }
            usernameLabel.setText("Username: " + currentUser.getUsername());
            phoneLabel.setText("Phone: " + currentUser.getPhone());
            roleLabel.setText("Role: " + (currentUser.getRole() != null ? currentUser.getRole() : "N/A"));
        }
    }
    /**
     * Handle recipe search button click - includes food search functionality
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        String kw = JOptionPane.showInputDialog(this, "Search dish:");
        if (kw == null || kw.isBlank()) return;

        // best-rating first (works on dishes)
        SortStrategy<Dish> bestRated = list -> list.stream()
                .sorted((a, b) -> Float.compare(b.getRating(), a.getRating()))
                .toList();

        var recipes = parentMain.getFoodController().search(kw.trim(), bestRated);

        JOptionPane.showMessageDialog(this,
                recipes.isEmpty() ? "No match found"
                        : recipes.stream()
                        .map(Recipe::getText)
                        .limit(5)
                        .reduce("", (a,b)->a + "\n\n" + b));
    }
=======

>>>>>>> 7f04fd0be43b86b8239b485e44354acbe790dad7

    /**
     * Handle edit profile button click
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (parentMain.getCurrentUser() == null) {
            JOptionPane.showMessageDialog(this, "Please login first!");
            return;
        }
        // Navigate to edit profile panel and load current user data
        parentMain.showPanel(EDIT_PROFILE_PANEL);
    }

    /**
     * Handle add friends button click - includes add friend functionality
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (parentMain.getCurrentUser() == null) {
            JOptionPane.showMessageDialog(this, "Please login first!");
            return;
        }

        new AddFriendDialog(
                javax.swing.SwingUtilities.getWindowAncestor(this) instanceof java.awt.Frame
                        ? (java.awt.Frame) javax.swing.SwingUtilities.getWindowAncestor(this)
                        : null,
                parentMain.getFriendsController(),
                parentMain.getCurrentUser().getUserId()
        ).setVisible(true);
    }

    /**
     * Handle view friends button click
     */
    private void jButtonViewFriendsActionPerformed(java.awt.event.ActionEvent evt) {
        if (parentMain.getCurrentUser() == null) {
            JOptionPane.showMessageDialog(this, "Please login first!");
            return;
        }

        // 1) Ask Main to reload its friendsModel
        parentMain.refreshFriendsList();

        // 2) Show the view-friends card
        cardLayout.show(contentPanel, VIEW_FRIENDS_PANEL);
    }

    /**
     * Handle logout button click
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        // Clear current user and return to intro panel
        parentMain.setCurrentUser(null);
        cardLayout.show(contentPanel, INTRO_PANEL);
    }

    /**
     * Handle rate food experience button click
     */
    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {
        if (parentMain.getCurrentUser() == null) {
            JOptionPane.showMessageDialog(this, "Please login first!");
            return;
        }
        cardLayout.show(contentPanel, RATING_PANEL);
    }
}