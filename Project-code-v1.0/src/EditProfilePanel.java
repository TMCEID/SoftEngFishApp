import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class EditProfilePanel extends javax.swing.JPanel {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Main parentMain;
    private final String MAIN_APP_PANEL = "mainAppPanel";

    private JTextField usernameField;
    private JTextField phoneField;
    private JComboBox<String> roleComboBox;
    private JLabel profilePicture;
    private Font defaultFont;

    /**
     * Creates new EditProfilePanel
     * @param cardLayout The CardLayout manager
     * @param contentPanel The parent content panel
     * @param parentMain Reference to main class
     */
    public EditProfilePanel(CardLayout cardLayout, JPanel contentPanel, Main parentMain) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.parentMain = parentMain;
        initComponents();
    }

    private void initComponents() {
        setBackground(Color.decode("#F5F5F5"));
        setLayout(new BorderLayout());

        defaultFont = new Font("SansSerif", Font.PLAIN, 16);

        JLabel titleLabel = new JLabel("🐟 ⭐ Edit Your Profile ⭐ 🐟", SwingConstants.CENTER);
        titleLabel.setFont(defaultFont.deriveFont(Font.BOLD, 20f));
        titleLabel.setForeground(Color.decode("#7A1F1F"));
        titleLabel.setBorder(new EmptyBorder(30, 0, 40, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(Color.decode("#F5F5F5"));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(0, 40, 40, 40));

        JPanel avatarPanel = new JPanel();
        avatarPanel.setBackground(Color.decode("#F5F5F5"));
        avatarPanel.setLayout(new BoxLayout(avatarPanel, BoxLayout.Y_AXIS));
        avatarPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePicture = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw circular border
                g2.setColor(Color.decode("#7A1F1F"));
                g2.setStroke(new BasicStroke(3));
                g2.drawOval(2, 2, getWidth() - 4, getHeight() - 4);

                // Clip to circle for image
                g2.setClip(new java.awt.geom.Ellipse2D.Float(8, 8, getWidth() - 16, getHeight() - 16));
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        profilePicture.setPreferredSize(new Dimension(120, 120));
        profilePicture.setMaximumSize(new Dimension(120, 120));
        profilePicture.setOpaque(false);
        profilePicture.setIcon(new ImageIcon(new BufferedImage(120, 120, BufferedImage.TYPE_INT_ARGB)));
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);
        avatarPanel.add(profilePicture);
        formPanel.add(avatarPanel);
        formPanel.add(Box.createVerticalStrut(15));

        RoundedButton avatarButton = new RoundedButton("Select New Avatar", Color.decode("#7A1F1F"));
        avatarButton.setFont(defaultFont.deriveFont(14f));
        avatarButton.addActionListener(e -> selectNewAvatar());
        avatarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(avatarButton);
        formPanel.add(Box.createVerticalStrut(35));

        // Role Selection
        JLabel roleLabel = new JLabel("Change your role:");
        roleLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        roleLabel.setForeground(Color.decode("#4A5568"));
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(roleLabel);
        formPanel.add(Box.createVerticalStrut(10));

        String[] roles = { "Select...", "Chef", "Customer", "Producer" };
        roleComboBox = new StyledComboBox<>(roles);
        roleComboBox.setFont(defaultFont.deriveFont(14f));
        roleComboBox.setMaximumSize(new Dimension(380, 45));
        roleComboBox.setPreferredSize(new Dimension(380, 45));
        roleComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(roleComboBox);
        formPanel.add(Box.createVerticalStrut(25));

        // Username Field
        JLabel usernameLabel = new JLabel("Change your username:");
        usernameLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        usernameLabel.setForeground(Color.decode("#4A5568"));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usernameLabel);

        // Username rules
        JLabel usernameRules = new JLabel(
                "<html>• Must be at least 3 characters long<br>• Cannot start or end with spaces, dots, or commas</html>");
        usernameRules.setFont(defaultFont.deriveFont(Font.PLAIN, 12f));
        usernameRules.setForeground(Color.decode("#718096"));
        usernameRules.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usernameRules);
        formPanel.add(Box.createVerticalStrut(10));

        usernameField = new StyledTextField("New Username...");
        usernameField.setFont(defaultFont.deriveFont(14f));
        usernameField.setMaximumSize(new Dimension(380, 45));
        usernameField.setPreferredSize(new Dimension(380, 45));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(usernameField);
        formPanel.add(Box.createVerticalStrut(25));

        // Phone Field
        JLabel phoneLabel = new JLabel("Change your mobile number:");
        phoneLabel.setFont(defaultFont.deriveFont(Font.PLAIN, 16f));
        phoneLabel.setForeground(Color.decode("#4A5568"));
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(phoneLabel);
        formPanel.add(Box.createVerticalStrut(10));

        phoneField = new StyledTextField("+3069XXXXXXXX");
        phoneField.setFont(defaultFont.deriveFont(14f));
        phoneField.setMaximumSize(new Dimension(380, 45));
        phoneField.setPreferredSize(new Dimension(380, 45));
        phoneField.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(phoneField);
        formPanel.add(Box.createVerticalStrut(50));

        // Action Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.decode("#F5F5F5"));

        RoundedButton saveButton = new RoundedButton("Save Changes", Color.decode("#7A1F1F"));
        saveButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        saveButton.addActionListener(e -> saveChanges());

        RoundedButton homeButton = new RoundedButton("Back to Home", Color.decode("#718096"));
        homeButton.setFont(defaultFont.deriveFont(Font.BOLD, 16f));
        homeButton.addActionListener(e -> goHome());

        buttonPanel.add(saveButton);
        buttonPanel.add(homeButton);
        formPanel.add(buttonPanel);

        add(formPanel, BorderLayout.CENTER);
    }

    private void selectNewAvatar() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose a new avatar");

        // Set file filter for images only
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                String name = f.getName().toLowerCase();
                return name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                        name.endsWith(".png") || name.endsWith(".gif") ||
                        name.endsWith(".bmp");
            }

            @Override
            public String getDescription() {
                return "Image files (*.jpg, *.jpeg, *.png, *.gif, *.bmp)";
            }
        });

        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            String name = file.getName().toLowerCase();

            // Double check if it's an image file
            if (name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                    name.endsWith(".png") || name.endsWith(".gif") ||
                    name.endsWith(".bmp")) {

                try {
                    ImageIcon icon = new ImageIcon(
                            new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(105, 105,
                                    Image.SCALE_SMOOTH));
                    profilePicture.setIcon(icon);
                } catch (Exception e) {
                    showToast("⚠︎ Error loading image file.", Color.decode("#DC2626"));
                }
            } else {
                showToast("⚠︎ Please select a valid image file (JPG, PNG, GIF, BMP).", Color.decode("#DC2626"));
            }
        }
    }

    private void saveChanges() {
        String username = usernameField.getText().trim();
        String phone = phoneField.getText().trim();

        if (username.equals("Username..."))
            username = "";
        if (phone.equals("+3069XXXXXXXX"))
            phone = "";

        if (!username.isEmpty()) {
            // Updated validation: no spaces, dots, or commas anywhere, minimum 3 characters
            if (!username.matches("^[a-zA-Z0-9](?:[a-zA-Z0-9._ ]{1,}[a-zA-Z0-9])?$") ||
                    username.startsWith(" ") || username.endsWith(" ")) {
                showToast(
                        "⚠︎ Username must be at least 3 characters, cannot start/end with spaces, and may include letters, numbers, dots, or underscores.",
                        Color.decode("#DC2626"));
                return;
            }
        }

        if (!phone.isEmpty()) {
            if (!phone.matches("\\+3069\\d{8}")) {
                showToast("⚠︎Invalid number, please try again", Color.decode("#DC2626"));
                return;
            }
        }

        // Here you could update the current user's profile
        if (parentMain.getCurrentUser() != null) {
            // Update user profile logic would go here
            showToast("✅ Profile updated!", Color.decode("#059669"));
        } else {
            showToast("⚠︎ Please login first!", Color.decode("#DC2626"));
        }
    }

    private void goHome() {
        cardLayout.show(contentPanel, MAIN_APP_PANEL);
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

        // Get the main window to position toast correctly
        Window parentWindow = SwingUtilities.getWindowAncestor(this);
        if (parentWindow != null) {
            toast.setLocation(parentWindow.getX() + (parentWindow.getWidth() - toast.getWidth()) / 2,
                    parentWindow.getY() + parentWindow.getHeight() / 2);
        }

        toast.setAlwaysOnTop(true);
        toast.setVisible(true);

        new Timer(50, new AbstractAction() {
            float opacity = 1.0f;

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

    static class StyledComboBox<T> extends JComboBox<T> {
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

    static class StyledTextField extends JTextField {
        private String placeholder;
        private boolean showingPlaceholder;

        public StyledTextField(String placeholder) {
            this.placeholder = placeholder;
            this.showingPlaceholder = true;

            setBackground(Color.decode("#E2E8F0"));
            setForeground(Color.decode("#A0AEC0"));
            setText(placeholder);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#7A1F1F"), 2),
                    BorderFactory.createEmptyBorder(8, 12, 8, 12)));

            addFocusListener(new java.awt.event.FocusListener() {
                @Override
                public void focusGained(java.awt.event.FocusEvent e) {
                    if (showingPlaceholder) {
                        setText("");
                        setForeground(Color.decode("#4A5568"));
                        showingPlaceholder = false;
                    }
                }

                @Override
                public void focusLost(java.awt.event.FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(placeholder);
                        setForeground(Color.decode("#A0AEC0"));
                        showingPlaceholder = true;
                    }
                }
            });
        }

        @Override
        public String getText() {
            return showingPlaceholder ? "" : super.getText();
        }
    }

    // Rounded Button class
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
}