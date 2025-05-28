import java.util.*;
import java.util.stream.Collectors;

class BaitResults {
    private List<Bait> baitList;

    public BaitResults() {
        this.baitList = new ArrayList<>();
        // Initialize with sample data
        initializeSampleBaits();
    }

    private void initializeSampleBaits() {
        baitList.add(new Bait("Earthworm", 2.50f, 8));
        baitList.add(new Bait("Minnow", 5.00f, 9));
        baitList.add(new Bait("Cricket", 3.00f, 7));
        baitList.add(new Bait("Artificial Lure", 12.99f, 6));
        baitList.add(new Bait("Bloodworm", 4.50f, 9));
        baitList.add(new Bait("Corn", 1.00f, 5));
        baitList.add(new Bait("Shrimp", 8.00f, 8));
    }

    public List<Bait> getResults() {
        return new ArrayList<>(baitList);
    }

    public List<Bait> filterByCheapest() {
        return baitList.stream()
                .sorted(Comparator.comparing(Bait::getPrice))
                .collect(Collectors.toList());
    }

    public List<Bait> filterByEffectiveness() {
        return baitList.stream()
                .sorted(Comparator.comparing(Bait::getEffectiveness, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    public List<Bait> filterAlphabetically() {
        return baitList.stream()
                .sorted(Comparator.comparing(Bait::getName))
                .collect(Collectors.toList());
    }

    // Add new bait to the list
    public void addBait(Bait bait) {
        baitList.add(bait);
    }

    // Search baits by name
    public List<Bait> searchByName(String query) {
        return baitList.stream()
                .filter(bait -> bait.getName().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
