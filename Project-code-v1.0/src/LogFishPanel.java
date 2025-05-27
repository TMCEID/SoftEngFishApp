import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class LogFishPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;

    private JComboBox<String> fishNameCombo;
    private JSpinner quantitySpinner;
    private JSpinner weightSpinner;

    public LogFishPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // --- Title Panel ---
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Log Fish");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(87, 3, 3));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // --- Main Panel ---
        JPanel mainPanel = new JPanel(new BorderLayout());

        // --- Form Panel ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Current Date (read-only)
        String todayDate = LocalDate.now().toString();
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Ημερομηνία Καταγραφής:"), gbc);

        JLabel dateLabel = new JLabel(todayDate);
        gbc.gridx = 1;
        formPanel.add(dateLabel, gbc);

        // Fish name (dropdown)
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Επιλέξτε Ψάρι:"), gbc);

        fishNameCombo = new JComboBox<>(new String[]{
                "Tzipoura", "Lavraki", "Fagri", "Octopus", "Sargos"
        });
        fishNameCombo.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        formPanel.add(fishNameCombo, gbc);

        // Quantity
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Ποσότητα:"), gbc);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        quantitySpinner.setPreferredSize(new Dimension(80, 25));
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);

        // Average weight
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Μέσο Βάρος (kg):"), gbc);

        weightSpinner = new JSpinner(new SpinnerNumberModel(1.0, 0.1, 100.0, 0.1));
        weightSpinner.setPreferredSize(new Dimension(80, 25));
        gbc.gridx = 1;
        formPanel.add(weightSpinner, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // --- Button Panel ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton logButton = new JButton("Finish Logging");
        logButton.setBackground(Color.decode("#800020"));
        logButton.setForeground(Color.WHITE);
        logButton.addActionListener(e -> handleLogging(todayDate));
        buttonPanel.add(logButton);

        JButton backButton = new JButton("Back to Home");
        backButton.setBackground(Color.decode("#800020"));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void handleLogging(String date) {
        String fish = (String) fishNameCombo.getSelectedItem();
        int quantity = (Integer) quantitySpinner.getValue();
        double weight = (Double) weightSpinner.getValue();
        double totalWeight = quantity * weight;

        if (totalWeight > 50.0) {
            JOptionPane.showMessageDialog(this,
                    "Παράνομη ψαριά! Το βάρος υπερβαίνει το επιτρεπόμενο όριο.",
                    "Σφάλμα",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String summary = String.format("""
                Ημερομηνία: %s
                Ψάρι: %s
                Ποσότητα: %d
                Μέσο Βάρος: %.2f kg
                Συνολικό Βάρος: %.2f kg
                """, date, fish, quantity, weight, totalWeight);

        JOptionPane.showMessageDialog(this,
                summary,
                "Καταγραφή Επιτυχής",
                JOptionPane.INFORMATION_MESSAGE);

        cardLayout.show(contentPanel, "mainAppPanel");
    }
}
