import java.util.*;

public class PairCount {

    public static void main(String ar[]) {
        List<Integer> list = Arrays.asList(2, 4, 9, 1, 9, 7, 6, 4, 9);
        //System.out.println(solve(list, 8));
        System.out.println(1%20);
    }

    public static int solve(List<Integer> A, int B) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();

        for (int i = 0; i < A.size(); i++) {
            if (!map.containsKey(A.get(i))) {
                Set<Integer> indexSet = new HashSet<Integer>();
                indexSet.add(i);
                map.put(A.get(i), indexSet);
            } else {
                Set<Integer> indexSet = map.get(A.get(i));
                indexSet.add(i);
            }
        }

        int numOfPairs = 0;

        for (int i = 0; i < A.size(); i++) {
            int numToBeFound = Math.abs(A.get(i) - B);
            if (map.containsKey(A.get(i)) && map.containsKey(numToBeFound)) {
                if (A.get(i) == numToBeFound) {
                    numOfPairs += map.get(numToBeFound).size() - 1;
                    map.remove(numToBeFound);
                } else {
                    numOfPairs += map.get(A.get(i)).size() * map.get(numToBeFound).size();
                    map.remove(A.get(i));
                    map.remove(numToBeFound);
                }
            }
        }
        return numOfPairs;
    }
}
