import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GoodWeatherReportPage extends JPanel {
    // Weather details and layout management
    protected WeatherDetails weatherDetails;
    protected CardLayout cardLayout;
    protected JPanel contentPanel;

    private String backgroundColor;
    private String emojiIcon;
    private String fontStyle;

    // UI Components
    private JLabel titleLabel;
    private JLabel weatherIconLabel;
    private JLabel temperatureLabel;
    private JLabel dateLabel;
    private JLabel weatherTypeLabel;
    private JLabel fishingAdviceLabel;
    private JButton backButton;
    private JButton programTripButton; // New button for trip programming

    /**
     * Constructor
     */
    public GoodWeatherReportPage(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.weatherDetails = null; // Initialize as null - will be set by Main class

        this.backgroundColor = "#87CEEB"; // Sky blue for good weather
        this.emojiIcon = "☀️";
        this.fontStyle = "Arial";
        initComponents();
        System.out.println("GoodWeatherReportPage constructor completed");
    }

    /**
     * Initialize components
     */
    private void initComponents() {
        // Set background color
        this.setBackground(Color.decode(backgroundColor));
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 50, 30, 50)); // padding around panel

        // Initialize components
        titleLabel = new JLabel("Weather Report - Perfect Fishing Day!");
        weatherIconLabel = new JLabel(emojiIcon + " " + emojiIcon + " " + emojiIcon);
        temperatureLabel = new JLabel("Temperature: Loading...");
        dateLabel = new JLabel("Date: Loading...");
        weatherTypeLabel = new JLabel("Weather: Loading...");
        fishingAdviceLabel = new JLabel("<html><center>Great weather for fishing!<br>The fish are likely to be active today.</center></html>");
        backButton = new JButton("Back to Main");
        programTripButton = new JButton("Program Fishing Trip"); // New button

        // Configure components
        Font titleFont = new Font("Segoe UI", Font.BOLD, 28);
        Font iconFont = new Font("Segoe UI Emoji", Font.PLAIN, 60);
        Font mainFontBold = new Font("Segoe UI", Font.BOLD, 20);
        Font mainFontPlain = new Font("Segoe UI", Font.PLAIN, 18);
        Font adviceFont = new Font("Segoe UI", Font.ITALIC, 16);

        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(25, 25, 112)); // Navy blue
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        weatherIconLabel.setFont(iconFont);
        weatherIconLabel.setHorizontalAlignment(JLabel.CENTER);
        weatherIconLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));

        temperatureLabel.setFont(mainFontBold);
        temperatureLabel.setForeground(new Color(25, 25, 112));
        temperatureLabel.setHorizontalAlignment(JLabel.CENTER);
        temperatureLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));

        dateLabel.setFont(mainFontPlain);
        dateLabel.setForeground(new Color(25, 25, 112));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));

        weatherTypeLabel.setFont(mainFontBold);
        weatherTypeLabel.setForeground(new Color(25, 25, 112));
        weatherTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        weatherTypeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 20, 0));

        fishingAdviceLabel.setFont(adviceFont);
        fishingAdviceLabel.setForeground(new Color(0, 100, 0)); // Dark green
        fishingAdviceLabel.setHorizontalAlignment(JLabel.CENTER);
        fishingAdviceLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 100, 0), 1, true));
        fishingAdviceLabel.setOpaque(true);
        fishingAdviceLabel.setBackground(new Color(225, 245, 225)); // Light green background for advice box

        backButton.setBackground(new Color(34, 139, 34)); // Forest green
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 10, 30));
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));

        // Configure program trip button
        programTripButton.setBackground(new Color(30, 144, 255)); // Dodger blue
        programTripButton.setForeground(Color.WHITE);
        programTripButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        programTripButton.setFocusPainted(false);
        programTripButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 20, 10, 20));
        programTripButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        programTripButton.addActionListener(e -> programFishingTrip());

        // Layout with GroupLayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(titleLabel)
                        .addComponent(weatherIconLabel)
                        .addComponent(temperatureLabel)
                        .addComponent(dateLabel)
                        .addComponent(weatherTypeLabel)
                        .addComponent(fishingAdviceLabel)
                        .addComponent(programTripButton) // Add program trip button
                        .addComponent(backButton)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(titleLabel)
                        .addGap(30)
                        .addComponent(weatherIconLabel)
                        .addGap(15)
                        .addComponent(temperatureLabel)
                        .addGap(10)
                        .addComponent(dateLabel)
                        .addGap(10)
                        .addComponent(weatherTypeLabel)
                        .addGap(25)
                        .addComponent(fishingAdviceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25)
                        .addComponent(programTripButton) // Add program trip button
                        .addGap(15)
                        .addComponent(backButton)
                        .addGap(20)
        );
    }

    /**
     * Program a fishing trip - main method for trip programming functionality
     */
    public void programFishingTrip() {
        System.out.println("=== programFishingTrip() called ===");

        // Ask user if they want to program a trip
        int confirmTrip = JOptionPane.showConfirmDialog(
                this,
                "Do you want to program a fishing trip?",
                "Program Fishing Trip",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirmTrip != JOptionPane.YES_OPTION) {
            System.out.println("User declined to program a trip");
            return;
        }

        // Ask for trip type (Morning or Night)
        String[] tripOptions = {"Morning Trip", "Night Trip"};
        String tripType = (String) JOptionPane.showInputDialog(
                this,
                "What type of trip would you like?",
                "Trip Type Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                tripOptions,
                tripOptions[0]
        );

        if (tripType == null) {
            System.out.println("User cancelled trip type selection");
            return;
        }

        // Ask user if they want to enter a specific date or use next available
        int dateChoice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to enter a specific date?\n(No = Use next available date)",
                "Date Selection",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        LocalDate tripDate;
        String tripHour;

        if (dateChoice == JOptionPane.YES_OPTION) {
            // User wants to enter specific date
            tripDate = getUserSpecifiedDate();
            if (tripDate == null) {
                System.out.println("Invalid date entered, operation cancelled");
                return;
            }
        } else {
            // Use next available date
            tripDate = getNextAvailableDate(tripType);
        }

        // Set trip hour based on type
        if (tripType.equals("Morning Trip")) {
            tripHour = "06:00";
        } else {
            tripHour = "20:00";
        }

        // Save trip to file
        saveTripToFile(tripDate, tripHour, tripType);

        // Show confirmation message
        JOptionPane.showMessageDialog(
                this,
                String.format("Trip programmed successfully!\n\nDate: %s\nTime: %s\nType: %s\n\nTrip saved to trips.txt",
                        tripDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        tripHour,
                        tripType),
                "Trip Confirmation",
                JOptionPane.INFORMATION_MESSAGE
        );

        System.out.println("=== Trip programming completed ===");
    }

    /**
     * Get user-specified date through input dialog
     */
    private LocalDate getUserSpecifiedDate() {
        JTextField dateField = new JTextField(10);
        JPanel datePanel = new JPanel();
        datePanel.add(new JLabel("Enter date (YYYY-MM-DD): "));
        datePanel.add(dateField);

        int result = JOptionPane.showConfirmDialog(
                this,
                datePanel,
                "Enter Trip Date",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String dateInput = dateField.getText().trim();
            try {
                LocalDate inputDate = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                // Check if date is in the past
                if (inputDate.isBefore(LocalDate.now())) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Cannot schedule trip for past date. Please enter a future date.",
                            "Invalid Date",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return null;
                }

                return inputDate;
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Invalid date format. Please use YYYY-MM-DD format (e.g., 2025-05-28)",
                        "Date Format Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }

        return null;
    }

    /**
     * Get next available date for the specified trip type
     */
    private LocalDate getNextAvailableDate(String tripType) {
        LocalDate today = LocalDate.now();
        LocalDate nextDate = today.plusDays(1); // Start with tomorrow

        return nextDate;
    }

    /**
     * Save trip information to text file
     */
    private void saveTripToFile(LocalDate tripDate, String tripHour, String tripType) {
        try {
            FileWriter writer = new FileWriter("trips.txt", true); // Append mode

            String tripEntry = String.format("Trip %s %s %s%n",
                    tripDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    tripHour,
                    tripType
            );

            writer.write(tripEntry);
            writer.close();

            System.out.println("Trip saved to file: " + tripEntry.trim());

        } catch (IOException e) {
            System.err.println("Error saving trip to file: " + e.getMessage());
            JOptionPane.showMessageDialog(
                    this,
                    "Error saving trip to file: " + e.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
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
     * Show card layout and present good weather info
     */
    public void cardLayoutShow() {
        System.out.println("=== GoodWeatherReportPage.cardLayoutShow() called ===");
        presentInfoGOOD();
        cardLayout.show(contentPanel, "goodWeatherReportPage");
        System.out.println("=== Card layout shown for good weather ===");
    }

    /**
     * Present good weather information
     */
    public void presentInfoGOOD() {
        System.out.println("=== presentInfoGOOD() called ===");

        // Ensure we have weather data
        ensureWeatherData();

        if (weatherDetails == null) {
            System.out.println("ERROR: weatherDetails is still null after ensureWeatherData()");
            // Set default values to prevent crashes
            temperatureLabel.setText("Temperature: N/A");
            dateLabel.setText("Date: N/A");
            weatherTypeLabel.setText("Weather: N/A");
            weatherIconLabel.setText("☀️ ☀️ ☀️");
            return;
        }

        // Update labels with weather information
        double temp = weatherDetails.getTemperature();
        String date = weatherDetails.getDate();
        String type = weatherDetails.getWeatherType();

        System.out.println("Updating UI with:");
        System.out.println("Temperature: " + temp);
        System.out.println("Date: " + date);
        System.out.println("Weather Type: " + type);

        temperatureLabel.setText(String.format("Temperature: %.1f°C", temp));
        dateLabel.setText("Date: " + (date != null ? date : "Unknown"));
        weatherTypeLabel.setText("Weather: " + (type != null ? type : "Unknown"));

        // Update weather icon based on weather type
        String iconToUse;
        if (type != null) {
            if (type.equalsIgnoreCase("Clear")) {
                iconToUse = "☀️";
            } else if (type.equalsIgnoreCase("Clouds")) {
                iconToUse = "⛅";
            } else {
                iconToUse = "🌤️";
            }
        } else {
            iconToUse = "🌤️";
        }

        weatherIconLabel.setText(iconToUse + " " + iconToUse + " " + iconToUse);

        System.out.println("Good weather labels updated successfully");
        System.out.println("Icon used: " + iconToUse);
        System.out.println("=== presentInfoGOOD() completed ===");
    }

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

    // Getters and Setters
    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
        this.setBackground(Color.decode(backgroundColor));
    }

    public String getEmojiIcon() {
        return emojiIcon;
    }

    public void setEmojiIcon(String emojiIcon) {
        this.emojiIcon = emojiIcon;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }
}