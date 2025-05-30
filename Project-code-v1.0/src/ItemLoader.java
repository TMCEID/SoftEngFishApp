import java.io.*;

public class ItemLoader {

    public static Ingredient[] loadItemsFromFile(String filename) {
        Ingredient[] items = new Ingredient[25]; // Adjust size as needed
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 2) continue;

                String name = parts[0].replace("_", " "); // Replace underscores with spaces
                int quantity = Integer.parseInt(parts[1]);
                items[index++] = new Ingredient(name, quantity);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return items;
    }
}


