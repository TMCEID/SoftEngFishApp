import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class OrderIngredientsPanel extends javax.swing.JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    
    public OrderIngredientsPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
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
        
        // Main content panel (holds form and summary/buttons)
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Form panel - using GridBagLayout for precise control
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.anchor = GridBagConstraints.WEST;
        
        // Form components
        JComboBox<String> producerCombo = new JComboBox<>(new String[]{"Alexis Pappas", "Maria Karpouzi", "Nikos Psaras"});
        producerCombo.setPreferredSize(new Dimension(200, 25));
        
        JComboBox<String> ingredientsCombo = new JComboBox<>(new String[]{"Tzipoura", "Lavraki", "Sargos", "Fagri"});
        ingredientsCombo.setPreferredSize(new Dimension(200, 25));
        
        JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.setPreferredSize(new Dimension(80, 25));
        
        // Add components to form
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Choose producer:"), gbc);
        
        gbc.gridx = 1;
        formPanel.add(producerCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Choose ingredients:"), gbc);
        
        gbc.gridx = 1;
        formPanel.add(ingredientsCombo, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Enter quantity:"), gbc);
        
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);
        
        mainPanel.add(formPanel, BorderLayout.NORTH);
        
        // Order summary panel
        JTextArea orderSummary = new JTextArea(5, 20);
        orderSummary.setEditable(false);
        orderSummary.setText("Order Summary\nIngredients\tQty.\n");
        JScrollPane summaryScroll = new JScrollPane(orderSummary);
        
        // Update summary when selections change
        producerCombo.addActionListener(e -> updateSummary(orderSummary, producerCombo, ingredientsCombo, quantitySpinner));
        ingredientsCombo.addActionListener(e -> updateSummary(orderSummary, producerCombo, ingredientsCombo, quantitySpinner));
        quantitySpinner.addChangeListener(e -> updateSummary(orderSummary, producerCombo, ingredientsCombo, quantitySpinner));
        
        mainPanel.add(summaryScroll, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton placeOrderButton = new JButton("Place order");
        placeOrderButton.setBackground(new Color(87, 3, 3));
        placeOrderButton.setForeground(Color.WHITE);
        placeOrderButton.addActionListener(e -> placeOrder());
        
        JButton cancelButton = new JButton("Cancel order");
        cancelButton.setBackground(new Color(87, 3, 3));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(e -> cancelOrder());
        
        JButton backButton = new JButton("Back to home");
        backButton.setBackground(new Color(87, 3, 3));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "mainAppPanel"));
        
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(backButton);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void updateSummary(JTextArea summary, JComboBox<String> producer, 
                             JComboBox<String> ingredients, JSpinner quantity) {
        summary.setText("Order Summary\nIngredients\tQty.\n" +
                       ingredients.getSelectedItem() + "\t" + quantity.getValue() + 
                       "\n\nProducer: " + producer.getSelectedItem());
    }
    
    private void placeOrder() {
        JOptionPane.showMessageDialog(this, 
            "Order placed successfully.\nYou may now go back to home", 
            "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void cancelOrder() {
        JOptionPane.showMessageDialog(this, 
            "Order was cancelled successfully!", 
            "Cancelled", JOptionPane.INFORMATION_MESSAGE);
    }
}