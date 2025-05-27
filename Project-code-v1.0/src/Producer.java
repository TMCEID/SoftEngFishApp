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
                allIngredients.get(0), // Tzipoura
                allIngredients.get(1), // Lavraki
                allIngredients.get(2)  // Sargos
            )),
            new Producer(2, "Maria Karpouzi", Arrays.asList(
                allIngredients.get(3), // Fagri
                allIngredients.get(4), // Octopus
                allIngredients.get(5)  // Mussels
            )),
            new Producer(3, "Nikos Psaras", Arrays.asList(
                allIngredients.get(6), // Shrimp
                allIngredients.get(7), // Squid
                allIngredients.get(8)  // Sea Bass
            )),
            new Producer(4, "Fisherman's Choice", Arrays.asList(
                allIngredients.get(9), // Red Mullet
                allIngredients.get(0), // Tzipoura
                allIngredients.get(3)  // Fagri
            ))
        );
    }

}
