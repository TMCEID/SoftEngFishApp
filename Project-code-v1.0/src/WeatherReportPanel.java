import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * Main Weather Report Panel that manages different weather report pages
 */
public class WeatherReportPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private WeatherDetails weatherDetails;

    // Weather report pages
    private GoodWeatherReportPage goodWeatherPage;
    private BadWeatherReportPage badWeatherPage;

    // Panel identifiers
    private final String GOOD_WEATHER_PAGE = "goodWeatherReportPage";
    private final String BAD_WEATHER_PAGE = "badWeatherReportPage";

    /**
     * Constructor
     * @param mainCardLayout the main CardLayout used to switch views
     * @param mainContentPanel the main content panel that holds this panel
     */
    public WeatherReportPanel(CardLayout mainCardLayout, JPanel mainContentPanel) {
        this.weatherDetails = new WeatherDetails();
        this.contentPanel = mainContentPanel;
        initComponents();
        setupWeatherPages(mainCardLayout, mainContentPanel);
    }

    /**
     * Initialize components
     */
    private void initComponents() {
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
    }

    /**
     * Setup weather report pages
     */
    private void setupWeatherPages(CardLayout mainCardLayout, JPanel mainContentPanel) {
        goodWeatherPage = new GoodWeatherReportPage(mainCardLayout, mainContentPanel);
        badWeatherPage = new BadWeatherReportPage(mainCardLayout, mainContentPanel);

        this.add(goodWeatherPage, GOOD_WEATHER_PAGE);
        this.add(badWeatherPage, BAD_WEATHER_PAGE);
    }

    /**
     * Show weather report based on current conditions
     */
    public void showWeatherReport() {
        System.out.println("=== Starting showWeatherReport ===");

        // Fetch weather data once
        weatherDetails.getWeatherDetails();

        System.out.println("Weather fetched in panel:");
        System.out.println("Temperature: " + weatherDetails.getTemperature() + "°C");
        System.out.println("Weather Type: " + weatherDetails.getWeatherType());
        System.out.println("Is Good Weather: " + weatherDetails.isGoodORBad());

        // Share the same weather data with both pages
        goodWeatherPage.setWeatherDetails(weatherDetails);
        badWeatherPage.setWeatherDetails(weatherDetails);

        // Show appropriate page based on weather conditions
        if (weatherDetails.isGoodORBad()) {
            System.out.println("Showing GOOD weather page");
            goodWeatherPage.presentInfoGOOD();
            cardLayout.show(this, GOOD_WEATHER_PAGE);
        } else {
            System.out.println("Showing BAD weather page");
            badWeatherPage.presentInfoBAD();
            cardLayout.show(this, BAD_WEATHER_PAGE);
        }

        System.out.println("=== Finished showWeatherReport ===");
    }

    /**
     * Force show good weather page
     */
    public void showGoodWeatherPage() {
        weatherDetails.getWeatherDetails();
        goodWeatherPage.setWeatherDetails(weatherDetails);
        goodWeatherPage.presentInfoGOOD();
        cardLayout.show(this, GOOD_WEATHER_PAGE);
    }

    /**
     * Force show bad weather page
     */
    public void showBadWeatherPage() {
        weatherDetails.getWeatherDetails();
        badWeatherPage.setWeatherDetails(weatherDetails);
        badWeatherPage.presentInfoBAD();
        cardLayout.show(this, BAD_WEATHER_PAGE);
    }

    /**
     * Get current weather details
     */
    public WeatherDetails getWeatherDetails() {
        return weatherDetails;
    }

    /**
     * Set weather details
     */
    public void setWeatherDetails(WeatherDetails weatherDetails) {
        this.weatherDetails = weatherDetails;
    }

    /**
     * Get good weather page
     */
    public GoodWeatherReportPage getGoodWeatherPage() {
        return goodWeatherPage;
    }

    /**
     * Get bad weather page
     */
    public BadWeatherReportPage getBadWeatherPage() {
        return badWeatherPage;
    }
}