import javax.swing.*;
import java.awt.*;
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
        
        // Ingredients list with individual quantity spinners
        JPanel ingredientsPanel = new JPanel();
        ingredientsPanel.setLayout(new BoxLayout(ingredientsPanel, BoxLayout.Y_AXIS));
        ingredientsPanel.setBorder(BorderFactory.createTitledBorder("Select Ingredients"));
        
        String[] ingredients = {"Tzipoura", "Lavraki", "Sargos", "Fagri", "Octopus", 
                               "Mussels", "Shrimp", "Squid", "Sea Bass", "Red Mullet"};
        
        for (String ingredient : ingredients) {
            JPanel ingredientRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox(ingredient);
            JSpinner quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
            quantitySpinner.setPreferredSize(new Dimension(60, 25));
            
            // Disable spinner when checkbox is unchecked
            quantitySpinner.setEnabled(false);
            checkBox.addActionListener(e -> {
                quantitySpinner.setEnabled(checkBox.isSelected());
            });
            
            ingredientRow.add(checkBox);
            ingredientRow.add(new JLabel("Quantity:"));
            ingredientRow.add(quantitySpinner);
            ingredientRow.setAlignmentX(Component.LEFT_ALIGNMENT);
            ingredientsPanel.add(ingredientRow);
        }
        
        JScrollPane ingredientsScroll = new JScrollPane(ingredientsPanel);
        ingredientsScroll.setPreferredSize(new Dimension(350, 150));
        
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
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        
        JButton confirmButton = new JButton("Confirm Selection");
        confirmButton.setBackground(new Color(87, 3, 3));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.addActionListener(e -> {
            // Collect selected ingredients and their quantities
            selectedItems.clear();
            for (Component comp : ingredientsPanel.getComponents()) {
                if (comp instanceof JPanel) {
                    JPanel row = (JPanel) comp;
                    Component[] components = row.getComponents();
                    if (components.length >= 3 && 
                        components[0] instanceof JCheckBox && 
                        components[2] instanceof JSpinner) {
                        
                        JCheckBox checkBox = (JCheckBox) components[0];
                        JSpinner spinner = (JSpinner) components[2];
                        
                        if (checkBox.isSelected()) {
                            selectedItems.put(checkBox.getText(), (Integer) spinner.getValue());
                        }
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
/* 
 import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class OrderIngredientsPanel extends JPanel {

    private final CardLayout cardLayout;
    private final JPanel contentPanel;

    private final DefaultListModel<String> orderListModel;
    private final JList<String> orderList;
    private final JTextField ingredientInput;

    public OrderIngredientsPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;

        setBackground(Color.WHITE);
        setLayout(null); // Using absolute layout for simplicity

        JLabel titleLabel = new JLabel("Order Ingredients");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(30, 20, 300, 30);
        add(titleLabel);

        JLabel inputLabel = new JLabel("Ingredient:");
        inputLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        inputLabel.setBounds(30, 70, 100, 25);
        add(inputLabel);

        ingredientInput = new JTextField();
        ingredientInput.setFont(new Font("Arial", Font.PLAIN, 14));
        ingredientInput.setBounds(130, 70, 200, 25);
        add(ingredientInput);

        JButton addButton = new JButton("Add");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(87, 3, 3));
        addButton.setForeground(Color.WHITE);
        addButton.setBounds(340, 70, 80, 25);
        add(addButton);

        orderListModel = new DefaultListModel<>();
        orderList = new JList<>(orderListModel);
        orderList.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setBounds(30, 110, 390, 200);
        add(scrollPane);

        JButton removeButton = new JButton("Remove Selected");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
        removeButton.setBackground(new Color(120, 30, 30));
        removeButton.setForeground(Color.WHITE);
        removeButton.setBounds(30, 320, 180, 30);
        add(removeButton);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBounds(240, 320, 80, 30);
        add(backButton);

        JButton confirmButton = new JButton("Confirm Order");
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        confirmButton.setBackground(new Color(34, 139, 34));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setBounds(330, 320, 150, 30);
        add(confirmButton);

        // Populate producers combo box
        JComboBox<String> producerCombo = new JComboBox<>();
        for (Producer producer : Producer.getSampleProducers()) {
            producerCombo.addItem(producer.getFullName());
        }

        // Populate ingredients list
        for (Ingredient ingredient : Ingredient.getSampleIngredients()) {
            orderListModel.addElement(ingredient.getName());
        }


        // Event handlers
        addButton.addActionListener((ActionEvent e) -> {
            String item = ingredientInput.getText().trim();
            if (!item.isEmpty()) {
                orderListModel.addElement(item);
                ingredientInput.setText("");
            }
        });

        removeButton.addActionListener((ActionEvent e) -> {
            int selected = orderList.getSelectedIndex();
            if (selected != -1) {
                orderListModel.remove(selected);
            }
        });

        backButton.addActionListener((ActionEvent e) -> {
            cardLayout.show(contentPanel, "mainAppPanel");
        });

        confirmButton.addActionListener((ActionEvent e) -> {
            ArrayList<String> orderedItems = new ArrayList<>();
            for (int i = 0; i < orderListModel.size(); i++) {
                orderedItems.add(orderListModel.get(i));
            }

            if (orderedItems.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No ingredients selected.");
            } else {
                JOptionPane.showMessageDialog(this, "Order confirmed:\n" + String.join(", ", orderedItems));
                orderListModel.clear(); // Clear list after confirmation
            }
        });
    }
}
*/