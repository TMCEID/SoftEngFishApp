// src/controller/FriendsController.java

import dao.UserDao;
import model.User;
import java.util.List;

public class FriendsController {

    private final UserDao dao = new UserDao();

    public List<User> getAll(int ownerId)   { return dao.findFriendsOf(ownerId); }

    // Attempt to add friend and return detailed result
    public AddFriendResult add(String token, int ownerId) {
                var userOpt = dao.findByPhoneOrUsername(token);
                if (userOpt.isEmpty()) return AddFriendResult.NOT_FOUND;

                        int friendId = userOpt.get().getUserId();
                if (dao.alreadyFriends(ownerId, friendId)) return AddFriendResult.ALREADY;

                        dao.addFriend(ownerId, friendId);
                return AddFriendResult.ADDED;
            }

                //autocomplete
                // Up to 10  users for the popup. */
                public List<User> suggest(String prefix) {
                if (prefix == null || prefix.isBlank()) return List.of();
                return dao.searchPrefix(prefix, 10);
            }
}
