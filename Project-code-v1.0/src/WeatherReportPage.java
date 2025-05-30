import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * Abstract base class for Weather Report Pages
 */
public abstract class WeatherReportPage extends JPanel {
    protected WeatherDetails weatherDetails;
    protected CardLayout cardLayout;
    protected JPanel contentPanel;

    /**
     * Constructor
     * @param cardLayout the layout manager to switch between panels
     * @param contentPanel the parent container using the card layout
     */
    public WeatherReportPage(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.weatherDetails = null; // Initialize as null - will be set by Main class
        System.out.println("WeatherReportPage constructor called");
    }

    /**
     * Fetch weather information - calls getWeatherDetails() to populate all data
     */
    public void fetchWeatherInfo() {
        System.out.println("=== fetchWeatherInfo() called ===");

        if (this.weatherDetails == null) {
            System.out.println("WeatherDetails is null, creating new instance");
            this.weatherDetails = new WeatherDetails();
        }

        // Always fetch fresh data when this method is called
        System.out.println("Calling weatherDetails.getWeatherDetails()...");
        this.weatherDetails.getWeatherDetails();

        System.out.println("=== Weather Info Fetched ===");
        System.out.println("Temperature: " + this.weatherDetails.getTemperature() + "°C");
        System.out.println("Weather Type: " + this.weatherDetails.getWeatherType());
        System.out.println("Date: " + this.weatherDetails.getDate());
        System.out.println("Is Good Weather: " + this.weatherDetails.isGoodORBad());
        System.out.println("========================");
    }

    /**
     * Abstract method to be implemented by child classes to show themselves
     */
    public abstract void cardLayoutShow();

    /**
     * Get weather details
     */
    public WeatherDetails getWeatherDetails() {
        return this.weatherDetails;
    }

    /**
     * Set weather details - used when sharing weather data between pages
     */
    public void setWeatherDetails(WeatherDetails weatherDetails) {
        System.out.println("=== setWeatherDetails() called ===");
        this.weatherDetails = weatherDetails;

        if (weatherDetails != null) {
            System.out.println("Weather details set successfully:");
            System.out.println("Temperature: " + weatherDetails.getTemperature() + "°C");
            System.out.println("Weather Type: " + weatherDetails.getWeatherType());
            System.out.println("Date: " + weatherDetails.getDate());
            System.out.println("Is Good: " + weatherDetails.isGoodORBad());
        } else {
            System.out.println("Weather details set to null");
        }
        System.out.println("==============================");
    }

    /**
     * Print weather information for debugging
     */
    public void printWeatherInfo() {
        System.out.println("=== Weather Info Debug Print ===");
        if (this.weatherDetails != null) {
            System.out.println("Date: " + this.weatherDetails.getDate());
            System.out.println("Temperature: " + this.weatherDetails.getTemperature() + "°C");
            System.out.println("Weather Type: " + this.weatherDetails.getWeatherType());
            System.out.println("Is Good Weather: " + this.weatherDetails.isGoodORBad());
            System.out.println("Data Fetched: " + this.weatherDetails.isDataFetched());
        } else {
            System.out.println("WeatherDetails is null!");
        }
        System.out.println("===============================");
    }

    /**
     * Ensure weather data is available - fetch if needed
     */
    protected void ensureWeatherData() {
        System.out.println("=== ensureWeatherData() called ===");

        if (this.weatherDetails == null) {
            System.out.println("WeatherDetails is null, fetching new data");
            fetchWeatherInfo();
        } else if (!this.weatherDetails.isDataFetched() ||
                this.weatherDetails.getWeatherType().equals("Unknown")) {
            System.out.println("WeatherDetails exists but data not fetched, fetching data");
            this.weatherDetails.getWeatherDetails();
        } else {
            System.out.println("WeatherDetails exists and has data, using existing data");
        }

        // Print current state after ensuring data
        if (this.weatherDetails != null) {
            this.weatherDetails.printCurrentState();
        }
        System.out.println("=================================");
    }
}