import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private int ingredientId;
    private String name;
    private int quantity;

    public Ingredient(int ingredientId, String name, int quantity) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.quantity = quantity;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    //Added Harcoded ingredients//
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static List<Ingredient> getSampleIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(1, "Tzipoura", 50));
        ingredients.add(new Ingredient(2, "Lavraki", 40));
        ingredients.add(new Ingredient(3, "Sargos", 35));
        ingredients.add(new Ingredient(4, "Fagri", 30));
        ingredients.add(new Ingredient(5, "Octopus", 25));
        ingredients.add(new Ingredient(6, "Mussels", 60));
        ingredients.add(new Ingredient(7, "Shrimp", 45));
        ingredients.add(new Ingredient(8, "Squid", 30));
        ingredients.add(new Ingredient(9, "Sea Bass", 20));
        ingredients.add(new Ingredient(10, "Red Mullet", 25));
        return ingredients;
    }
}
