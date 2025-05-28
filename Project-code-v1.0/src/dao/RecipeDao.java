package dao;

import model.Dish;
import model.Recipe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Data-access object for the recipe table. */
public class RecipeDao {

    /* ----------  INSERT ---------- */

    public void save(Recipe r) {
        String sql = "INSERT INTO recipe(dish_id, text) VALUES (?, ?)";
        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt   (1, r.getDishId());
            ps.setString(2, r.getText());
            ps.executeUpdate();

        } catch (SQLException e) { e.printStackTrace(); }
    }

    // QUERIES

    //Find recipes whose text contains the given keyword
    public List<Recipe> findByKeyword(String kw) {
        String sql = """
                     SELECT r.recipe_id, r.dish_id, r.text, d.rating
                     FROM   recipe r
                     JOIN   dish   d USING (dish_id)
                     WHERE  r.text LIKE ?""";

        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + kw + "%");
            return mapMany(ps.executeQuery());

        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // All recipes that belong to one dish
    public List<Recipe> findByDishId(int dishId) {
        String sql = """
                      SELECT r.recipe_id, r.dish_id, r.text,
                      d.name, d.category, d.price, d.rating
                      FROM   recipe r
                      JOIN   dish   d USING (dish_id)
                      WHERE  r.dish_id = ?""";

        try (Connection conn = DB.get();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, dishId);
            return mapMany(ps.executeQuery());

        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    // helper

    private List<Recipe> mapMany(ResultSet rs) throws SQLException {
        List<Recipe> list = new ArrayList<>();
        while (rs.next()) {
            Dish dish = new Dish(
                                        rs.getInt   ("dish_id"),
                                        rs.getString("name"),
                                        rs.getString("category"),
                                        (float) rs.getDouble("price"),
                                        rs.getFloat ("rating"));

                                list.add(new Recipe(
                                                rs.getInt   ("recipe_id"),
                                                dish.getDishId(),
                                                rs.getString("text"),
                                                dish));
        }
        return list;
    }
}
