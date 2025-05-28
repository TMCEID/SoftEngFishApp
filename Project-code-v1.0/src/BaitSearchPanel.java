

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.*;
import model.Bait;

public class BaitSearchPanel extends javax.swing.JPanel {

    private CardLayout cardLayout;
    private JPanel contentPanel;
    private final String MAIN_APP_PANEL = "mainAppPanel";

    // Components
    private SearchPage searchPage;
    private SortingPage sortingPage;
    private ResultPage resultPage;
    private javax.swing.JButton backButton;

    public BaitSearchPanel(CardLayout cardLayout, JPanel contentPanel) {
        this.cardLayout = cardLayout;
        this.contentPanel = contentPanel;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Create title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(87, 3, 3));
        titlePanel.add(new javax.swing.JLabel("Bait Search & Filter") {{
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 18));
        }});

        // Create back button
        backButton = new javax.swing.JButton("← Back to Main");
        backButton.setBackground(new Color(87, 3, 3));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(contentPanel, MAIN_APP_PANEL));
        titlePanel.add(backButton);

        add(titlePanel, BorderLayout.NORTH);

        // Create main content panel with components
        JPanel mainContent = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Create components
        searchPage = new SearchPage();
        sortingPage = new SortingPage(searchPage.getBaitResults());
        resultPage = new ResultPage();

        // Connect components
        connectComponents();

        // Layout components
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5; gbc.weighty = 0.3;
        mainContent.add(createBorderedPanel("Search", searchPage), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        mainContent.add(createBorderedPanel("Sort & Filter", sortingPage), gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2; gbc.weighty = 0.7;
        mainContent.add(createBorderedPanel("Results", resultPage), gbc);

        add(mainContent, BorderLayout.CENTER);

        // Show initial results
        resultPage.showResults(searchPage.getBaitResults().getResults());
    }

    private JPanel createBorderedPanel(String title, JPanel content) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder(title));
        panel.add(content, BorderLayout.CENTER);
        return panel;
    }

    private void connectComponents() {
        // Override SearchPage's showResults to display in ResultPage
        SearchPage originalSearchPage = searchPage;
        searchPage = new SearchPage() {
            @Override
            protected void showResults(java.util.List<Bait> results) {
                resultPage.showResults(results);
            }
        };

        // Override SortingPage's showFilteredResults to display in ResultPage
        SortingPage originalSortingPage = sortingPage;
        sortingPage = new SortingPage(searchPage.getBaitResults()) {
            @Override
            protected void showFilteredResults(java.util.List<Bait> results) {
                resultPage.showResults(results);
            }
        };
    }
}