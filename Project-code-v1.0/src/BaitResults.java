
import java.util.*;
import java.util.stream.Collectors;
import model.Bait;

class BaitResults {
    private final dao.BaitDao dao = new dao.BaitDao();
    private List<Bait> baitList;

    public BaitResults() {
        reload();
    }

    /** pull everything from DB (default sort = name ASC) */
    public void reload() {
                baitList = dao.findAll("name");
            }

    public List<Bait> getResults() {
        return List.copyOf(baitList);   // immutable copy
    }

    public List<Bait> filterByCheapest() {
        return dao.findAll("price ASC");
    }

    public List<Bait> filterByEffectiveness() {
        return dao.findAll("effectiveness DESC");
    }

    public List<Bait> filterAlphabetically() {
        return dao.findAll("name");
    }

    // Add new bait to the list
    public void addBait(Bait bait) {
        dao.insert(bait);
        reload();
    }

    // Search baits by name
    public List<Bait> searchByName(String query) {
        return dao.search(query, "name");
    }
}
