package model;

public class Dish {
    private final int    dishId;
    private final String name;
    private final String category;
    private final float  price;
    private final float  rating;

    public Dish(int dishId, String name, String category,
                float price, float rating) {
        this.dishId   = dishId;
        this.name     = name;
        this.category = category;
        this.price    = price;
        this.rating   = rating;
    }

    // getters
    public int    getDishId()   { return dishId; }
    public String getName()     { return name; }
    public String getCategory() { return category; }
    public float  getPrice()    { return price; }
    public float  getRating()   { return rating; }
}
