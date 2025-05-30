package dao;

import model.User;
import model.Friend;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

                                // AUTH / REGISTER

                /** return the user if phone+username match, else empty */
                public Optional<User> login(String username,String pwd){
                    String sql = "SELECT * FROM user WHERE username=? AND password=?";
                    try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
                        ps.setString(1, username);
                        ps.setString(2, pwd);
                        ResultSet rs = ps.executeQuery();
                        return rs.next() ? Optional.of(map(rs))
                                                 : Optional.empty();
                    } catch (SQLException e) { e.printStackTrace(); }
                return Optional.empty();
            }

            /** insert into user, returns new PK or -1 on fail/duplicate */
            public int register(String phone,String username,char gender,String pwd){
                String sql="INSERT INTO user(phone,username,gender,password) VALUES(?,?,?,?)";
                try (Connection c = DB.get();
                     PreparedStatement ps = c.prepareStatement(sql,
                             Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, phone);
                    ps.setString(2, username);
                    ps.setString(3, String.valueOf(gender));
                    ps.setString(4, pwd);
                    if (ps.executeUpdate() != 1) return -1;
                    ResultSet rs = ps.getGeneratedKeys();
                    return rs.next() ? rs.getInt(1) : -1;
                } catch (SQLException e) {            // duplicates end up here
                    return -1;
                }
            }


    public Optional<User> findByPhoneOrUsername(String token) {
        String sql = "SELECT * FROM user WHERE phone=? OR username=?";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, token);
            ps.setString(2, token);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return Optional.empty();
                return Optional.of(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    //autocomplete support
    /** First (int limit) users whose phone or username starts with (String prefix). */
            public List<User> searchPrefix(String prefix, int limit) {
                String sql = """
                SELECT *
                  FROM user
                 WHERE phone    LIKE ? OR username LIKE ?
                 ORDER BY username
                 LIMIT ?""";
                try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
                        String pat = prefix + "%";
                        ps.setString(1, pat);
                        ps.setString(2, pat);
                        ps.setInt(3, limit);

                                ResultSet rs = ps.executeQuery();
                        List<User> list = new ArrayList<>();
                        while (rs.next()) list.add(map(rs));
                        return list;
                    } catch (SQLException e) { e.printStackTrace(); }
                return List.of();
            }

    public List<Friend> getFriends(int userId) {
        String sql = "SELECT friend_id FROM friend WHERE owner_id=?";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            List<Friend> list = new ArrayList<>();
            while (rs.next()) list.add(new Friend(rs.getInt(1)));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public boolean addFriend(int ownerId, int friendId) {
        String sql = "INSERT OR IGNORE INTO friend(owner_id,friend_id) VALUES(?,?)";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, ownerId);
            ps.setInt(2, friendId);
            return ps.executeUpdate() == 1;         // true = really inserted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /** true when (ownerId, friendId) is already present in table friend. */
    public boolean alreadyFriends(int ownerId, int friendId) {
        String sql = "SELECT 1 FROM friend WHERE owner_id=? AND friend_id=?";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            ps.setInt(2, friendId);
            return ps.executeQuery().next();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }


    public List<User> findFriendsOf(int ownerId) {
        String sql =
                "SELECT u.* FROM friend f JOIN user u ON u.user_id = f.friend_id " +
                        "WHERE f.owner_id = ?";
        try (Connection c = DB.get();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ownerId);
            ResultSet rs = ps.executeQuery();
            List<User> list = new ArrayList<>();
            while (rs.next())
                list.add(map(rs));
            return list;
        } catch (SQLException e) { e.printStackTrace(); }
        return List.of();
    }


    // Helpers for controller
    public boolean linkFriend(int ownerId, int friendId) {       // <-- boolean
        return addFriend(ownerId, friendId);
    }


    private User map(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("user_id"),
                rs.getString("phone"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("gender").charAt(0));

    }
}
