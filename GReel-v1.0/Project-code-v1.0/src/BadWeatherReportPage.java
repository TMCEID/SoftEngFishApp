import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Bad Weather Report Page - displays less favorable weather information
 */
public class BadWeatherReportPage extends WeatherReportPage {

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

    /**
     * Constructor
     */
    public BadWeatherReportPage(CardLayout cardLayout, JPanel contentPanel) {
        super(cardLayout, contentPanel);
        this.backgroundColor = "#A9A9A9"; // Dark gray for bad weather
        this.emojiIcon = "🌧️";
        this.fontStyle = "Segoe UI";
        initComponents();
    }

    /**
     * Initialize components
     */
    private void initComponents() {
        // Set background color
        this.setBackground(Color.decode(backgroundColor));
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(30, 50, 30, 50)); // padding around panel

        // Initialize components
        titleLabel = new JLabel("Weather Report - Not Ideal for Fishing");
        weatherIconLabel = new JLabel(emojiIcon + " " + emojiIcon + " " + emojiIcon);
        temperatureLabel = new JLabel("Temperature: Loading...");
        dateLabel = new JLabel("Date: Loading...");
        weatherTypeLabel = new JLabel("Weather: Loading...");
        fishingAdviceLabel = new JLabel("<html><center>Bad weather today.<br>Consider staying indoors or fishing with caution.</center></html>");
        backButton = new JButton("Back to Main");

        // Configure components
        Font titleFont = new Font(fontStyle, Font.BOLD, 28);
        Font iconFont = new Font("Segoe UI Emoji", Font.PLAIN, 60);
        Font mainFontBold = new Font(fontStyle, Font.BOLD, 20);
        Font mainFontPlain = new Font(fontStyle, Font.PLAIN, 18);
        Font adviceFont = new Font(fontStyle, Font.ITALIC, 16);

        titleLabel.setFont(titleFont);
        titleLabel.setForeground(new Color(50, 50, 50)); // Darker gray
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        weatherIconLabel.setFont(iconFont);
        weatherIconLabel.setHorizontalAlignment(JLabel.CENTER);
        weatherIconLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));

        temperatureLabel.setFont(mainFontBold);
        temperatureLabel.setForeground(new Color(50, 50, 50));
        temperatureLabel.setHorizontalAlignment(JLabel.CENTER);
        temperatureLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));

        dateLabel.setFont(mainFontPlain);
        dateLabel.setForeground(new Color(50, 50, 50));
        dateLabel.setHorizontalAlignment(JLabel.CENTER);
        dateLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));

        weatherTypeLabel.setFont(mainFontBold);
        weatherTypeLabel.setForeground(new Color(50, 50, 50));
        weatherTypeLabel.setHorizontalAlignment(JLabel.CENTER);
        weatherTypeLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 20, 0));

        fishingAdviceLabel.setFont(adviceFont);
        fishingAdviceLabel.setForeground(new Color(139, 0, 0)); // Dark red
        fishingAdviceLabel.setHorizontalAlignment(JLabel.CENTER);
        fishingAdviceLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(139, 0, 0), 1, true));
        fishingAdviceLabel.setOpaque(true);
        fishingAdviceLabel.setBackground(new Color(255, 230, 230)); // Light red background for advice box

        backButton.setBackground(new Color(178, 34, 34)); // Firebrick red
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font(fontStyle, Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 30, 10, 30));
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));

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
                        .addGap(40)
                        .addComponent(backButton)
                        .addGap(20)
        );
    }

    /**
     * Show card layout and present bad weather info
     */
    @Override
    public void cardLayoutShow() {
        presentInfoBAD();
        cardLayout.show(contentPanel, "badWeatherReportPage");
    }

    /**
     * Present bad weather information
     */
    public void presentInfoBAD() {
        System.out.println("Presenting bad weather info...");

        // Only fetch if weather details is null or has no data
        if (weatherDetails == null || weatherDetails.getWeatherType().equals("Unknown")) {
            System.out.println("Fetching weather info because data is missing");
            fetchWeatherInfo();
        } else {
            System.out.println("Using existing weather data");
        }

        // Update labels with weather information
        temperatureLabel.setText(String.format("Temperature: %.1f°C", weatherDetails.getTemperature()));
        dateLabel.setText("Date: " + weatherDetails.getDate());
        weatherTypeLabel.setText("Weather: " + weatherDetails.getWeatherType());

        // Update weather icon based on weather type
        String weatherType = weatherDetails.getWeatherType();
        if (weatherType.equalsIgnoreCase("Rain") || weatherType.equalsIgnoreCase("Thunderstorm")) {
            emojiIcon = "🌧️";
        } else if (weatherType.equalsIgnoreCase("Snow")) {
            emojiIcon = "❄️";
        } else if (weatherType.equalsIgnoreCase("Drizzle")) {
            emojiIcon = "🌦️";
        } else if (weatherType.equalsIgnoreCase("Fog")) {
            emojiIcon = "🌫️";
        } else {
            emojiIcon = "☁️";
        }
        weatherIconLabel.setText(emojiIcon + " " + emojiIcon + " " + emojiIcon);

        System.out.println("Bad weather labels updated with: " +
                weatherDetails.getTemperature() + "°C, " +
                weatherDetails.getWeatherType());
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