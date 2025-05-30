public class Dish {
    private String name;
    private Ingredient[] ingredients;

    public Dish(String name, Ingredient[] ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public boolean makeDish() {
        Ingredient[] stock = Ingredient.getStock();

        // First, check availability
        for (Ingredient dishIng : ingredients) {
            boolean found = false;
            for (Ingredient stockIng : stock) {
                if (stockIng != null && dishIng.getName().equalsIgnoreCase(stockIng.getName())) {
                    if (stockIng.getQuantity() < dishIng.getQuantity()) {
                        return false; // Not enough quantity
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false; // Ingredient not found
            }
        }

        // If we reach here, we have enough. Subtract quantities.
        for (Ingredient dishIng : ingredients) {
            for (Ingredient stockIng : stock) {
                if (stockIng != null && dishIng.getName().equalsIgnoreCase(stockIng.getName())) {
                    stockIng.setQuantity(stockIng.getQuantity() - dishIng.getQuantity());
                }
            }
        }

        return true;
    }

    public static Dish[] getDishes() {
        return new Dish[]{
                new Dish("Grilled Fish", new Ingredient[]{new Ingredient("Tzipoura", 2), new Ingredient("tomato", 3)}),
                new Dish("Seafood Pasta", new Ingredient[]{new Ingredient("Shrimp", 10), new Ingredient("Squid", 5)}),
                new Dish("Fried Calamari", new Ingredient[]{new Ingredient("Squid", 8)}),
                new Dish("Fisherman's Soup", new Ingredient[]{new Ingredient("Red Mullet", 2), new Ingredient("onions", 1), new Ingredient("potato", 2)}),
                new Dish("Octopus Salad", new Ingredient[]{new Ingredient("Octopus", 1), new Ingredient("onions", 1), new Ingredient("tomato", 2)})
        };
    }

    @Override
    public String toString() {
        return name;
    }
}

