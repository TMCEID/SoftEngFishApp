import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class FishLoggingApp extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new FishLoggingApp().setVisible(true);
        });
    }

    public FishLoggingApp() {
        setTitle("Fish Logging");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 850);
        setLocationRelativeTo(null);

        add(new LogFishPanel());
    }

    private void showToast(String message, Color backgroundColor) {
        JWindow toast = new JWindow();
        toast.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 25, 25);

                g2.setColor(backgroundColor);
                g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 25, 25);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(12, 25, 12, 25));

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        panel.add(label);

        toast.add(panel);
        toast.pack();

        int x = getX() + (getWidth() - toast.getWidth()) / 2;
        int y = getY() + (getHeight() - toast.getHeight()) / 2;
        toast.setLocation(x, y);
        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        new Timer(50, new AbstractAction() {
            float opacity = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.03f;
                if (opacity <= 0) {
                    toast.dispose();
                    ((Timer) e.getSource()).stop();
                } else {
                    toast.setOpacity(opacity);
                }
            }
        }) {
            {
                setInitialDelay(2000);
                start();
            }
        };
    }
}

class LogFishPanel extends JPanel {

    private JComboBox<String> fishNameCombo;
    private JSpinner quantitySpinner;
    private JSpinner weightSpinner;
    private Font defaultFont;

    public LogFishPanel() {
        defaultFont = new Font("SansSerif", Font.PLAIN, 16);
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBorder(new EmptyBorder(20, 0, 10, 0));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Log Fish", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 28f));
        titleLabel.setForeground(Color.decode("#570303"));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 40, 10, 40));
        mainPanel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;

        String todayDate = LocalDate.now().toString();
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel dateLabel1 = new JLabel("Log Date: ");
        dateLabel1.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        formPanel.add(dateLabel1, gbc);

        JLabel dateLabel = new JLabel(todayDate);
        dateLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 14f));
        dateLabel.setForeground(Color.decode("#570303"));
        gbc.gridx = 1;
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel fishLabel = new JLabel("Choose fish:");
        fishLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        formPanel.add(fishLabel, gbc);

        String[] fishList = {
                "Select...", "Anchovy", "Carp", "Bass", "Shrimp",
                "Octopus", "Mussels", "Squid", "Sea Bass", "Red Mullet"
        };
        fishNameCombo = new JComboBox<>(fishList);
        fishNameCombo.setFont(defaultFont.deriveFont(14f));
        fishNameCombo.setPreferredSize(new Dimension(250, 35));
        gbc.gridx = 1;
        formPanel.add(fishNameCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        formPanel.add(quantityLabel, gbc);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        quantitySpinner.setPreferredSize(new Dimension(120, 35));
        quantitySpinner.setFont(defaultFont.deriveFont(14f));
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        formPanel.add(weightLabel, gbc);

        weightSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.1, 100.0, 0.1));
        weightSpinner.setPreferredSize(new Dimension(120, 40));
        weightSpinner.setFont(defaultFont.deriveFont(14f));
        gbc.gridx = 1;
        formPanel.add(weightSpinner, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0)); // Reduced top padding
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(Box.createVerticalStrut(30));

        RoundedButton logButton = new RoundedButton("Finish Logging", Color.decode("#059669"));
        logButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        logButton.setPreferredSize(new Dimension(200, 35));
        logButton.setMaximumSize(new Dimension(200, 35));
        logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logButton.addActionListener(e -> handleLogging(LocalDate.now().toString()));
        buttonPanel.add(logButton);

        buttonPanel.add(Box.createVerticalStrut(15));

        RoundedButton homeButton = new RoundedButton("Back To Home", Color.decode("#DC2626"));
        homeButton.setFont(defaultFont.deriveFont(Font.BOLD, 15f));
        homeButton.setPreferredSize(new Dimension(200, 35));
        homeButton.setMaximumSize(new Dimension(200, 35));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(homeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void handleLogging(String date) {
        String selectedFish = (String) fishNameCombo.getSelectedItem();
        int quantity = (Integer) quantitySpinner.getValue();
        double weight = (Double) weightSpinner.getValue();
        double totalWeight = quantity * weight;

        if ("Select...".equals(selectedFish)) {
            showToast("⚠︎ Please select a fish type", Color.decode("#DC2626"));
            return;
        }

        if (totalWeight > 50.0) {
            showToast("ILLEGAL CATCH! Weight exceeds 50kg limit!", Color.decode("#DC2626"));
            return;
        }

        if (quantity > 100) {
            showToast("Large quantity detected: " + quantity + " fish. Please verify!", Color.decode("#F59E0B"));
        }

        if (weight > 20.0) {
            showToast("Very large fish! Avg weight: " + String.format("%.1f", weight) + "kg",
                    Color.decode("#059669"));
        }

        String summary = String.format("""
                Date: %s
                Fish: %s
                Quantity: %d
                Net Weight: %.2f kg
                """, date, selectedFish, quantity, totalWeight);

        Timer dialogTimer = new Timer(1000, e -> {
            JOptionPane.showMessageDialog(this, summary, "Success!", JOptionPane.INFORMATION_MESSAGE);
            fishNameCombo.setSelectedIndex(0);
            quantitySpinner.setValue(1);
            weightSpinner.setValue(1.0);
        });
        dialogTimer.setRepeats(false);
        dialogTimer.start();
    }

    private void showToast(String message, Color backgroundColor) {
        JWindow toast = new JWindow();
        toast.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 25, 25);
                g2.setColor(backgroundColor);
                g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 25, 25);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(12, 25, 12, 25));

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(defaultFont.deriveFont(Font.BOLD, 15f));
        panel.add(label);

        toast.add(panel);
        toast.pack();

        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            int x = parentWindow.getX() + (parentWindow.getWidth() - toast.getWidth()) / 2;
            int y = parentWindow.getY() + (parentWindow.getHeight() - toast.getHeight()) / 2;
            toast.setLocation(x, y);
        }

        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        new Timer(50, new AbstractAction() {
            float opacity = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.03f;
                if (opacity <= 0) {
                    toast.dispose();
                    ((Timer) e.getSource()).stop();
                } else {
                    toast.setOpacity(opacity);
                }
            }
        }) {
            {
                setInitialDelay(2000);
                start();
            }
        };
    }
}

class RoundedButton extends JButton {
    private Color buttonColor;

    public RoundedButton(String text, Color color) {
        super(text);
        this.buttonColor = color;
        setContentAreaFilled(false);
        setFocusPainted(false);
        setForeground(Color.WHITE);
        setBackground(color);
        setBorder(new EmptyBorder(12, 25, 12, 25));
        setPreferredSize(new Dimension(180, 45));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(0, 0, 0, 20));
        g2.fillRoundRect(2, 2, getWidth(), getHeight(), 25, 25);
        Color bgColor = getModel().isPressed() ? buttonColor.darker()
                : (getModel().isRollover() ? brighten(buttonColor) : buttonColor);
        g2.setColor(bgColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
        super.paintComponent(g2);
        g2.dispose();
    }

    private Color brighten(Color color) {
        int r = Math.min(255, color.getRed() + 20);
        int g = Math.min(255, color.getGreen() + 20);
        int b = Math.min(255, color.getBlue() + 20);
        return new Color(r, g, b);
    }

    @Override
    public boolean isContentAreaFilled() {
        return false;
    }
}
