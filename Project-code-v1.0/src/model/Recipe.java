package model;

import model.Dish;

        /**
  * A recipe **knows its parent {@link Dish}** so the UI can show name/price/rating
  * without a second DB round-trip.
  */
        public class Recipe {
     private final int  recipeId;
    private final int  dishId;
    private final String text;

            /* NEW — full Dish record (name, price, rating…) */
            private final Dish dish;

            public Recipe(int recipeId, int dishId, String text, Dish dish) {
                this.recipeId = recipeId;
                this.dishId   = dishId;
                this.text     = text;
                this.dish     = dish;
            }

    //getters
    public int    getRecipeId() { return recipeId; }
    public int    getDishId()   { return dishId; }
    public String getText()     { return text; }
    /* Convenience delegates */
    public Dish   getDish()     { return dish; }
    public float  getRating()   { return dish.getRating(); }
}
