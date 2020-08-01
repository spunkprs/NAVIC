package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonGame {

    public int calculateMinimumHP(int[][] dungeon) {
        Map<Pair, Integer> map = new HashMap<Pair, Integer>();
        Pair origin = new Pair(0, 0);
        Pair destination = new Pair(dungeon.length - 1, dungeon[0].length - 1);
        if (dungeon[destination.x][destination.y] >= 0) {
            map.put(destination, 1);
        } else {
            map.put(destination, Math.abs(dungeon[destination.x][destination.y]) + 1);

        }
        computeMinimumHealth(dungeon, map, origin);
        int result = map.get(origin);
        return result;
    }

    private int computeMinimumHealth(int[][] dungeon, Map<Pair, Integer> map, Pair pair) {
        List<Pair> children = fetchChildren(pair, dungeon);
        List<Integer> values = new ArrayList<Integer>();
        for (Pair p : children) {
            if (!map.containsKey(p)) {
                values.add(computeMinimumHealth(dungeon, map, p));
            } else {
                values.add(map.get(p));
            }
        }
        if (values.size() > 0) {
            if (values.size() > 1) {
                int valOne = values.get(0);
                int valTwo = values.get(1);

                int min = Math.min(valOne, valTwo);

                if (dungeon[pair.x][pair.y] < 0) {
                    map.put(pair, Math.abs(dungeon[pair.x][pair.y]) + 1 + min - 1);
                } else {
                    if (dungeon[pair.x][pair.y] >= min) {
                        map.put(pair, 1);
                    } else {
                        int diff = min - dungeon[pair.x][pair.y];
                        map.put(pair, diff);
                    }
                }
            } else {
                if (dungeon[pair.x][pair.y] < 0) {
                    int val = Math.abs(dungeon[pair.x][pair.y]) + 1;
                    map.put(pair, val + values.get(0) - 1);
                } else {
                    if (dungeon[pair.x][pair.y] >= values.get(0)) {
                        map.put(pair, 1);
                    } else {
                        int diff = values.get(0) - dungeon[pair.x][pair.y];
                        map.put(pair, diff);
                    }
                }
            }
        }
        return map.get(pair);
    }

    private List<Pair> fetchChildren(Pair pair, int[][] dungeon) {
        List<Pair> children = new ArrayList<Pair>();

        if (pair.x < dungeon.length - 1) {
            children.add(new Pair(pair.x + 1, pair.y));
        }

        if (pair.y < dungeon[0].length - 1) {
            children.add(new Pair(pair.x, pair.y + 1));
        }
        return children;
    }

    class Pair {
        int x,y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (x != pair.x) return false;
            return y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
