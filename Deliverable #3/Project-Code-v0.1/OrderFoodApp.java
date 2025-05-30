import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class OrderFoodApp extends JFrame {
    private Font defaultFont;

    public OrderFoodApp() {
        this.defaultFont = new Font("SansSerif", Font.PLAIN, 16);
        initializeFrame();
        createMainPanel();
    }

    private void initializeFrame() {
        setTitle("GReel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 790);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void createMainPanel() {
        // Create and add order ingredients panel directly
        OrderIngredientsPanel orderPanel = new OrderIngredientsPanel();
        add(orderPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new OrderFoodApp().setVisible(true);
        });
    }
}

class OrderIngredientsPanel extends JPanel {
    private JPanel confirmationPanel;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Map<String, Integer> selectedItems = new HashMap<>();
    private Map<String, JCheckBox> checkBoxMap = new HashMap<>();
    private Map<String, JSpinner> spinnerMap = new HashMap<>();
    private Font defaultFont;
    private JComboBox<String> producerCombo;

    public OrderIngredientsPanel() {
        this.defaultFont = new Font("SansSerif", Font.PLAIN, 16);
        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(cardLayout);

        initComponents();
        createConfirmationPanel();

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Show order panel initially
        cardLayout.show(contentPanel, "orderPanel");
    }

    private void initComponents() {
        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(Color.decode("#F5F5F5"));

        // Title panel with emojis
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#F5F5F5"));
        JLabel titleLabel = new JLabel("🐟 ⭐ Dish order ⭐ 🐟", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 20f));
        titleLabel.setForeground(Color.decode("#7A1F1F"));
        titleLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
        titlePanel.add(titleLabel);
        orderPanel.add(titlePanel, BorderLayout.NORTH);

        // Main content panel with scroll
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#F5F5F5"));

