import java.awt.CardLayout;

import model.User;

public class Main extends javax.swing.JPanel {

    // Controllers from second main
    private final FriendsController friendsCtrl = new FriendsController();
    private User currentUser = null;   // logged-in user
    private final FoodSearchController foodCtrl = new FoodSearchController();

    // All panels connected
    private CardLayout cardLayout;
    private final String INTRO_PANEL = "introPanel";
    private final String MAIN_APP_PANEL = "mainAppPanel";
    private final String ORDER_INGREDIENTS_PANEL = "orderIngredientsPanel";
    private final String LOG_FISH_PANEL = "confirmationPanel";
    private final String WEATHER_REPORT_PANEL = "weatherReportPanel";
    private final String FRIENDS_PANEL = "friendsPanel";

    // Create panels for different sections
    private javax.swing.JPanel contentPanel;
    private IntroPanel introPanel;
    private MainAppPanel mainAppPanel;
    private OrderIngredientsPanel orderIngredientsPanel;
    private LogFishPanel logFishPanel;
    private WeatherReportPanel weatherReportPanel;
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

        // Create friends panel
        friendsPanel = new FriendsPanel(1);  // use any test user ID


        // Add panels to the card layout
        contentPanel.add(introPanel, INTRO_PANEL);
        contentPanel.add(mainAppPanel, MAIN_APP_PANEL);
        contentPanel.add(orderIngredientsPanel, ORDER_INGREDIENTS_PANEL);
        contentPanel.add(logFishPanel, LOG_FISH_PANEL);
        contentPanel.add(weatherReportPanel, WEATHER_REPORT_PANEL);
        contentPanel.add(friendsPanel, FRIENDS_PANEL);

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