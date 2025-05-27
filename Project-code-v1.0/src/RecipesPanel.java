// src/ui/RecipesPanel.java

import model.Dish;
import model.Recipe;
import model.SortStrategy;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecipesPanel extends JPanel {

    private final FoodSearchController ctrl = new FoodSearchController();

    private final JTextField   txtSearch = new JTextField(12);
    private final JComboBox<String> cboSort =
            new JComboBox<>(new String[]{"best rating", "alphabetical", "low price"});
    private final DefaultListModel<String> model = new DefaultListModel<>();
    private final JList<String>            list  = new JList<>(model);

    public RecipesPanel() {
        setLayout(new BorderLayout(10,10));

        /* top bar */
        var top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("Dish:"));
        top.add(txtSearch);
        top.add(cboSort);
        top.add(new JButton("search"){{
            addActionListener(e -> search());
        }});
        add(top, BorderLayout.NORTH);

        /* results */
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    private void search() {
        String kw = txtSearch.getText().trim();
        if (kw.isBlank()) return;

        SortStrategy<Dish> strat = switch (cboSort.getSelectedIndex()) {
            case 1  -> list -> list.stream().sorted(
                            (a,b)->a.getName().compareToIgnoreCase(b.getName())).toList();
            case 2  -> list -> list.stream().sorted(
                            (a,b)->Float.compare(a.getPrice(), b.getPrice())).toList();
            default -> list -> list.stream().sorted(
                            (a,b)->Float.compare(b.getRating(), a.getRating())).toList();
        };

        List<Recipe> out = ctrl.search(kw, strat);
        model.clear();
        out.forEach(r -> model.addElement(r.getText()));
    }
}
