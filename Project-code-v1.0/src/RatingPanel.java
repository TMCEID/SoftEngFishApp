import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RatingPanel extends JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Main parentMain;

    private JComboBox<String> dishComboBox;
    private JComboBox<String> rateComboBox;
    private Font defaultFont;

    public RatingPanel(CardLayout cardLayout, JPanel contentPanel, Main parentMain) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.parentMain = parentMain;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Default font
        defaultFont = new Font("SansSerif", Font.PLAIN, 16);

        // Title
        JLabel titleLabel = new JLabel("🐟⭐️🌀 Fish Dish Rating ✮⋆˙", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 24f));
        titleLabel.setForeground(Color.decode("#570303"));
        titleLabel.setBorder(new EmptyBorder(25, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.WHITE);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(10, 30, 20, 30));

        // Dish selection
        JPanel dishRow = createFormRow("Please select the dish you want to rate:",
                dishComboBox = createStyledComboBox(new String[] {
                        "Select...", "Grilled Mackerel w/ Lemon&Herbs", "Salmon Teriyaki",
                        "Thai Steamed Fish w/ Lime&Chili", "Tilapia Veracruzana", "Kalamaraki w/Lemon",
                        "Pesto Salmon w/ Breadcrumb Crust", "Sheet Pan Shrimp", "Fish Sticks",
                        "Baked Red Snapper w/ Zhoug"
                }));
        formPanel.add(dishRow);
        formPanel.add(Box.createVerticalStrut(12));

        // Rating selection
        JPanel ratingRow = createFormRow("Select your rating:",
                rateComboBox = createStyledComboBox(new String[] {
                        "Select...", "★", "★★", "★★★"
                }));
        formPanel.add(ratingRow);
        formPanel.add(Box.createVerticalStrut(150));

        // Buttons
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonRow.setBackground(Color.WHITE);

        RoundedButton saveButton = new RoundedButton("Save Rating", Color.decode("#570303"));
        saveButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        saveButton.addActionListener(e -> saveAction());

        RoundedButton homeButton = new RoundedButton("Back to Home", Color.decode("#6B7280"));
        homeButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        homeButton.addActionListener(e -> goHome());

        buttonRow.add(saveButton);
        buttonRow.add(homeButton);
        formPanel.add(buttonRow);

        add(formPanel, BorderLayout.CENTER);
    }

    private JPanel createFormRow(String labelText, JComboBox<String> comboBox) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.Y_AXIS));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel(labelText);
        label.setFont(defaultFont.deriveFont(Font.BOLD, 17f));
        label.setForeground(Color.decode("#374151"));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(new EmptyBorder(0, 0, 8, 0));

        row.add(label);
        row.add(comboBox);

        return row;
    }

    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        styleComboBox(comboBox);
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        comboBox.setMaximumSize(new Dimension(350, 40));
        comboBox.setPreferredSize(new Dimension(350, 40));
        return comboBox;
    }

    private void styleComboBox(JComboBox<String> comboBox) {
        comboBox.setFont(defaultFont.deriveFont(14f));
        comboBox.setForeground(Color.WHITE);
        comboBox.setBackground(Color.decode("#570303"));
        comboBox.setOpaque(true);
        comboBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#570303"), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                          boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, false, false);
                label.setFont(defaultFont.deriveFont(14f));
                label.setOpaque(true);
                label.setBorder(new EmptyBorder(8, 12, 8, 12));

                if (isSelected) {
                    label.setBackground(Color.decode("#7C2D12"));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(Color.decode("#374151"));
                }
                return label;
            }
        });
    }

    private void saveAction() {
        String dish = dishComboBox.getSelectedItem().toString();
        String rating = rateComboBox.getSelectedItem().toString();

        if ("Select...".equals(dish) || "Select...".equals(rating)) {
            showToast("⚠︎ Please select what's missing", Color.decode("#DC2626"));
        } else {
            showToast("✅You rated " + dish + " with " + rating, Color.decode("#059669"));
            // TODO: Save rating to database or user's profile
        }
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
        panel.setBackground(new Color(0, 0, 0, 0));
        panel.setBorder(new EmptyBorder(12, 25, 12, 25));

        JLabel label = new JLabel(message);
        label.setForeground(Color.WHITE);
        label.setFont(defaultFont.deriveFont(Font.BOLD, 15f));
        panel.add(label);

        toast.add(panel);
        toast.pack();

        // Position relative to parent panel
        Container parent = SwingUtilities.getWindowAncestor(this);
        if (parent != null) {
            int x = parent.getX() + (parent.getWidth() - toast.getWidth()) / 2;
            int y = parent.getY() + (parent.getHeight() - toast.getHeight()) / 2;
            toast.setLocation(x, y);
        }

        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        new Timer(50, new AbstractAction() {
            float opacity = 1.0f;

            @Override
            public void actionPerformed(ActionEvent e) {
                opacity -= 0.05f;
                if (opacity <= 0) {
                    toast.dispose();
                    ((Timer) e.getSource()).stop();
                } else {
                    toast.setOpacity(opacity);
                }
            }
        }) {
            {
                setInitialDelay(1000);
                start();
            }
        };
    }

    private void goHome() {
        cardLayout.show(contentPanel, Main.MAIN_APP_PANEL);
    }

    // Inner class for rounded buttons
    static class RoundedButton extends JButton {
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

        @Override
        protected void paintBorder(Graphics g) {
            // No border needed
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
}