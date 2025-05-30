import model.Dish;
import model.Recipe;
import model.SortStrategy;
import controller.FoodSearchController;
import javax.swing.*;
import java.awt.*;
import java.util.List;

/** Looks & behaves like BaitSearchPanel but talks to FoodSearchController. */
public class RecipeSearchPanel extends JPanel {

    /* widgets */
    private final JTextField txtQuery       = new JTextField(15);
    private final JButton    btnSearch      = new JButton("Search");
    private final JButton    btnFetchAll    = new JButton("Fetch all");
    private final JComboBox<String> cmbSort = new JComboBox<>(new String[]{
            "Name (A-Z)", "Best rating", "Price (low→high)"});

    private final JTextArea  txtResults     = new JTextArea(18, 40);

    /* collaborators */
    private final CardLayout mainCards;
    private final JPanel     mainContent;
    private final FoodSearchController ctrl = new FoodSearchController();

    public RecipeSearchPanel(CardLayout cards, JPanel content, Main shell) {
        this.mainCards   = cards;
        this.mainContent = content;
        buildUi(shell);
    }

    /* ---------- ui ---------- */
    private void buildUi(Main shell) {
        setLayout(new BorderLayout());

                // crimson title-bar + back button (matches Bait panel)
                var titleBar = new JPanel(new FlowLayout(FlowLayout.LEADING,10,4));
                titleBar.setBackground(new Color(87,3,3));

                var lblTitle = new JLabel("Recipe Search & Filter");
                lblTitle.setForeground(Color.WHITE);
                lblTitle.setFont(lblTitle.getFont().deriveFont(Font.BOLD,16f));
                titleBar.add(lblTitle);

                var btnBack = new JButton("← Back to Main");
                btnBack.setBackground(new Color(87,3,3));
                btnBack.setForeground(Color.WHITE);
                btnBack.addActionListener(e ->
                mainCards.show(mainContent, Main.MAIN_APP_PANEL));
                titleBar.add(btnBack);

                add(titleBar, BorderLayout.PAGE_START);

                /* ── search-bar & results area */
                var center = new JPanel(new BorderLayout(8,8));

                var searchBar = new JPanel(new FlowLayout(FlowLayout.LEADING));
                searchBar.add(new JLabel("Dish or keyword:"));
                searchBar.add(txtQuery);
                searchBar.add(btnSearch);
                searchBar.add(btnFetchAll);
                searchBar.add(new JLabel("Sort by:"));
                searchBar.add(cmbSort);

                        btnSearch  .addActionListener(e -> doSearch());
                btnFetchAll.addActionListener(e -> { txtQuery.setText(""); doSearch(); });
                cmbSort.addActionListener(e -> doSearch());          // live re-sort

                        center.add(searchBar, BorderLayout.NORTH);

                        txtResults.setEditable(false);
                txtResults.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
                center.add(new JScrollPane(txtResults), BorderLayout.CENTER);

                        add(center, BorderLayout.CENTER);
    }

    /* ---------- helpers ---------- */
    private void doSearch() {
        String kw = txtQuery.getText().trim();

        SortStrategy<Dish> strategy = switch (cmbSort.getSelectedIndex()) {
            case 1 -> list -> list.stream()
                    .sorted((a,b)->Float.compare(b.getRating(), a.getRating()))
                    .toList();
            case 2 -> list -> list.stream()
                    .sorted((a,b)->Float.compare(a.getPrice(),  b.getPrice()))
                    .toList();
            default -> list -> list.stream()
                    .sorted((a,b)->a.getName().compareToIgnoreCase(b.getName()))
                    .toList();
        };

        List<Recipe> recipes = ctrl.search(kw, strategy);
        showResults(recipes);
    }

    private void showResults(List<Recipe> recipes) {
        if (recipes.isEmpty()) {
            txtResults.setText("No recipes found.");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-25s %-7s %-5s%n",
                "Dish", "Price", "★"));
        sb.append("-".repeat(45)).append('\n');
        for (Recipe r : recipes) {
            Dish d = r.getDish();
            sb.append(String.format("%-25s %7.2f %5.1f%n",
                    d.getName(), d.getPrice(), d.getRating()));
        }
        txtResults.setText(sb.toString());
    }
}
