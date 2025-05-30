import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {
    //private int chefId;
    private int orderId;
    private Producer producer;
    private Map<Ingredient, Integer> ingredients = new HashMap<>();
    private Date orderDate;
    private String status;

    public Order(int chefId, int orderId, Producer producer) {
       // this.chefId = chefId;
        this.orderId = orderId;
        this.producer = producer;
        this.orderDate = new Date();
        this.status = "Pending";
    }

    public void addIngredient(Ingredient ingredient, int quantity) {
        ingredients.put(ingredient, quantity);
    }

    public Map<Ingredient, Integer> getIngredients() {
        return ingredients;
    }

    public String getStatus() {
        return status;
    }

    public void confirm() {
        status = "Confirmed";
    }

    public void cancel() {
        status = "Canceled";
    }

    public Producer getProducer() {
        return producer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getOrderId() {
        return orderId;
    }
}
