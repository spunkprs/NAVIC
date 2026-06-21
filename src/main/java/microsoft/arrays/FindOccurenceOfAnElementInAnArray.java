package microsoft.arrays;

import java.util.HashMap;
import java.util.Map;

public class FindOccurenceOfAnElementInAnArray {

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int result[] = new int[queries.length];
        int minOccurence = -1;
        int maxOccurence = -1;
        Map<Integer, Integer> map = new HashMap();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                if (minOccurence == -1) {
                    minOccurence = 1;
                    maxOccurence = 1;
                    map.put(minOccurence, i);
                } else {
                    maxOccurence++;
                    map.put(maxOccurence, i);
                }
            }
        }

        for (int i = 0; i < queries.length; i++) {
            result[i] = queries[i] > maxOccurence ? -1 : map.get(queries[i]);
        }

        return result;
    }
}