        // Form panel with centered content
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.decode("#F5F5F5"));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(20, 40, 30, 40));

        // Producer selection
        JLabel producerLabel = new JLabel("Choose chef:");
        producerLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        producerLabel.setForeground(Color.decode("#4A5568"));
        producerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(producerLabel);
        formPanel.add(Box.createVerticalStrut(10));

        // Updated producer combo with "Select..." as first option
        producerCombo = new StyledComboBox<>(new String[] {
                "Select...", "Alexis Pappas", "Maria Ioannou", "Nikos Fournakos", "Kostas Mpompis"
        });
        producerCombo.setFont(defaultFont.deriveFont(14f));
        producerCombo.setMaximumSize(new Dimension(420, 45));
        producerCombo.setPreferredSize(new Dimension(420, 45));
        producerCombo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(producerCombo);
        formPanel.add(Box.createVerticalStrut(20));

        // Dish selection
        JLabel dishLabel = new JLabel("Select dishes and quantities:");
        dishLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        dishLabel.setForeground(Color.decode("#4A5568"));
        dishLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(dishLabel);
        formPanel.add(Box.createVerticalStrut(10));

        JPanel dishPanel = new JPanel();
        dishPanel.setLayout(new BoxLayout(dishPanel, BoxLayout.Y_AXIS));
        dishPanel.setBackground(Color.decode("#E2E8F0"));
        dishPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#7A1F1F"), 2),
                BorderFactory.createEmptyBorder(15, 20, 15, 20)));

        String[] Dish = { "Grilled Mackerel w/ Lemon&Herbs", "Salmon Teriyaki",
                "Thai Steamed Fish w/ Lime&Chili", "Tilapia Veracruzana", "Kalamaraki w/Lemon",
                "Pesto Salmon w/ Breadcrumb Crust", "Sheet Pan Shrimp", "Fish Sticks",
                "Baked Red Snapper w/ Zhoug" };

        for (String dish : Dish) {
            // Create a panel for each dish row
            JPanel dishRow = new JPanel(new BorderLayout());
            dishRow.setBackground(Color.decode("#E2E8F0"));
            dishRow.setBorder(new EmptyBorder(5, 0, 5, 0));

            // Checkbox for dish selection
            JCheckBox checkBox = new JCheckBox(dish);
            checkBox.setFont(defaultFont.deriveFont(14f));
            checkBox.setForeground(Color.decode("#4A5568"));
            checkBox.setBackground(Color.decode("#E2E8F0"));
            checkBox.setFocusPainted(false);
            checkBoxMap.put(dish, checkBox);

            // Quantity spinner for each dish
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            quantitySpinner.setFont(defaultFont.deriveFont(12f));
            quantitySpinner.setPreferredSize(new Dimension(80, 30));
            quantitySpinner.setMaximumSize(new Dimension(80, 30));
            quantitySpinner.setEnabled(false); // Initially disabled

            // Style the spinner
            JComponent editor = quantitySpinner.getEditor();
            if (editor instanceof JSpinner.DefaultEditor) {
                JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
                textField.setBackground(Color.decode("#F8F9FA"));
                textField.setForeground(Color.decode("#4A5568"));
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.decode("#CBD5E0"), 1),
                        BorderFactory.createEmptyBorder(4, 8, 4, 8)));
            }

            spinnerMap.put(dish, quantitySpinner);

            // Add listener to checkbox to enable/disable spinner
            checkBox.addActionListener(e -> {
                quantitySpinner.setEnabled(checkBox.isSelected());
                if (!checkBox.isSelected()) {
                    quantitySpinner.setValue(1); // Reset to 1 when unchecked
                }
            });

            // Create quantity label
            JLabel qtyLabel = new JLabel("Qty:");
            qtyLabel.setFont(defaultFont.deriveFont(12f));
            qtyLabel.setForeground(Color.decode("#4A5568"));

            // Panel for quantity controls
            JPanel quantityPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            quantityPanel.setBackground(Color.decode("#E2E8F0"));
            quantityPanel.add(qtyLabel);
            quantityPanel.add(quantitySpinner);

            // Add components to row
            dishRow.add(checkBox, BorderLayout.WEST);
            dishRow.add(quantityPanel, BorderLayout.EAST);

            dishPanel.add(dishRow);
        }

        JScrollPane dishScroll = new JScrollPane(dishPanel);
        dishScroll.setPreferredSize(new Dimension(420, 250));
        dishScroll.setMaximumSize(new Dimension(420, 250));
        dishScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        dishScroll.setBorder(null);
        dishScroll.setBackground(Color.decode("#F5F5F5"));
        formPanel.add(dishScroll);
        formPanel.add(Box.createVerticalStrut(30));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#F5F5F5"));

        RoundedButton confirmButton = new RoundedButton("Confirm Order", Color.decode("#008000"));
        confirmButton.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        confirmButton.setPreferredSize(new Dimension(200, 45));
        confirmButton.setMaximumSize(new Dimension(200, 45));
        confirmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmButton.addActionListener(e -> {

            if (producerCombo.getSelectedIndex() == 0) { // "Select..." is at index 0
                showToast("⚠︎ Please select a chef", Color.decode("#DC2626"));
                return;
            }

            selectedItems.clear();
            for (Map.Entry<String, JCheckBox> entry : checkBoxMap.entrySet()) {
                String ingredient = entry.getKey();
                JCheckBox checkBox = entry.getValue();
                if (checkBox.isSelected()) {
                    JSpinner spinner = spinnerMap.get(ingredient);
                    selectedItems.put(ingredient, (Integer) spinner.getValue());
                }
            }

            if (selectedItems.isEmpty()) {
                showToast("⚠︎ Please select at least one dish", Color.decode("#DC2626"));
                return;
            }

            updateConfirmationPanel(producerCombo.getSelectedItem().toString());
            cardLayout.show(contentPanel, "confirmationPanel");
        });

        RoundedButton cancelButton = new RoundedButton("Cancel Order", Color.decode("#DC2626"));
        cancelButton.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        cancelButton.setPreferredSize(new Dimension(200, 45));
        cancelButton.setMaximumSize(new Dimension(200, 45));
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(e -> showCancelOrderDialog());

        RoundedButton homeButton = new RoundedButton("Back To Home", Color.decode("#808080"));
        homeButton.setFont(defaultFont.deriveFont(Font.BOLD, 14f));
        homeButton.setPreferredSize(new Dimension(200, 45));
        homeButton.setMaximumSize(new Dimension(200, 45));
        homeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        homeButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(confirmButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(cancelButton);
        buttonPanel.add(Box.createVerticalStrut(10));
        buttonPanel.add(homeButton);

        formPanel.add(buttonPanel);
        formPanel.add(Box.createVerticalStrut(20)); // Extra space at bottom

        // Wrap form panel in scroll pane to handle overflow
        JScrollPane mainScrollPane = new JScrollPane(formPanel);
        mainScrollPane.setBorder(null);
        mainScrollPane.setBackground(Color.decode("#F5F5F5"));
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        mainPanel.add(mainScrollPane, BorderLayout.CENTER);
        orderPanel.add(mainPanel, BorderLayout.CENTER);

        // Add to content panel
        contentPanel.add(orderPanel, "orderPanel");
    }

    private void createConfirmationPanel() {
        confirmationPanel = new JPanel(new BorderLayout());
        confirmationPanel.setBackground(Color.decode("#F5F5F5"));

        // Title with emojis
        JLabel titleLabel = new JLabel("🐟 ⭐ Order Confirmation ⭐ 🐟", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 20f));
        titleLabel.setForeground(Color.decode("#7A1F1F"));
        titleLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
        confirmationPanel.add(titleLabel, BorderLayout.NORTH);

        // Content panel
        JPanel contentPanelConfirm = new JPanel(new BorderLayout());
        contentPanelConfirm.setBackground(Color.decode("#F5F5F5"));
        contentPanelConfirm.setBorder(new EmptyBorder(0, 40, 40, 40));

        // Producer label
        JLabel producerLabel = new JLabel("", SwingConstants.CENTER);
        producerLabel.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        producerLabel.setForeground(Color.decode("#4A5568"));
        producerLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        contentPanelConfirm.add(producerLabel, BorderLayout.NORTH);

        // Order details
        JTextArea orderDetails = new JTextArea();
        orderDetails.setEditable(false);
        orderDetails.setFont(new Font("Monospaced", Font.PLAIN, 14));
        orderDetails.setBackground(Color.decode("#E2E8F0"));
        orderDetails.setForeground(Color.decode("#4A5568"));
        orderDetails.setBorder(new EmptyBorder(20, 20, 20, 20));

        JScrollPane scrollPane = new JScrollPane(orderDetails);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#7A1F1F"), 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        scrollPane.setBackground(Color.decode("#F5F5F5"));
        scrollPane.setPreferredSize(new Dimension(500, 300));
        contentPanelConfirm.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.decode("#F5F5F5"));
        buttonPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        RoundedButton placeOrderButton = new RoundedButton("Place Order", Color.decode("#059669"));
        placeOrderButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        placeOrderButton.addActionListener(e -> {
            showToast("✅ Order completed successfully!", Color.decode("#059669"));
        });

        RoundedButton backButton = new RoundedButton("Go back", Color.decode("#718096"));
        backButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "orderPanel"));

        buttonPanel.add(placeOrderButton);
        buttonPanel.add(backButton);

        contentPanelConfirm.add(buttonPanel, BorderLayout.SOUTH);
        confirmationPanel.add(contentPanelConfirm, BorderLayout.CENTER);

        // Add to content panel
        contentPanel.add(confirmationPanel, "confirmationPanel");
    }

    private void updateConfirmationPanel(String producer) {
        JTextArea orderDetails = null;
        JLabel producerLabel = null;

        // Find components in confirmation panel
        JPanel contentPanelConfirm = (JPanel) confirmationPanel.getComponent(1);
        producerLabel = (JLabel) contentPanelConfirm.getComponent(0);
        JScrollPane scrollPane = (JScrollPane) contentPanelConfirm.getComponent(1);
        orderDetails = (JTextArea) scrollPane.getViewport().getView();

        if (orderDetails != null && producerLabel != null) {
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
    }

    ;

    private void resetForm() {
        // Reset producer selection
        producerCombo.setSelectedIndex(0);

        // Reset all checkboxes and spinners
        for (Map.Entry<String, JCheckBox> entry : checkBoxMap.entrySet()) {
            entry.getValue().setSelected(false);
            JSpinner spinner = spinnerMap.get(entry.getKey());
            spinner.setValue(1);
            spinner.setEnabled(false);
        }

        // Clear selected items
        selectedItems.clear();
    }

    private void showCancelOrderDialog() {
        JWindow toast = new JWindow();
        toast.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 0, 0, 40));
                g2.fillRoundRect(3, 3, getWidth() - 3, getHeight() - 3, 25, 25);

                g2.setColor(Color.decode("#DC2626"));
                g2.fillRoundRect(0, 0, getWidth() - 3, getHeight() - 3, 25, 25);
                g2.dispose();
            }
        };
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(15, 25, 15, 25));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel mainLabel = new JLabel("⚠️ Cancel Order?");
        mainLabel.setForeground(Color.WHITE);
        mainLabel.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        mainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(mainLabel);

        panel.add(Box.createVerticalStrut(5));
        JLabel subLabel = new JLabel("This will clear your current selections");
        subLabel.setForeground(new Color(255, 255, 255, 200));
        subLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 12f));
        subLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subLabel);

        panel.add(Box.createVerticalStrut(15));

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setOpaque(false);

        // Confirm cancel button
        RoundedButton confirmBtn = new RoundedButton("Yes, Cancel", Color.decode("#DC2626").darker());
        confirmBtn.setFont(defaultFont.deriveFont(Font.BOLD, 12f));
        confirmBtn.setPreferredSize(new Dimension(100, 30));
        confirmBtn.setMaximumSize(new Dimension(100, 30));

        // Keep order button
        RoundedButton keepBtn = new RoundedButton("Keep Order", Color.decode("#059669"));
        keepBtn.setFont(defaultFont.deriveFont(Font.BOLD, 12f));
        keepBtn.setPreferredSize(new Dimension(100, 30));
        keepBtn.setMaximumSize(new Dimension(100, 30));

        buttonPanel.add(confirmBtn);
        buttonPanel.add(keepBtn);
        panel.add(buttonPanel);

        toast.add(panel);
        toast.pack();

        // Position toast in center of the window
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            toast.setLocation(parentWindow.getX() + (parentWindow.getWidth() - toast.getWidth()) / 2,
                    parentWindow.getY() + parentWindow.getHeight() / 2 - 50);
        }
        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        // Auto-dismiss after 6 seconds
        Timer dismissTimer = new Timer(6000, e -> toast.dispose());
        dismissTimer.setRepeats(false);
        dismissTimer.start();

        // Button actions
        confirmBtn.addActionListener(e -> {
            toast.dispose();
            selectedItems.clear();
            resetForm();
            showToast("✅ Order cancelled successfully!", Color.decode("#059669"));
        });

        keepBtn.addActionListener(e -> toast.dispose());

        // Click outside to dismiss
        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getSource() == panel) {
                    toast.dispose();
                }
            }
        });
    }

    private void showToast(String message, Color backgroundColor) {
        JWindow toast = new JWindow();
        toast.setBackground(new Color(0, 0, 0, 0));

        JPanel panel = new JPanel() {
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

        // Position toast in center of the window
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            toast.setLocation(parentWindow.getX() + (parentWindow.getWidth() - toast.getWidth()) / 2,
                    parentWindow.getY() + parentWindow.getHeight() / 2);
        }
        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        // Fade out animation
        new Timer(50, new AbstractAction() {
            float opacity = 1.0f;

            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;
                if (opacity <= 0) {
                    toast.dispose();
                    ((Timer) e.getSource()).stop();
                } else {
                    try {
                        toast.setOpacity(opacity);
                    } catch (Exception ex) {
                        // Handle opacity not supported
                        toast.dispose();
                        ((Timer) e.getSource()).stop();
                    }
                }
            }
        }) {
            {
                setInitialDelay(1000);
                start();
            }
        };
    }
}

// Styled ComboBox to match the design
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

// Rounded Button class
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

        // Shadow
        g2.setColor(new Color(0, 0, 0, 20));
        g2.fillRoundRect(2, 2, getWidth(), getHeight(), 25, 25);

        // Button background
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
        return new Color(Math.min(255, color.getRed() + 20), Math.min(255, color.getGreen() + 20),
                Math.min(255, color.getBlue() + 20));
    }

    public boolean isContentAreaFilled() {
        return false;
    }
}