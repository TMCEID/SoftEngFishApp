import java.awt.CardLayout;
import javax.swing.JPanel;

public class IntroPanel extends javax.swing.JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Main parentMain;  // Reference to main for user management
    private final String MAIN_APP_PANEL = "mainAppPanel";

    // Components
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;

    /**
     * Creates new IntroPanel
     * @param cardLayout The CardLayout manager
     * @param contentPanel The parent content panel
     * @param parentMain Reference to main class for user management
     */
    public IntroPanel(CardLayout cardLayout, JPanel contentPanel, Main parentMain) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        this.parentMain = parentMain;
        initComponents();
    }

    /**
     * Initialize the components
     */
    private void initComponents() {
        // Initialize components
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        // Configure button
        jButton1.setBackground(new java.awt.Color(87, 3, 3));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Continue to app");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Configure labels
        jLabel1.setFont(new java.awt.Font("Arial", 1, 18));
        jLabel1.setForeground(new java.awt.Color(87, 3, 3));
        jLabel1.setText("<html> Welcome to GReel ,<br> a fisherman's handyman.<br> Are you in for a reel ? </html> ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("GReel_final_png.png")));

        // Layout setup
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(74, 74, 74)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(132, 132, 132)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(97, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(157, 157, 157)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72))
        );
    }

    /**
     * Handle continue button click - now includes login functionality
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Show login dialog and get user
        var dlg = new LoginDialog(null);
        var currentUser = dlg.showLogin();        // null -> cancel

        if (currentUser != null) {               // success
            parentMain.setCurrentUser(currentUser);  // Set the logged-in user
            cardLayout.show(contentPanel, MAIN_APP_PANEL);
        }
        // If currentUser is null (login cancelled), stay on intro panel
    }
}