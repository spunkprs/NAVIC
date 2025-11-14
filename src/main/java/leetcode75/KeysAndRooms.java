package leetcode75;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
Problem : 841

There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise

Constraints:-

a.) n == rooms.length
b.) 2 <= n <= 1000
c.) 0 <= rooms[i].length <= 1000
d.) 1 <= sum(rooms[i].length) <= 3000
e.) 0 <= rooms[i][j] < n
f.) All the values of rooms[i] are unique.

Time Complexity : O(N + E), where N being number of rooms && E being number of keys present inside each room
Space Complexity : O(N)
 * */

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
