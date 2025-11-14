package leetcode75;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeysAndRooms {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> set = new HashSet();
        set.add(0);
        processToCheckCanVisitAllRooms(rooms, set, 0);
        return set.size() == rooms.size() ? true : false;
    }

    private void processToCheckCanVisitAllRooms(List<List<Integer>> rooms, Set<Integer> set, int parentIndex) {

        if (set.size() < rooms.size()) {
            for (Integer childIndex : fetchChildIndexes(set, rooms, parentIndex)) {
                set.add(childIndex);
                processToCheckCanVisitAllRooms(rooms, set, childIndex);
            }
        }
    }

    private List<Integer> fetchChildIndexes(Set<Integer> set, List<List<Integer>> rooms, int parentIndex) {
        List<Integer> roomKeys = rooms.get(parentIndex);
        List<Integer> childIndexes = new ArrayList();

        for (Integer roomKey : roomKeys) {
            if (!set.contains(roomKey)) {
                childIndexes.add(roomKey);
            }
        }
        return childIndexes;
    }
}
