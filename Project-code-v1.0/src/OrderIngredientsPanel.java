import javax.swing.*;
import java.awt.*;
//import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class OrderIngredientsPanel extends javax.swing.JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel confirmationPanel;
    private Map<String, Integer> selectedItems = new HashMap<>();
    
    public OrderIngredientsPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
        createConfirmationPanel();
    }
    
    private void initComponents() {
        setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Order Ingredients");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(87, 3, 3));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Producers list
        JComboBox<String> producerCombo = new JComboBox<>(new String[]{
            "Alexis Pappas", "Maria Karpouzi", "Nikos Psaras", "Fisherman's Choice", "Ocean Harvest"
        });
        producerCombo.setPreferredSize(new Dimension(200, 25));
        
        // Ingredients list (with checkboxes for multiple selection)
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder("Select Ingredients"));
        
        String[] ingredients = {"Tzipoura", "Lavraki", "Sargos", "Fagri", "Octopus", 
                               "Mussels", "Shrimp", "Squid", "Sea Bass", "Red Mullet"};
        
        for (String ingredient : ingredients) {
            JCheckBox checkBox = new JCheckBox(ingredient);
            ingredientsPanel.add(checkBox);
        }
        
        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);
        ingredientsScroll.setPreferredSize(new Dimension(250, 150));
        
        // Quantity spinner
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.setPreferredSize(new Dimension(80, 25));
        
        // Add components to form
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Choose producer:"), gbc);
        
        gbc.gridx = 1;
        formPanel.add(producerCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        formPanel.add(new JLabel("Select ingredients:"), gbc);
        
        gbc.gridy = 2;
        formPanel.add(ingredientsScroll, gbc);
        
        gbc.gridy = 3;
        formPanel.add(new JLabel("Enter quantity:"), gbc);
        
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        formPanel.add(quantitySpinner, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.setBackground(new Color(87, 3, 3));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            // Collect selected ingredients
            selectedItems.clear();
            for (Component comp : ingredientsPanel.getComponents()) {
                if (comp instanceof JCheckBox) {
                    JCheckBox checkBox = (JCheckBox) comp;
                    if (checkBox.isSelected()) {
                        selectedItems.put(checkBox.getText(), (Integer) quantitySpinner.getValue());
                    }
                }
            }
            
            if (selectedItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please select at least one ingredient", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            updateConfirmationPanel(producerCombo.getSelectedItem().toString());
            cardLayout.show(contentPanel, "confirmationPanel");
        });
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(87, 3, 3));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> cancelOrder());
        
        JButton backButton = new JButton("Back to home");
        backButton.setBackground(new Color(87, 3, 3));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));
        
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void createConfirmationPanel() {
        confirmationPanel = new JPanel(new BorderLayout());
        confirmationPanel.setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("Order Confirmation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(87, 3, 3));
        confirmationPanel.add(titleLabel, BorderLayout.NORTH);
        
        // Order details
        JTextArea orderDetails = new JTextArea();
        orderDetails.setEditable(false);
        orderDetails.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(orderDetails);
        confirmationPanel.add(scrollPane, BorderLayout.CENTER);
        
        // Producer label
        JLabel producerLabel = new JLabel("", SwingConstants.CENTER);
        producerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        confirmationPanel.add(producerLabel, BorderLayout.NORTH);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        
        JButton placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBackground(new Color(87, 3, 3));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, 
                "Η παραγγελία ολοκληρώθηκε επιτυχώς", 
                "Success", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(contentPanel, "mainAppPanel");
        });
        
        JButton backButton = new JButton("Back to Edit");
        backButton.setBackground(new Color(87, 3, 3));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "orderIngredientsPanel"));
        
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(backButton);
        
        confirmationPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add to content panel (you'll need to modify your Main class to include this)
        contentPanel.add(confirmationPanel, "confirmationPanel");
    }
    
    private void updateConfirmationPanel(String producer) {
        JTextArea orderDetails = null;
        JLabel producerLabel = null;
        
        // Find components in confirmation panel
        for (Component comp : confirmationPanel.getComponents()) {
            if (comp instanceof JScrollPane) {
                orderDetails = (JTextArea) ((JScrollPane) comp).getViewport().getView();
            } else if (comp instanceof JLabel && comp != confirmationPanel.getComponent(0)) {
                producerLabel = (JLabel) comp;
            }
        }
        
        if (orderDetails != null && producerLabel != null) {
            producerLabel.setText("Producer: " + producer);
            
            StringBuilder sb = new StringBuilder("Order Summary:\n\n");
            sb.append("Ingredient\t\tQuantity\n");
            sb.append("--------------------------------\n");
            
            for (Map.Entry<String, Integer> entry : selectedItems.entrySet()) {
                sb.append(entry.getKey()).append("\t\t").append(entry.getValue()).append("\n");
            }
            
            orderDetails.setText(sb.toString());
        }
    }
    
    private void cancelOrder() {
        int option = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to cancel this order?", 
            "Cancel Order", JOptionPane.YES_NO_OPTION);
        
        if (option == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, 
                "Order was cancelled successfully!", 
                "Cancelled", JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(contentPanel, "mainAppPanel");
        }
    }
}