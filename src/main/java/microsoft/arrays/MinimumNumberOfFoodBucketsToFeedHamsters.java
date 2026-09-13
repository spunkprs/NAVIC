package microsoft.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
Problem : 2086
Level : Medium
Link :  https://leetcode.com/problems/minimum-number-of-food-buckets-to-feed-the-hamsters/description/?envType=company&envId=grab&favoriteSlug=grab-all

You are given a 0-indexed string hamsters where hamsters[i] is either:

 a.) 'H' indicating that there is a hamster at index i, or
 b.) '.' indicating that index i is empty.

You will add some number of food buckets at the empty indices in order to feed the hamsters.
A hamster can be fed if there is at least one food bucket to its left or to its right. More formally,
 a hamster at index i can be fed if you place a food bucket at index i - 1 and/or at index i + 1.

Return the minimum number of food buckets you should place at empty indices to feed all the hamsters
or -1 if it is impossible to feed all of them.

Constraints:-

a.) 1 <= hamsters.length <= 10^5
b.) hamsters[i] is either'H' or '.'
 * */

public class MinimumNumberOfFoodBucketsToFeedHamsters {

    public static void main(String ar[]) {
        MinimumNumberOfFoodBucketsToFeedHamsters unit = new MinimumNumberOfFoodBucketsToFeedHamsters();
        String hamsters = ".H.H.";
        System.out.print("Minimum number of food buckets to feed hamsters is " +
                unit.minimumBuckets(hamsters));
    }

    public int minimumBuckets(String hamsters) {
        char arr[] = hamsters.toCharArray();
        List<Integer> hamsterIndexes = new ArrayList<>();
        int result = 0;

        if (arr.length == 1 && arr[0] == 'H') {
            return -1;
        } else if (arr.length == 1 && arr[0] == '.') {
            return 0;
        } else if (arr.length == 2 && arr[0] == 'H' && arr[1] == 'H') {
            return -1;
        }

        if (checkForExistenceOfNeighbouringHamsters(arr)) {
            return -1;
        } else {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 'H') {
                    hamsterIndexes.add(i);
                }
            }

            Set<Integer> set = new HashSet<>();

            if (hamsterIndexes.size() > 1) {
                int pairCount = 0;
                for (int i = 0; i < hamsterIndexes.size() - 1; i++) {
                    if (hamsterIndexes.get(i + 1) - hamsterIndexes.get(i) == 2
                            && !set.contains(hamsterIndexes.get(i + 1))
                            && !set.contains(hamsterIndexes.get(i))) {
                        pairCount++;
                        set.add(hamsterIndexes.get(i + 1));
                        set.add(hamsterIndexes.get(i));
                    }
                }

                if (pairCount > 0) {
                    result = hamsterIndexes.size() - (pairCount * 2) + pairCount;
                } else {
                    result = hamsterIndexes.size();
                }
            } else if (hamsterIndexes.size() == 1) {
                result = 1;
            }
        }
        return result;
    }

    private boolean checkForExistenceOfNeighbouringHamsters(char[] arr) {
        if (arr[0] == 'H' && arr[1] == 'H' && arr.length > 2) {
            return true;
        } else if (arr[arr.length - 2] == 'H' && arr[arr.length - 1] == 'H' && arr.length > 2) {
            return true;
        } else {
            for (int i = 1; i < arr.length - 1; i++) {
                if (arr[i] == 'H' && arr[i - 1] == 'H' && arr[i + 1] == 'H') {
                    return true;
                }
            }
        }
         return false;
    }
}
