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
    ingredients.add(new Ingredient(1, "Anchovy", 50));
    ingredients.add(new Ingredient(2, "Carp", 40));
    ingredients.add(new Ingredient(3, "Bass", 35));
    ingredients.add(new Ingredient(4, "Shrimp", 30));
    ingredients.add(new Ingredient(5, "Octopus", 25));
    ingredients.add(new Ingredient(6, "Mussels", 60));
    ingredients.add(new Ingredient(7, "Squid", 45));
    ingredients.add(new Ingredient(8, "Sea Bass", 30));
    ingredients.add(new Ingredient(9, "Red Mullet", 20));
    return ingredients;
}
}
