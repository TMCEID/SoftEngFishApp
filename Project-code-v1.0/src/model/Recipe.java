package model;

public class Recipe {
    private final int    recipeId;
    private final int    dishId;
    private final String text;
    private final float  rating;   // rating of its parent dish

    public Recipe(int recipeId, int dishId, String text, float rating) {
        this.recipeId = recipeId;
        this.dishId   = dishId;
        this.text     = text;
        this.rating   = rating;
    }

    //getters
    public int    getRecipeId() { return recipeId; }
    public int    getDishId()   { return dishId; }
    public String getText()     { return text; }
    public float  getRating()   { return rating; }
}
