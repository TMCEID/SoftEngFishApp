import javax.swing.*;
import java.awt.*;
import java.util.List;

class SortingPage extends JPanel {
    private String selectedFilter;
    private BaitResults baitResults;
    private JComboBox<String> filterComboBox;
    private JButton applyButton;

    public SortingPage(BaitResults baitResults) {
        this.baitResults = baitResults;
        this.selectedFilter = "Name";
        initComponents();
    }

    private void initComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Sort by:"), gbc);

        String[] filterOptions = {"Name", "Price (Cheapest)", "Effectiveness"};
        filterComboBox = new JComboBox<>(filterOptions);
        filterComboBox.setSelectedItem(selectedFilter);
        gbc.gridx = 1; gbc.gridy = 0;
        add(filterComboBox, gbc);

        applyButton = new JButton("Apply Filter");
        applyButton.addActionListener(e -> applyFilter());
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(applyButton, gbc);
    }

    public void chooseFilter(String filter) {
        this.selectedFilter = filter;
        filterComboBox.setSelectedItem(filter);
    }

    public void applyFilter() {
        selectedFilter = (String) filterComboBox.getSelectedItem();
        List<Bait> filteredResults;

        switch (selectedFilter) {
            case "Price (Cheapest)":
                filteredResults = baitResults.filterByCheapest();
                break;
            case "Effectiveness":
                filteredResults = baitResults.filterByEffectiveness();
                break;
            case "Name":
            default:
                filteredResults = baitResults.filterAlphabetically();
                break;
        }

        // Pass filtered results to ResultPage
        showFilteredResults(filteredResults);
    }

    protected void showFilteredResults(List<Bait> results) {
        StringBuilder sb = new StringBuilder("Filtered Results (" + selectedFilter + "):\n\n");
        for (Bait bait : results) {
            sb.append(bait.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
    }

    public String getSelectedFilter() {
        return selectedFilter;
    }
}
