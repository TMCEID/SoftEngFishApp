

import java.awt.CardLayout;

import model.User;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Friend;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
//import BaitSearchPanel;


public class Main extends javax.swing.JPanel {

    // — for the “view friends” card —
    private javax.swing.DefaultListModel<model.User> friendsModel;
    private javax.swing.JList<model.User>          friendsList;
    private javax.swing.JButton                    backFromFriendsBtn;

    // Controllers from second main
    private final FriendsController friendsCtrl = new FriendsController();
    private User currentUser = null;   // logged-in user
    private final FoodSearchController foodCtrl = new FoodSearchController();

    // All panels connected
    private CardLayout cardLayout;
    /* ─── card IDs (made public so other panels can import) ─── */
    public static final String INTRO_PANEL             = "introPanel";
    public static final String MAIN_APP_PANEL          = "mainAppPanel";
    public static final String ORDER_INGREDIENTS_PANEL = "orderIngredientsPanel";
    public static final String LOG_FISH_PANEL          = "confirmationPanel";
    public static final String WEATHER_REPORT_PANEL    = "weatherReportPanel";
    /* new: bait search */
    public static final String BAIT_SEARCH_PANEL       = "baitSearchPanel";

    public static final String FRIENDS_PANEL           = "friendsPanel";
    /* sub-cards inside the friends feature */
    public static final String ADD_FRIENDS_PANEL       = "addFriendsPanel";
    public static final String VIEW_FRIENDS_PANEL      = "viewFriendsPanel";

    // Create panels for different sections
    private javax.swing.JPanel contentPanel;
    private IntroPanel introPanel;
    private MainAppPanel mainAppPanel;
    private OrderIngredientsPanel orderIngredientsPanel;
    private LogFishPanel logFishPanel;
    private WeatherReportPanel weatherReportPanel;
    private BaitSearchPanel    baitSearchPanel;   // <--  NEW

    private FriendsPanel friendsPanel;

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
        // Initialize card layout and content panel FIRST
        cardLayout = new CardLayout();
        contentPanel = new javax.swing.JPanel();
        contentPanel.setLayout(cardLayout);

        // Create the intro panel (enhanced with login functionality)
        introPanel = new IntroPanel(cardLayout, contentPanel, this);

        // Create the main app panel (enhanced with friends and food search)
        mainAppPanel = new MainAppPanel(cardLayout, contentPanel, this);

        // Create other panels
        orderIngredientsPanel = new OrderIngredientsPanel(cardLayout, contentPanel);
        logFishPanel = new LogFishPanel(cardLayout, contentPanel);
        weatherReportPanel = new WeatherReportPanel(cardLayout, contentPanel);
        baitSearchPanel    = new BaitSearchPanel   (cardLayout, contentPanel);

        // === friends: two separate cards ===
        // 1) panel to ADD friends (search bar + button)
        friendsPanel = new FriendsPanel(1);
        contentPanel.add(friendsPanel, ADD_FRIENDS_PANEL);

        // 2) panel to VIEW friends (list + back button)
        friendsModel = new DefaultListModel<>();
        friendsList = new JList<>(friendsModel);
        // copy the same cell‐renderer from FriendsPanel so we see icon + phone
        friendsList.setCellRenderer((lst, user, i, sel, foc) -> {
            JPanel p = new JPanel(new BorderLayout());
            p.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

            JLabel icon = new JLabel(user.getAvatar());
            JLabel name = new JLabel(user.getUsername() +
                    "  (" + user.getPhone() + ")");
            name.setFont(name.getFont().deriveFont(15f));

            p.add(icon, BorderLayout.WEST);
            p.add(name, BorderLayout.CENTER);
            if (sel) p.setBackground(new Color(225, 230, 255));
            return p;
        });

        backFromFriendsBtn = new JButton("← Back");
        backFromFriendsBtn.addActionListener(e ->
                cardLayout.show(contentPanel, MAIN_APP_PANEL)
        );

        JPanel viewFriendsPanel = new JPanel(new BorderLayout(5, 5));
        viewFriendsPanel.add(backFromFriendsBtn, BorderLayout.NORTH);
        viewFriendsPanel.add(new JScrollPane(friendsList), BorderLayout.CENTER);
        contentPanel.add(viewFriendsPanel, VIEW_FRIENDS_PANEL);




                        // Add the rest of your panels
                                contentPanel.add(introPanel, INTRO_PANEL);
                contentPanel.add(mainAppPanel, MAIN_APP_PANEL);
                contentPanel.add(orderIngredientsPanel, ORDER_INGREDIENTS_PANEL);
                contentPanel.add(logFishPanel, LOG_FISH_PANEL);
                contentPanel.add(weatherReportPanel, WEATHER_REPORT_PANEL);
                contentPanel.add(baitSearchPanel   , BAIT_SEARCH_PANEL);

        // Set the main layout to show the content panel
        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(this);
        this.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainLayout.setVerticalGroup(
                mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        // Show the intro panel first
        cardLayout.show(contentPanel, INTRO_PANEL);
    }
    /* * Called by MainAppPanel to reload the view‐friends list before showing it */
    public void refreshFriendsList () {
        if (currentUser == null) return;
        friendsModel.clear();
        friendsCtrl
                .getAll(currentUser.getUserId())
                .forEach(friendsModel::addElement);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        // This method is now empty since we moved all components to separate panels
    }

    /**
     * Method to add a new panel to the card layout
     * @param panel The panel to add
     * @param name The name identifier for the panel to show
     */
    public void addPanel(javax.swing.JPanel panel, String name) {
        contentPanel.add(panel, name);
    }

    /**
     * Method to switch to a specific panel
     * @param name The name identifier of the panel to show
     */
    public void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }

    /**
     * Get the CardLayout instance for external use
     * @return The CardLayout instance
     */
    public CardLayout getCardLayout() {
        return cardLayout;
    }

    /**
     * Get the content panel for external use
     * @return The content panel
     */
    public javax.swing.JPanel getContentPanel() {
        return contentPanel;
    }

    // Getters and setters for controllers and current user
    public FriendsController getFriendsController() {
        return friendsCtrl;
    }

    public FoodSearchController getFoodController() {
        return foodCtrl;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Panel name constants for external access
    public String getIntroPanel() { return INTRO_PANEL; }
    public String getMainAppPanel() { return MAIN_APP_PANEL; }
    public String getOrderIngredientsPanel() { return ORDER_INGREDIENTS_PANEL; }
    public String getLogFishPanel() { return LOG_FISH_PANEL; }
    public String getWeatherReportPanel() { return WEATHER_REPORT_PANEL; }
    public String getFriendsPanel() { return FRIENDS_PANEL; }
}