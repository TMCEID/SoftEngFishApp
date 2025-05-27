import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class OrderIngredientsPanel extends JPanel {
    private JPanel confirmationPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Map<String, Integer> selectedItems = new HashMap<>();
    private Map<String, JCheckBox> checkBoxMap = new HashMap<>();
    private Map<String, JSpinner> spinnerMap = new HashMap<>();
    private Font defaultFont;
    private JComboBox<String> producerCombo;

    public OrderIngredientsPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.defaultFont = new Font("SansSerif", Font.PLAIN, 16);
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;

        setLayout(new BorderLayout());
        initComponents();
        createConfirmationPanel();
    }

    private void initComponents() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(Color.decode("#F5F5F5"));

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#F5F5F5"));
        JLabel titleLabel = new JLabel("🐟 ⭐ Order Ingredients ⭐ 🐟", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 20f));
        titleLabel.setForeground(Color.decode("#7A1F1F"));
        titleLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
        titlePanel.add(titleLabel);
        orderPanel.add(titlePanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F5F5F5"));

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.decode("#F5F5F5"));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(20, 40, 30, 40));

        JLabel producerLabel = new JLabel("Choose producer:");
        producerLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        producerLabel.setForeground(Color.decode("#4A5568"));
        producerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(producerLabel);
        formPanel.add(Box.createVerticalStrut(10));

        producerCombo = new StyledComboBox<>(new String[]{
                "Select...", "Alexis Pappas", "Maria Ioannou", "Nikos Fournakos", "Kostas Mpompis"
        });
        producerCombo.setFont(defaultFont.deriveFont(14f));
        producerCombo.setMaximumSize(new Dimension(420, 45));
        producerCombo.setPreferredSize(new Dimension(420, 45));
        producerCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(producerCombo);
        formPanel.add(Box.createVerticalStrut(20));

        JLabel ingredientsLabel = new JLabel("Select ingredients and quantities:");
        ingredientsLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        ingredientsLabel.setForeground(Color.decode("#4A5568"));
        ingredientsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(ingredientsLabel);
        formPanel.add(Box.createVerticalStrut(10));

        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.setBackground(Color.decode("#E2E8F0"));
        ingredientsPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#7A1F1F"), 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)));

        String[] ingredients = {"Anchovy", "Carp", "Bass", "Shrimp", "Octopus",
                "Mussels", "Squid", "Sea Bass", "Red Mullet"};

        for (String ingredient : ingredients) {
            JPanel ingredientRow = new JPanel(new BorderLayout());
            ingredientRow.setBackground(Color.decode("#E2E8F0"));
            ingredientRow.setBorder(new EmptyBorder(5, 0, 5, 0));

            JCheckBox checkBox = new JCheckBox(ingredient);
            checkBox.setFont(defaultFont.deriveFont(14f));
            checkBox.setForeground(Color.decode("#4A5568"));
            checkBox.setBackground(Color.decode("#E2E8F0"));
            checkBox.setFocusPainted(false);
            checkBoxMap.put(ingredient, checkBox);

            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            quantitySpinner.setFont(defaultFont.deriveFont(12f));
            quantitySpinner.setPreferredSize(new Dimension(80, 30));
            quantitySpinner.setMaximumSize(new Dimension(80, 30));
            quantitySpinner.setEnabled(false);

            spinnerMap.put(ingredient, quantitySpinner);

            checkBox.addActionListener(e -> {
                quantitySpinner.setEnabled(checkBox.isSelected());
                if (!checkBox.isSelected()) quantitySpinner.setValue(1);
            });

            JLabel qtyLabel = new JLabel("Qty:");
            qtyLabel.setFont(defaultFont.deriveFont(12f));
            qtyLabel.setForeground(Color.decode("#4A5568"));

            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            quantityPanel.setBackground(Color.decode("#E2E8F0"));
            quantityPanel.add(qtyLabel);
            quantityPanel.add(quantitySpinner);

            ingredientRow.add(checkBox, BorderLayout.WEST);
            ingredientRow.add(quantityPanel, BorderLayout.EAST);
            ingredientsPanel.add(ingredientRow);
        }

        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);
        ingredientsScroll.setPreferredSize(new Dimension(420, 250));
        ingredientsScroll.setMaximumSize(new Dimension(420, 250));
        ingredientsScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        ingredientsScroll.setBorder(null);
        ingredientsScroll.setBackground(Color.decode("#F5F5F5"));
        formPanel.add(ingredientsScroll);
        formPanel.add(Box.createVerticalStrut(30));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#F5F5F5"));

        RoundedButton confirmButton = new RoundedButton("Confirm Order", Color.decode("#008000"));
        confirmButton.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(e -> {
            if (producerCombo.getSelectedIndex() == 0) {
                showToast("⚠︎ Please select a producer", Color.decode("#DC2626"));
                return;
            }

            selectedItems.clear();
            for (Map.Entry<String, JCheckBox> entry : checkBoxMap.entrySet()) {
                if (entry.getValue().isSelected()) {
                    selectedItems.put(entry.getKey(), (Integer) spinnerMap.get(entry.getKey()).getValue());
                }
            }

            if (selectedItems.isEmpty()) {
                showToast("⚠︎ Please select at least one ingredient", Color.decode("#DC2626"));
                return;
            }

            updateConfirmationPanel(producerCombo.getSelectedItem().toString());
            cardLayout.show(contentPanel, "confirmationPanel");
        });

        RoundedButton cancelButton = new RoundedButton("Cancel Order", Color.decode("#DC2626"));
        cancelButton.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> showCancelOrderDialog());

        RoundedButton homeButton = new RoundedButton("Back To Home", Color.decode("#808080"));
        homeButton.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));

        buttonPanel.add(confirmButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(homeButton);

        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalStrut(20));

        JScrollPane mainScrollPane = new JScrollPane(formPanel);
        mainScrollPane.setBorder(null);
        mainScrollPane.setBackground(Color.decode("#F5F5F5"));
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(mainScrollPane, BorderLayout.CENTER);
        orderPanel.add(mainPanel, BorderLayout.CENTER);

        //contentPanel.add(orderPanel, "orderPanel");
        add(orderPanel, BorderLayout.CENTER);
    }

    private void createConfirmationPanel() {
        confirmationPanel = new JPanel(new BorderLayout());
        confirmationPanel.setBackground(Color.decode("#F5F5F5"));

        JLabel titleLabel = new JLabel("🐟 ⭐ Order Confirmation ⭐ 🐟", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 20f));
        titleLabel.setForeground(Color.decode("#7A1F1F"));
        titleLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
        confirmationPanel.add(titleLabel, BorderLayout.NORTH);

        JTextArea orderDetails = new JTextArea();
        orderDetails.setEditable(false);
        orderDetails.setFont(new Font("Monospaced", Font.PLAIN, 14));
        orderDetails.setBackground(Color.decode("#E2E8F0"));
        orderDetails.setForeground(Color.decode("#4A5568"));
        orderDetails.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel producerLabel = new JLabel("", SwingConstants.CENTER);
        producerLabel.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        producerLabel.setForeground(Color.decode("#4A5568"));
        producerLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        JPanel contentPanelConfirm = new JPanel(new BorderLayout());
        contentPanelConfirm.setBackground(Color.decode("#F5F5F5"));
        contentPanelConfirm.setBorder(new EmptyBorder(0, 40, 40, 40));
        contentPanelConfirm.add(producerLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(orderDetails);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#7A1F1F"), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        scrollPane.setPreferredSize(new Dimension(500, 300));
        contentPanelConfirm.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.decode("#F5F5F5"));
        buttonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        RoundedButton placeOrderButton = new RoundedButton("Place Order", Color.decode("#059669"));
        placeOrderButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        placeOrderButton.addActionListener(e -> {
            showToast("✅ Order completed successfully!", Color.decode("#059669"));
            cardLayout.show(contentPanel, "mainAppPanel");
        });

        RoundedButton backButton = new RoundedButton("Go back", Color.decode("#718096"));
        backButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "orderPanel"));

        buttonPanel.add(placeOrderButton);
        buttonPanel.add(backButton);

        contentPanelConfirm.add(buttonPanel, BorderLayout.SOUTH);
        confirmationPanel.add(contentPanelConfirm, BorderLayout.CENTER);
        contentPanel.add(confirmationPanel, "confirmationPanel");
    }

    private void updateConfirmationPanel(String producer) {
        JPanel contentPanelConfirm = (JPanel) confirmationPanel.getComponent(1);
        JLabel producerLabel = (JLabel) contentPanelConfirm.getComponent(0);
        JScrollPane scrollPane = (JScrollPane) contentPanelConfirm.getComponent(1);
        JTextArea orderDetails = (JTextArea) scrollPane.getViewport().getView();

        producerLabel.setText("Producer: " + producer);
        StringBuilder sb = new StringBuilder("Order Summary:\n\n");
        sb.append("Ingredient                Quantity\n");
        sb.append("----------------------------------\n");

        int totalItems = 0;
        for (Map.Entry<String, Integer> entry : selectedItems.entrySet()) {
            sb.append(String.format("%-20s %8d\n", entry.getKey(), entry.getValue()));
            totalItems += entry.getValue();
        }

        sb.append("----------------------------------\n");
        sb.append(String.format("%-20s %8d\n", "TOTAL ITEMS:", totalItems));
        orderDetails.setText(sb.toString());
    }

    private void showCancelOrderDialog() {
        resetForm();
        showToast("✅ Order cancelled successfully!", Color.decode("#059669"));
    }

    private void resetForm() {
        producerCombo.setSelectedIndex(0);
        for (Map.Entry<String, JCheckBox> entry : checkBoxMap.entrySet()) {
            entry.getValue().setSelected(false);
            JSpinner spinner = spinnerMap.get(entry.getKey());
            spinner.setValue(1);
            spinner.setEnabled(false);
        }
        selectedItems.clear();
    }

    private void showToast(String message, Color backgroundColor) {
        JOptionPane.showMessageDialog(this, message);
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
            setPreferredSize(new Dimension(160, 45));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(0, 0, 0, 20)); // Shadow
            g2.fillRoundRect(2, 2, getWidth(), getHeight(), 25, 25);

            Color bgColor = getModel().isPressed() ? buttonColor.darker()
                    : (getModel().isRollover() ? brighten(buttonColor) : buttonColor);
            g2.setColor(bgColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);

            super.paintComponent(g2);
            g2.dispose();
        }

        protected void paintBorder(Graphics g) {
        }

        private Color brighten(Color color) {
            return new Color(Math.min(255, color.getRed() + 20),
                            Math.min(255, color.getGreen() + 20),
                            Math.min(255, color.getBlue() + 20));
        }

        public boolean isContentAreaFilled() {
            return false;
        }
    }

    class StyledComboBox<T> extends JComboBox<T> {
        public StyledComboBox(T[] items) {
            super(items);
            setBackground(Color.decode("#E2E8F0"));
            setForeground(Color.decode("#4A5568"));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#7A1F1F"), 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)));
            setFocusable(false);
        }
    }

}
