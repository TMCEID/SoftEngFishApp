import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

class ResultPage extends JPanel {
    private List<Bait> displayedResults;
    private JTextArea resultsArea;
    private JScrollPane scrollPane;

    public ResultPage() {
        this.displayedResults = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        add(new JLabel("Bait Results", JLabel.CENTER), BorderLayout.NORTH);

        resultsArea = new JTextArea(15, 30);
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        scrollPane = new JScrollPane(resultsArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh Results");
        refreshButton.addActionListener(e -> showResults(displayedResults));
        add(refreshButton, BorderLayout.SOUTH);
    }

    public void showResults(List<Bait> results) {
        this.displayedResults = new ArrayList<>(results);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-15s %-10s %-12s%n", "Name", "Price", "Effectiveness"));
        sb.append("-".repeat(40)).append("\n");

        for (Bait bait : results) {
            sb.append(String.format("%-15s $%-9.2f %-12d%n",
                    bait.getName(), bait.getPrice(), bait.getEffectiveness()));
        }

        if (results.isEmpty()) {
            sb.append("No baits found matching your criteria.");
        }

        resultsArea.setText(sb.toString());
    }

    public List<Bait> getDisplayedResults() {
        return new ArrayList<>(displayedResults);
    }
}