
import java.io.*;

public class Ingredient {
    private String name;
    private int quantity;
    private static Ingredient[] stock;

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static void loadStockFromFile(String filename) {
        stock = ItemLoader.loadItemsFromFile(filename);
    }

    public static Ingredient[] getStock() {
        return stock;
    }

    public static void saveStockToFile(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Ingredient ing : stock) {
                if (ing != null) {
                    String line = ing.getName().replace(" ", "_") + " " + ing.getQuantity();
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return name + " - " + quantity;
    }
}



