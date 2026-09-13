package microsoft.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
