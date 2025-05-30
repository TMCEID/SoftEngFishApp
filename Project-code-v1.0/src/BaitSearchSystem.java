

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.Bait;


class BaitSearchSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bait Search System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(2, 2));

            // Create components
            SearchPage searchPage = new SearchPage();
            SortingPage sortingPage = new SortingPage(searchPage.getBaitResults());
            ResultPage resultPage = new ResultPage();

            // Add to frame
            frame.add(new JScrollPane(searchPage));
            frame.add(new JScrollPane(sortingPage));
            frame.add(new JScrollPane(resultPage));

            // Show some initial results
            resultPage.showResults(searchPage.getBaitResults().getResults());

            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}