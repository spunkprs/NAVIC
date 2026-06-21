package microsoft.arrays;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 3159
Link : https://leetcode.com/problems/find-occurrences-of-an-element-in-an-array/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all
You are given an integer array nums, an integer array queries, and an integer x.

For each queries[i], you need to find the index of the queries[i]th occurrence of x in the nums array. If there are fewer
than queries[i] occurrences of x, the answer should be -1 for that query.

Return an integer array answer containing the answers to all queries.


Constraints:-

a.) 1 <= nums.length, queries.length <= 10^5
b.) 1 <= queries[i] <= 10^5
c.) 1 <= nums[i], x <= 10^4

Time Complexity = O(N)
Space Complexity = O(N)
 * */

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
