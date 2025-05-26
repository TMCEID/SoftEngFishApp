import java.util.Arrays;
import java.util.List;

public class Producer {
    private int producerId;
    private String fullName;
    private List<Ingredient> availableIngredients;

    
    public Producer(int producerId, String fullName, List<Ingredient> ingredients) {
        this.producerId = producerId;
        this.fullName = fullName;
        this.availableIngredients = ingredients;
    }

    public int getProducerId() {
        return producerId;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Ingredient> getAvailableIngredients() {
        return availableIngredients;
    }


        public static List<Producer> getSampleProducers() {
        List<Ingredient> allIngredients = Ingredient.getSampleIngredients();
        
        // Create producers with different subsets of ingredients
        return Arrays.asList(
            new Producer(1, "Alexis Pappas", Arrays.asList(
                allIngredients.get(0), // Anchovy
                allIngredients.get(1), // Carp
                allIngredients.get(2)  // Bass
            )),
            new Producer(2, "Maria Ioannou", Arrays.asList(
                allIngredients.get(3), // Shrimp
                allIngredients.get(4), // Octopus
                allIngredients.get(5)  // Mussels
            )),
            new Producer(3, "Nikos Fournakos", Arrays.asList(
                allIngredients.get(6), // Squid
                allIngredients.get(7), // Sea Bass
                allIngredients.get(8)  // Red Mullet
            )),
            new Producer(4, "Kostas Mpompis", Arrays.asList(
                allIngredients.get(2), // Bass
                allIngredients.get(0), // Anchovy
                allIngredients.get(3)  // Shrimp
            ))
        );
    }

}
