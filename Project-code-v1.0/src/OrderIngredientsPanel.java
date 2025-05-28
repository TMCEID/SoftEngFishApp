import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderIngredientsPanel extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel confirmationPanel;
    private Map<String, Integer> selectedItems = new HashMap<>();

    // Components that need to be accessed across methods
    private JComboBox<String> producerCombo;
    private JPanel ingredientsPanel;
    private JSpinner quantitySpinner;

    // File path for saving orders
    private static final String ORDERS_FILE_PATH = "orders.txt";

    public OrderIngredientsPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
        createConfirmationPanel();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Title panel with better spacing
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Order Ingredients");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(87, 3, 3));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        // Main content panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        // Form panel with better layout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Producer selection
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1;
        JLabel producerLabel = new JLabel("Choose Producer:");
        producerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(producerLabel, gbc);

        producerCombo = new JComboBox<>(new String[]{
                "Alexis Pappas", "Maria Karpouzi", "Nikos Psaras", "Fisherman's Choice", "Ocean Harvest"
        });
        producerCombo.setPreferredSize(new Dimension(250, 30));
        producerCombo.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        formPanel.add(producerCombo, gbc);

        // Ingredients selection with improved layout
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        JLabel ingredientsLabel = new JLabel("Select Ingredients:");
        ingredientsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(ingredientsLabel, gbc);

        ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new GridLayout(0, 2, 10, 5)); // 2 columns for better organization
        ingredientsPanel.setBackground(Color.WHITE);
        ingredientsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(87, 3, 3), 1),
                        "Available Ingredients",
                        0, 0,
                        new Font("Arial", Font.BOLD, 12),
                        new Color(87, 3, 3)
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        String[] ingredients = {"Tzipoura", "Lavraki", "Sargos", "Fagri", "Octopus",
                "Mussels", "Shrimp", "Squid", "Sea Bass", "Red Mullet"};

        for (String ingredient : ingredients) {
            JCheckBox checkBox = new JCheckBox(ingredient);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
            checkBox.setBackground(Color.WHITE);
            ingredientsPanel.add(checkBox);
        }

        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);
        ingredientsScroll.setPreferredSize(new Dimension(400, 180));
        ingredientsScroll.setBackground(Color.WHITE);
        ingredientsScroll.setBorder(BorderFactory.createEmptyBorder());

        gbc.gridy = 2;
        formPanel.add(ingredientsScroll, gbc);

        // Quantity selection
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1;
        JLabel quantityLabel = new JLabel("Quantity per ingredient:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(quantityLabel, gbc);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.setPreferredSize(new Dimension(100, 30));
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Button panel with improved styling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton confirmButton = createStyledButton("Confirm Selection", true);
        confirmButton.addActionListener(e -> handleConfirmSelection());

        JButton cancelButton = createStyledButton("Cancel Order", false);
        cancelButton.addActionListener(e -> cancelOrder());

        JButton backButton = createStyledButton("Back to Home", false);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }

    private JButton createStyledButton(String text, boolean isPrimary) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setPreferredSize(new Dimension(150, 35));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        if (isPrimary) {
            button.setBackground(new Color(87, 3, 3));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(200, 200, 200));
            button.setForeground(new Color(60, 60, 60));
        }

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (isPrimary) {
                    button.setBackground(new Color(120, 10, 10));
                } else {
                    button.setBackground(new Color(180, 180, 180));
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (isPrimary) {
                    button.setBackground(new Color(87, 3, 3));
                } else {
                    button.setBackground(new Color(200, 200, 200));
                }
            }
        });

        return button;
    }

    private void handleConfirmSelection() {
        // Clear previous selections
        selectedItems.clear();

        // Collect selected ingredients
        int quantity = (Integer) quantitySpinner.getValue();
        for (Component comp : ingredientsPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    selectedItems.put(checkBox.getText(), quantity);
                }
            }
            cardLayout.show(contentPanel, "MainAppPanel");
        }

        // Validation
        if (selectedItems.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select at least one ingredient before confirming.",
                    "No Ingredients Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Update confirmation panel and show it
        updateConfirmationPanel(producerCombo.getSelectedItem().toString());
        cardLayout.show(contentPanel, "confirmationPanel");
    }

    private void createConfirmationPanel() {
        confirmationPanel = new JPanel(new BorderLayout());
        confirmationPanel.setBackground(Color.WHITE);
        confirmationPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Title section
        JPanel titleSection = new JPanel(new BorderLayout());
        titleSection.setBackground(Color.WHITE);
        titleSection.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        JLabel titleLabel = new JLabel("Order Confirmation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(87, 3, 3));
        titleSection.add(titleLabel, BorderLayout.NORTH);

        JLabel producerLabel = new JLabel("", SwingConstants.CENTER);
        producerLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        producerLabel.setForeground(new Color(100, 100, 100));
        titleSection.add(producerLabel, BorderLayout.SOUTH);

        confirmationPanel.add(titleSection, BorderLayout.NORTH);

        // Order details with better formatting
        JTextArea orderDetails = new JTextArea();
        orderDetails.setEditable(false);
        orderDetails.setFont(new Font("Consolas", Font.PLAIN, 14));
        orderDetails.setBackground(new Color(248, 248, 248));
        orderDetails.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(orderDetails);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        confirmationPanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton placeOrderButton = createStyledButton("Place Order", true);
        placeOrderButton.addActionListener(e -> placeOrder());

        JButton backToEditButton = createStyledButton("Back to Edit", false);
        backToEditButton.addActionListener(e -> cardLayout.show(contentPanel, "orderIngredientsPanel"));

        buttonPanel.add(placeOrderButton);
        buttonPanel.add(backToEditButton);

        confirmationPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add to content panel
        contentPanel.add(confirmationPanel, "confirmationPanel");
    }

    // NEW METHOD: Save order to file and handle completion
    private void placeOrder() {
        try {
            boolean success = saveOrderToFile();
            if (success) {
                JOptionPane.showMessageDialog(this,
                        "Order completed successfully!\nΗ παραγγελία ολοκληρώθηκε επιτυχώς!\n\nOrder has been saved to " + ORDERS_FILE_PATH,
                        "Order Success",
                        JOptionPane.INFORMATION_MESSAGE);
                resetForm();
                cardLayout.show(contentPanel, "MainAppPanel");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Order was processed but there was an issue saving to file.\nPlease check if you have write permissions.",
                        "File Save Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "An error occurred while processing your order: " + ex.getMessage(),
                    "Order Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // NEW METHOD: Save order details to text file
    private boolean saveOrderToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE_PATH, true))) {
            // Add timestamp and separator
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = dateFormat.format(new Date());

            writer.println("==========================================");
            writer.println("NEW ORDER - " + timestamp);
            writer.println("==========================================");
            writer.println("Producer: " + producerCombo.getSelectedItem().toString());
            writer.println();
            writer.println("INGREDIENTS ORDERED:");
            writer.println("--------------------");

            int totalItems = 0;
            for (Map.Entry<String, Integer> entry : selectedItems.entrySet()) {
                writer.println(String.format("%-20s : %d", entry.getKey(), entry.getValue()));
                totalItems += entry.getValue();
            }

            writer.println("--------------------");
            writer.println("Total Items: " + totalItems);
            writer.println();
            writer.println();

            return true;
        } catch (IOException e) {
            System.err.println("Error saving order to file: " + e.getMessage());
            return false;
        }
    }

    private void updateConfirmationPanel(String producer) {
        // Find components in confirmation panel
        JTextArea orderDetails = findOrderDetailsTextArea(confirmationPanel);
        JLabel producerLabel = findProducerLabel(confirmationPanel);

        if (orderDetails != null && producerLabel != null) {
            producerLabel.setText("Producer: " + producer);

            StringBuilder sb = new StringBuilder();
            sb.append("ORDER SUMMARY\n");
            sb.append("═".repeat(50)).append("\n\n");
            sb.append(String.format("%-25s %10s\n", "INGREDIENT", "QUANTITY"));
            sb.append("─".repeat(50)).append("\n");

            int totalItems = 0;
            for (Map.Entry<String, Integer> entry : selectedItems.entrySet()) {
                sb.append(String.format("%-25s %10d\n", entry.getKey(), entry.getValue()));
                totalItems += entry.getValue();
            }

            sb.append("─".repeat(50)).append("\n");
            sb.append(String.format("%-25s %10d\n", "TOTAL ITEMS:", totalItems));
            sb.append("\nPlease review your order before confirming.");
            sb.append("\n\nNote: Order will be saved to orders.txt file.");

            orderDetails.setText(sb.toString());
        }
    }

    private JTextArea findOrderDetailsTextArea(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JScrollPane) {
                Component view = ((JScrollPane) comp).getViewport().getView();
                if (view instanceof JTextArea) {
                    return (JTextArea) view;
                }
            } else if (comp instanceof Container) {
                JTextArea result = findOrderDetailsTextArea((Container) comp);
                if (result != null) return result;
            }
        }
        return null;
    }

    private JLabel findProducerLabel(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JPanel) {
                for (Component subComp : ((JPanel) comp).getComponents()) {
                    if (subComp instanceof JLabel) {
                        JLabel label = (JLabel) subComp;
                        if (label.getText().startsWith("Producer:") || label.getText().isEmpty()) {
                            return label;
                        }
                    }
                }
            }
        }
        return null;
    }

    private void cancelOrder() {
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel this order?\nAll selections will be lost.",
                "Cancel Order Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if( option == JOptionPane.YES_OPTION) {
            resetForm();
            JOptionPane.showMessageDialog(this,
                    "Order cancelled successfully!",
                    "Order Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(contentPanel, "MainAppPanel");
        }
    }

    private void resetForm() {
        // Reset all form fields
        producerCombo.setSelectedIndex(0);
        quantitySpinner.setValue(1);
        selectedItems.clear();

        // Uncheck all checkboxes
        for (Component comp : ingredientsPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setSelected(false);
            }
        }
    }
}