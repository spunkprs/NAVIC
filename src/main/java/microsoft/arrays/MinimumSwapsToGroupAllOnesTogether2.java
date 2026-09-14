package microsoft.arrays;

import java.util.Arrays;

public class MinimumSwapsToGroupAllOnesTogether2 {

    public static void main(String ar[]) {
        MinimumSwapsToGroupAllOnesTogether2 unit = new MinimumSwapsToGroupAllOnesTogether2();
        int nums[] = {1,1,0,0,1};
        System.out.print("Minimum swaps to group all ones together is " + unit.minSwaps(nums));
    }

    public int minSwaps(int[] nums) {
       int onesCount =  (int)fetchNumberOfOnesCount(nums);
       
       if (onesCount == 0 || onesCount == nums.length || onesCount == 1) {
           return 0;
       }
       
       return processToComputeMinSwaps(nums, onesCount);
       //return 0;
    }

    private int processToComputeMinSwaps(int[] nums, int onesCount) {
        int minSwapsNeeded = Integer.MAX_VALUE;
        int leftPointer = 0;
        int rightPointer = leftPointer + onesCount - 1;
        int onesCountInWindow = fetchOnesCount(leftPointer, rightPointer, nums);
        minSwapsNeeded = updateMinSwapsNeeded(minSwapsNeeded, onesCount - onesCountInWindow);
        leftPointer++;
        rightPointer++;
        int flowCount = 1;

        while (flowCount == 1) {
            if (leftPointer == nums.length) {
                leftPointer = 0;
                flowCount++;
            } else {
                if (rightPointer == nums.length) {
                    rightPointer = 0;
                }

                if (nums[leftPointer - 1] == 1) {
                        onesCountInWindow--;
                    }

                if (nums[rightPointer] == 1) {
                        onesCountInWindow++;
                    }

                minSwapsNeeded = updateMinSwapsNeeded(minSwapsNeeded,
                            onesCount - onesCountInWindow);
            }
            leftPointer++;
            rightPointer++;
        }
        return minSwapsNeeded;
    }

    private int updateMinSwapsNeeded(int minSwapsNeeded, int swapCount) {
        return swapCount < minSwapsNeeded ? swapCount : minSwapsNeeded;
    }

    private int fetchOnesCount(int leftPointer, int rightPointer, int[] nums) {
        int onesCount = 0;
        while (leftPointer <= rightPointer) {
            if (nums[leftPointer] == 1) {
                onesCount++;
            }
            leftPointer++;
        }
        return onesCount;
    }

    private long fetchNumberOfOnesCount(int[] nums) {
        return Arrays.stream(nums).filter(num -> num == 1).count();
    }
}
