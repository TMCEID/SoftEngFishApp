package dao;

import model.Bait;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaitDao {

    /*  basic queries  */

    public List<Bait> findAll(String orderBySql) {
        String sql = "SELECT * FROM bait" +
                     (orderBySql == null ? "" : " ORDER BY " + orderBySql);
        return query(sql, List.of());
    }

    public List<Bait> search(String kw, String orderBySql) {
        String sql = """
                SELECT * FROM bait
                 WHERE name LIKE ?
            """ + (orderBySql == null ? "" : " ORDER BY " + orderBySql);
        return query(sql, List.of('%' + kw + '%'));
    }

    public void insert(Bait b) {
        String sql = "INSERT INTO bait(name,price,effectiveness) VALUES(?,?,?)";
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, b.getName());
            ps.setFloat (2, b.getPrice());
            ps.setInt   (3, b.getEffectiveness());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    /*  helpers */
    private List<Bait> query(String sql, List<Object> args) {
        try (Connection c = DB.get(); PreparedStatement ps = c.prepareStatement(sql)) {
            for (int i=0;i<args.size();i++) ps.setObject(i+1, args.get(i));
            ResultSet rs = ps.executeQuery();
            List<Bait> list = new ArrayList<>();
            while (rs.next())
                list.add(new Bait(
                        rs.getInt("bait_id"),
                        rs.getString("name"),
                        rs.getFloat("price"),
                        rs.getInt("effectiveness")));
            return list;
        } catch (SQLException e) { e.printStackTrace(); }
        return List.of();
    }
}
