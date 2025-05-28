// src/controller/FoodSearchController.java

package controller;

import dao.DishDao;
import dao.RecipeDao;
import model.*;

import java.util.List;

public class FoodSearchController {

    private final DishDao   dishDao   = new DishDao();
    private final RecipeDao recipeDao = new RecipeDao();

    // Search recipes whose parent dishes match the keyword.
    public List<Recipe> search(String keyword, SortStrategy<Dish> sortOpt) {
        List<Dish> dishes = dishDao.findByKeyword(keyword);
        dishes = sortOpt.sort(dishes);

        return dishes.stream()
                .flatMap(d -> recipeDao.findByDishId(d.getDishId()).stream())
                .toList();
    }
}
