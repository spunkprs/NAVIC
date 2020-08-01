package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        Map<Pair, Integer> map = new HashMap<Pair, Integer>();
        Pair destination = new Pair(n - 1, m - 1);
        Pair source = new Pair(0, 0);

        if (source.equals(destination)) {
            return 1;
        } else {
            map.put(destination, 1);
            return processToFindUniquePaths(source, map, destination);
        }
    }

    private int processToFindUniquePaths(Pair pair, Map<Pair, Integer> map, Pair destination) {
        int result = 0;
        for (Pair child : getChildren(pair, destination)) {
            if (map.containsKey(child)) {
                result+= map.get(child);
            } else {
                result += processToFindUniquePaths(child, map, destination);
            }
        }
        map.put(pair, result);
        return result;
    }

    private List<Pair> getChildren(Pair pair, Pair destination) {
        List<Pair> children = new ArrayList<Pair>();
        int childX = pair.x + 1;
        int childY = pair.y;

        if (childX <= destination.x) {
            children.add(new Pair(childX, childY));
        }

        childX = pair.x;
        childY = pair.y + 1;

        if (childY <= destination.y) {
            children.add(new Pair(childX, childY));
        }

        return children;
    }

    class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
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
