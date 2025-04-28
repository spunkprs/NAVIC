package arrays.slidingwindow;

/*
*
You are given a 0-indexed integer array candies, where candies[i] represents the flavor of the ith candy. Your mom wants you to share these candies with your little sister by giving her k consecutive candies, but you want to keep as many flavors of candies as possible.

Return the maximum number of unique flavors of candy you can keep after sharing with your sister.

Example 1:

Input: candies = [1,2,2,3,4,3], k = 3
Output: 3
Explanation:
Give the candies in the range [1, 3] (inclusive) with flavors [2,2,3].
You can eat candies with flavors [1,4,3].
There are 3 unique flavors, so return 3.
Example 2:

Input: candies = [2,2,2,2,3,3], k = 2
Output: 2
Explanation:
Give the candies in the range [3, 4] (inclusive) with flavors [2,3].
You can eat candies with flavors [2,2,2,3].
There are 2 unique flavors, so return 2.
Note that you can also share the candies with flavors [2,2] and eat the candies with flavors [2,2,3,3].
Example 3:

Input: candies = [2,4,5], k = 0
Output: 3
Explanation:
You do not have to give any candies.
You can eat the candies with flavors [2,4,5].
There are 3 unique flavors, so return 3.
*
*
* Constraints:

0 <= candies.length <= pow(10,5)
1 <= candies[i] <= pow(10,5)
0 <= k <= candies.length
*
*
* Time Complexity = O(n)
* Space Complexity = O(n)
*
* */


import java.util.HashMap;
import java.util.Map;

public class NumberOfUniqueFlavoursAfterSharingKCandies {

    public static void main(String ar[]) {
        NumberOfUniqueFlavoursAfterSharingKCandies unit = new NumberOfUniqueFlavoursAfterSharingKCandies();
        //int candyAarr[] = {1,2,2,3,4,3};
        int candyAarr[] = {2,2,2,2,3,3};
        //int k = 3;
        int k = 2;
        System.out.print("Maximum number of unique candy flavous that can be enjoyed by me is :: " + unit.shareCandies(candyAarr, k));
    }

    /*
    *
    * Following use cases needs to be handled :-
    * a.) When k == candies.length
    * b.) When k < candies.length
    * c.) When k == 0
    * d.) candies.length == 0
    * */
    public int shareCandies(int[] candies, int k) {
        Map<Integer, Integer> mapOne = new HashMap<>();
        Map<Integer, Integer> mapTwo = new HashMap<>();

        if (candies.length == 0) {
            return 0;
        } else {
            if (k == candies.length) {
                return 0;
            } else if (k == 0 && candies.length != 0) {
                updateMaps(mapOne, candies);
                return mapOne.keySet().size();
            } else {
                return processToComeUpWithNumberOfUniqueCandies(mapOne, mapTwo, candies, k);
            }
        }
    }

    private int processToComeUpWithNumberOfUniqueCandies(Map<Integer, Integer> mapOne, Map<Integer, Integer> mapTwo, int[] candies, int k) {
        int uniqueCandies = 0;
        updateMaps(mapOne, candies);
        int leftIndex = 0;
        int rightIndex = 0;

        while(rightIndex < candies.length) {
            updateMaps(mapOne, mapTwo, candies, rightIndex, true);
            if (rightIndex - leftIndex + 1 == k) {
                uniqueCandies = Math.max(uniqueCandies, mapOne.size());
            } else if (rightIndex - leftIndex + 1 > k) {
                updateMaps(mapOne, mapTwo, candies, leftIndex, false);
                uniqueCandies = Math.max(uniqueCandies, mapOne.size());
                leftIndex++;
            }
            rightIndex++;
        }
        return uniqueCandies;
    }

    private void updateMaps(Map<Integer, Integer> mapOne, Map<Integer, Integer> mapTwo, int[] candies, int index, boolean flag) {
        if (flag) {
            if (!mapTwo.containsKey(candies[index])) {
                mapTwo.put(candies[index], 1);
            } else {
                mapTwo.put(candies[index], mapTwo.get(candies[index]) + 1);
            }

            if (mapOne.containsKey(candies[index])) {
                if (mapOne.get(candies[index]) > 1) {
                    mapOne.put(candies[index], mapOne.get(candies[index]) - 1);
                } else if (mapOne.get(candies[index]) == 1) {
                    mapOne.remove(candies[index]);
                }
            }
        } else {
            if (!mapOne.containsKey(candies[index])) {
                mapOne.put(candies[index], 1);
            } else {
                mapOne.put(candies[index], mapOne.get(candies[index]) + 1);
            }

            if (mapTwo.containsKey(candies[index])) {
                if (mapTwo.get(candies[index]) > 1) {
                    mapTwo.put(candies[index], mapTwo.get(candies[index]) - 1);
                } else if (mapTwo.get(candies[index]) == 1) {
                    mapTwo.remove(candies[index]);
                }
            }
        }

    }

    private void updateMaps(Map<Integer, Integer> map, int[] candies) {
        for (Integer candy : candies) {
            if (!map.containsKey(candy)) {
                map.put(candy, 1);
            } else {
                map.put(candy, map.get(candy) + 1);
            }
        }
    }
}
