import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class OrderFoodGui extends JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JPanel chartPanel;
    private Map<String, Integer> selectedDishes = new LinkedHashMap<>();
    private JPanel dishesPanel;
    private JSpinner quantitySpinner;

    private static final String CHART_FILE_PATH = "Chart.txt";

    public OrderFoodGui(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
        createChartPanel();
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        titlePanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("Order Food");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(87, 3, 3));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        mainPanel.setBackground(Color.WHITE);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Dishes selection
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel dishesLabel = new JLabel("Available Dishes:");
        dishesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(dishesLabel, gbc);

        dishesPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        dishesPanel.setBackground(Color.WHITE);
        dishesPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(87, 3, 3), 1),
                        "Available Dishes",
                        0, 0,
                        new Font("Arial", Font.BOLD, 12),
                        new Color(87, 3, 3)
                ),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        String[] dishes = {"Grilled Fish", "Seafood Pasta", "Fried Calamari", "Fisherman's Soup", "Octopus Salad"};
        for (String dish : dishes) {
            JCheckBox checkBox = new JCheckBox(dish);
            checkBox.setFont(new Font("Arial", Font.PLAIN, 12));
            checkBox.setBackground(Color.WHITE);
            dishesPanel.add(checkBox);
        }

        JScrollPane dishesScroll = new JScrollPane(dishesPanel);
        dishesScroll.setPreferredSize(new Dimension(400, 180));
        dishesScroll.setBackground(Color.WHITE);
        dishesScroll.setBorder(BorderFactory.createEmptyBorder());

        gbc.gridy = 1;
        formPanel.add(dishesScroll, gbc);

        // Quantity
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1;
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 14));
        formPanel.add(quantityLabel, gbc);

        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        quantitySpinner.setPreferredSize(new Dimension(100, 30));
        quantitySpinner.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 1;
        formPanel.add(quantitySpinner, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton saveButton = createStyledButton("Save to Chart", true);
        saveButton.addActionListener(e -> saveToChart());

        JButton seeChartButton = createStyledButton("See Chart", false);
        seeChartButton.addActionListener(e -> showChartPanel());

        JButton cancelButton = createStyledButton("Cancel Order", false);
        cancelButton.addActionListener(e -> cancelOrder());


        buttonPanel.add(saveButton);
        buttonPanel.add(seeChartButton);
        buttonPanel.add(cancelButton);

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

    private void saveToChart() {
        selectedDishes.clear();
        int quantity = (Integer) quantitySpinner.getValue();
        for (Component comp : dishesPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                JCheckBox checkBox = (JCheckBox) comp;
                if (checkBox.isSelected()) {
                    selectedDishes.put(checkBox.getText(), quantity);
                }
            }
        }

        if (selectedDishes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please select at least one dish before saving.",
                    "No Dish Selected",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(CHART_FILE_PATH, true))) {
            for (Map.Entry<String, Integer> entry : selectedDishes.entrySet()) {
                writer.println(entry.getKey() + " : " + entry.getValue());
            }
            writer.println("---------------------------");
            JOptionPane.showMessageDialog(this, "Dishes saved to chart!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving to chart: " + e.getMessage(),
                    "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createChartPanel() {
        chartPanel = new JPanel(new BorderLayout());
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel chartTitle = new JLabel("Chart", SwingConstants.CENTER);
        chartTitle.setFont(new Font("Arial", Font.BOLD, 24));
        chartTitle.setForeground(new Color(87, 3, 3));
        chartPanel.add(chartTitle, BorderLayout.NORTH);

        JTextArea chartArea = new JTextArea();
        chartArea.setEditable(false);
        chartArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(chartArea);
        chartPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 20));
        buttonPanel.setBackground(Color.WHITE);

        JButton confirmOrderButton = createStyledButton("Confirm Order", true);
        confirmOrderButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Order confirmed! (Simulated)");
            clearChartFile();
            cardLayout.show(contentPanel, "MainAppPanel");
        });

        JButton returnButton = createStyledButton("Return to Selection", false);
        returnButton.addActionListener(e -> cardLayout.show(contentPanel, "orderFoodPanel"));

        buttonPanel.add(confirmOrderButton);
        buttonPanel.add(returnButton);

        chartPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.add(chartPanel, "chartPanel");
    }

    private void showChartPanel() {
        JTextArea chartArea = findTextArea(chartPanel);
        chartArea.setText("");
        try (BufferedReader reader = new BufferedReader(new FileReader(CHART_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                chartArea.append(line + "\n");
            }
        } catch (IOException e) {
            chartArea.setText("Chart is empty.");
        }
        cardLayout.show(contentPanel, "chartPanel");
    }

    private JTextArea findTextArea(Container container) {
        for (Component comp : container.getComponents()) {
            if (comp instanceof JScrollPane) {
                Component view = ((JScrollPane) comp).getViewport().getView();
                if (view instanceof JTextArea) {
                    return (JTextArea) view;
                }
            } else if (comp instanceof Container) {
                JTextArea result = findTextArea((Container) comp);
                if (result != null) return result;
            }
        }
        return null;
    }

    private void clearChartFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CHART_FILE_PATH))) {
            writer.print("");
        } catch (IOException e) {
            System.err.println("Error clearing chart: " + e.getMessage());
        }
    }

    private void cancelOrder() {
        int option = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel this order?\nAll selections will be lost.",
                "Cancel Order Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == JOptionPane.YES_OPTION) {
            resetForm();
            JOptionPane.showMessageDialog(this,
                    "Order cancelled successfully!",
                    "Order Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            cardLayout.show(contentPanel, "mainAppPanel");

        }
    }

    private void resetForm() {
        quantitySpinner.setValue(1);
        selectedDishes.clear();

        // Uncheck all checkboxes
        for (Component comp : dishesPanel.getComponents()) {
            if (comp instanceof JCheckBox) {
                ((JCheckBox) comp).setSelected(false);
            }
        }
    }
}
