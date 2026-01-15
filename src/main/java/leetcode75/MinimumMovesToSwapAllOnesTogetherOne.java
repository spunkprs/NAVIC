package leetcode75;

/**
Problem : 1151
Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array

Constraints:-

a.) 1 <= data.length <= 105
b.) data[i] is either 0 or 1

Time Complexity : O(N), where N being number of elements in the array
Space Complexity : O(1)
 * */

public class MinimumMovesToSwapAllOnesTogetherOne {

    public static void main(String ar[]) {
        MinimumMovesToSwapAllOnesTogetherOne unit = new MinimumMovesToSwapAllOnesTogetherOne();
        int data[] = {1,0,1,0,1,0,1,1,1,0,1,0,0,1,1,1,0,0,1,1,1,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1,0,
                0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1,0,1,1,0,0,0,1,1,1,1,0,0,1};
        System.out.print("Minimum moves to swap all ones together is " + unit.minSwaps(data));
    }

    public int minSwaps(int[] data) {
        int minSwaps = Integer.MAX_VALUE;
        int count = fetchTotalNumberOfOnes(data);
        if (count == 0 || count == data.length || count == 1) {
            return 0;
        } else {
            int leftPointer = 0;
            int rightPointer = 0;
            int numberOfOnes = 0;
            while (rightPointer < data.length) {
                if (data[rightPointer] == 1) {
                    numberOfOnes++;
                }
                if (rightPointer - leftPointer + 1 == count) {
                    minSwaps = Math.min(minSwaps, count - numberOfOnes);
                    if (data[leftPointer] == 1) {
                        numberOfOnes--;
                    }
                    leftPointer++;
                }
                rightPointer++;
            }
            return minSwaps;
        }
    }

    private int fetchTotalNumberOfOnes(int[] data) {
        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 1) {
                count++;
            }
        }
        return count;
    }

}
