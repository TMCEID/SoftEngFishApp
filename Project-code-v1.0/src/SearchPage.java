

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.List;
import model.Bait;

class SearchPage extends JPanel {
    private String searchQuery;
    private BaitResults baitResults;
    private JTextField searchField;
    private JButton searchButton;

    public SearchPage() {
        this.baitResults = new BaitResults();
        this.searchQuery = "";
        initComponents();
    }

    private void initComponents() {
        setLayout(new java.awt.FlowLayout());

        add(new JLabel("Search Bait:"));

        searchField = new JTextField(20);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> submitSearch());
        add(searchButton);

        JButton detailsButton = new JButton("Fetch Details");
        detailsButton.addActionListener(e -> fetchBaitDetails());
        add(detailsButton);
    }

    public void enterSearchQuery(String query) {
        this.searchQuery = query;
        searchField.setText(query);
    }

    public void submitSearch() {
        String query = searchField.getText().trim();
        if (!query.isEmpty()) {
            this.searchQuery = query;
            List<Bait> results = baitResults.searchByName(query);
            // Pass results to ResultPage or SortingPage
            showResults(results);
        }
    }

    public void fetchBaitDetails() {
        List<Bait> allBaits = baitResults.getResults();
        showResults(allBaits);
    }

    protected void showResults(List<Bait> results) {
        //  navigate to ResultPage or show results
        StringBuilder sb = new StringBuilder("Search Results:\n");
        for (Bait bait : results) {
            sb.append(bait.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    public BaitResults getBaitResults() {
        return baitResults;
    }

    public String getSearchQuery() {
        return searchQuery;
    }
}