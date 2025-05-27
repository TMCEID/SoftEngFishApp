package dao;
import model.*;
import java.sql.*;
import java.util.*;

public class DishDao {

    public List<Dish> findByKeyword(String keyword){
        String sql = "SELECT * FROM dish WHERE name LIKE ?";
        try(Connection c = DB.get();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            List<Dish> list = new ArrayList<>();
            while(rs.next()) list.add(map(rs));
            return list;
        }catch(SQLException e){ e.printStackTrace(); }
        return List.of();
    }

    private Dish map(ResultSet rs) throws SQLException{
        return new Dish(
            rs.getInt("dish_id"),
            rs.getString("name"),
            rs.getString("category"),
                (float) rs.getDouble("price"),
            rs.getFloat("rating")
        );
    }
}
