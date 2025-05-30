import javax.swing.*;
import java.awt.*;

public class PrepareDishPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton menuButton;
    private JPopupMenu popupMenu;
    private JButton backButton;

    public PrepareDishPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel titleLabel = new JLabel("Recipe Checker");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(87, 3, 3));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, gbc);

        // Instruction label
        gbc.gridy = 1;
        JLabel instructionLabel = new JLabel("Choose recipe:");
        instructionLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        instructionLabel.setForeground(new Color(100, 30, 30));
        mainPanel.add(instructionLabel, gbc);

        // Menu button
        gbc.gridy = 2;
        menuButton = new JButton("Choose recipe ▼");
        menuButton.setFocusPainted(false);
        menuButton.setBackground(new Color(100, 30, 30));
        menuButton.setForeground(Color.WHITE);
        menuButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        menuButton.setOpaque(true);
        menuButton.setUI(new RoundedButtonUI());
        mainPanel.add(menuButton, gbc);

        // Create popup menu with dishes
        createPopupMenu();

        // Add action listener to menu button
        menuButton.addActionListener(e ->
                popupMenu.show(menuButton, 0, menuButton.getHeight())
        );

        // Back button
        backButton = new JButton("← Back to Main");
        backButton.setBackground(new Color(87, 3, 3));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        backButton.addActionListener(e ->
                cardLayout.show(contentPanel, Main.MAIN_APP_PANEL)
        );

        // Add back button to top
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(backButton);
        add(topPanel, BorderLayout.NORTH);
    }

    private void createPopupMenu() {
        popupMenu = new JPopupMenu();
        Dish[] dishes = Dish.getDishes();

        for (Dish dish : dishes) {
            JMenuItem item = new JMenuItem(dish.getName());
            item.setFont(new Font("SansSerif", Font.PLAIN, 14));
            item.addActionListener(e -> {
                processDishSelection(dish);
            });
            popupMenu.add(item);
        }
    }

    private void processDishSelection(Dish dish) {
        boolean canMake = dish.makeDish();

        String message;
        String title;
        int messageType;

        if (canMake) {
            message = "✅ You can make " + dish.getName() + "!\n\nStock has been updated.";
            title = "Success!";
            messageType = JOptionPane.INFORMATION_MESSAGE;

            // Save updated stock to file
            Ingredient.saveStockToFile("Ingredient_list.txt");
        } else {
            message = "❌ Not enough ingredients to make " + dish.getName() + "!\n\nPlease check your stock or order more ingredients.";
            title = "Insufficient Ingredients";
            messageType = JOptionPane.WARNING_MESSAGE;
        }

        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    // Rounded Button UI Class
    static class RoundedButtonUI extends javax.swing.plaf.basic.BasicButtonUI {
        @Override
        public void paint(Graphics g, JComponent c) {
            Graphics2D g2 = (Graphics2D) g.create();
            AbstractButton b = (AbstractButton) c;

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = b.getWidth();
            int height = b.getHeight();

            // Draw rounded background
            g2.setColor(b.getBackground());
            g2.fillRoundRect(0, 0, width, height, 30, 30);

            // Draw text
            g2.setColor(b.getForeground());
            FontMetrics fm = g2.getFontMetrics();
            String text = b.getText();
            int textWidth = fm.stringWidth(text);
            int textHeight = fm.getAscent();

            g2.drawString(text, (width - textWidth) / 2, (height + textHeight) / 2 - 2);
            g2.dispose();
        }
    }
}