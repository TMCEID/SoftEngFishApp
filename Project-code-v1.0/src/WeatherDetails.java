import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherDetails {
    public String city = "Patras";
    public String apiKey = "6c6b748d756eba6cb93876eb00292254";

    // Made non-static and private with proper encapsulation
    private double temperature;
    private String weatherType;
    private String date;
    private boolean isGood;
    private boolean dataFetched = false; // Track if data has been loaded

    // Constructor
    public WeatherDetails() {
        // Initialize with default values
        this.temperature = 0.0;
        this.weatherType = "Unknown";
        this.date = "";
        this.isGood = false;
        this.dataFetched = false;
    }

    public void getWeatherDetails() {
        System.out.println("=== Starting getWeatherDetails() ===");


        // Commented out API call - uncomment and use this for real API calls
        try {
            String urlString = String.format(
                "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric",
                city, apiKey
            );
            System.out.println("Request URL: " + urlString);
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            System.out.println("Raw JSON response: " + responseBuilder.toString());
            JSONObject response = new JSONObject(responseBuilder.toString());

            this.temperature = response.getJSONObject("main").getDouble("temp");
            this.weatherType = response.getJSONArray("weather").getJSONObject(0).getString("main");

            System.out.println("Parsed temperature: " + temperature);
            System.out.println("Parsed weatherType: " + weatherType);

            // Determine if weather is "good"
            isGood = temperature >= 15 && (weatherType.equalsIgnoreCase("Clear") ||
                     weatherType.equalsIgnoreCase("Clouds"));
            System.out.println("isGood set to: " + isGood);

        } catch (Exception e) {
            e.printStackTrace();
            this.temperature = 0;
            this.weatherType = "Unknown";
            this.isGood = false;
            System.out.println("Exception caught in fetchWeatherData");
        }


       /* // Random weather data for testing
        System.out.println("Generating random weather data...");

        // Random temperature with more variety including bad weather temps
        double[] tempOptions = {-2.5, 5.0, 8.0, 12.0, 18.5, 22.0, 28.5, 35.0, 3.5, 10.5};
        this.temperature = tempOptions[(int) (Math.random() * tempOptions.length)];

        // Random weather type
        String[] weatherOptions = {"Clear", "Clouds", "Rain", "Thunderstorm", "Snow", "Drizzle", "Fog"};
        this.weatherType = weatherOptions[(int) (Math.random() * weatherOptions.length)];

        // Determine if weather is "good" - temperature >= 15 AND (Clear OR Clouds)
        this.isGood = this.temperature >= 15.0 && (this.weatherType.equalsIgnoreCase("Clear") ||
                this.weatherType.equalsIgnoreCase("Clouds"));*/

        // Fetch the date
        fetchDate();

        // Mark data as fetched
        this.dataFetched = true;

        System.out.println("=== Weather Data Generated ===");
        System.out.println("Temperature: " + this.temperature + "°C");
        System.out.println("Weather type: " + this.weatherType);
        System.out.println("Date: " + this.date);
        System.out.println("IsGood: " + this.isGood);
        System.out.println("DataFetched: " + this.dataFetched);
        System.out.println("=== End Weather Data ===");
    }

    public boolean isGoodORBad() {
        return this.isGood;
    }

    public void setGoodORBad(boolean value) {
        this.isGood = value;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public String getWeatherType() {
        return this.weatherType;
    }

    public String getDate() {
        if (this.date == null || this.date.isEmpty()) {
            fetchDate();
        }
        return this.date;
    }

    public void fetchDate() {
        this.date = java.time.LocalDate.now().toString();
        System.out.println("Fetched date: " + this.date);
    }

    public boolean isDataFetched() {
        return this.dataFetched;
    }

    // Additional setters for manual data setting
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setWeatherType(String weatherType) {
        this.weatherType = weatherType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Method to reset data (useful for testing)
    public void resetData() {
        this.temperature = 0.0;
        this.weatherType = "Unknown";
        this.date = "";
        this.isGood = false;
        this.dataFetched = false;
        System.out.println("Weather data reset");
    }

    // Method to print current state for debugging
    public void printCurrentState() {
        System.out.println("=== WeatherDetails Current State ===");
        System.out.println("Temperature: " + this.temperature);
        System.out.println("Weather Type: " + this.weatherType);
        System.out.println("Date: " + this.date);
        System.out.println("IsGood: " + this.isGood);
        System.out.println("DataFetched: " + this.dataFetched);
        System.out.println("===================================");
    }
}