package microsoft.arrays;

import java.util.LinkedList;

public class MinimumOperationsToMakeArrayNonDecreasing {

    public static void main(String ar[]) {
        MinimumOperationsToMakeArrayNonDecreasing unit = new MinimumOperationsToMakeArrayNonDecreasing();
        //int arr[] = {3, 3, 2, 1};

        //int arr[] = {5 ,1, 2, 3};

        //int arr[] = {10, 12, 4};

        int arr[] = {18, 12, 7, 4, 30};

        System.out.print("Minimum possible sum is " + unit.minOperations(arr));
    }


    public long minOperations(int[] nums) {
        long numsLong[] = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            numsLong[i] = nums[i];
        }
        return processToComputeMinOperations(numsLong);
        //return 0;
    }

    private long processToComputeMinOperations(long[] nums) {
        boolean flag = true;
        int indexOne = 0;
        long minimumSum = 0;

        while (flag) {
            while (indexOne < nums.length - 1 && nums[indexOne] <= nums[indexOne + 1]) {
                indexOne++;
            }

            if (indexOne + 1 < nums.length && nums[indexOne] > nums[indexOne + 1]) {
                LinkedList<Integer> queueOne = new LinkedList<>();
                LinkedList<Integer> queueTwo = new LinkedList<>();

                int indexTwo = indexOne + 1;
                while (indexTwo + 1 < nums.length && nums[indexTwo + 1] <= nums[indexTwo]) {
                    queueOne.add(indexTwo);
                    indexTwo++;
                }
                if (indexTwo + 1 <= nums.length) {
                    queueOne.add(indexTwo);
                }

                indexTwo = indexOne + 1;
                while (indexTwo + 1 < nums.length && nums[indexTwo + 1] >= nums[indexTwo]) {
                    queueTwo.add(indexTwo);
                    indexTwo++;
                }
                if (indexTwo + 1 <= nums.length) {
                    queueTwo.add(indexTwo);
                }

                if (queueOne.size() > queueTwo.size()) {
                    long difference = Math.abs(nums[indexOne] - nums[queueOne.peekLast()]);
                    nums[queueOne.peekLast()] = nums[indexOne];
                    indexOne = queueOne.peekLast();
                    minimumSum += difference;
                } else if (queueOne.size() < queueTwo.size()) {
                    long difference = Math.abs(nums[indexOne] - nums[queueTwo.peekFirst()]);
                    nums[queueTwo.peekLast()] = nums[indexOne];
                    indexOne = queueTwo.peekLast();
                    minimumSum += difference;
                } else {
                    long difference = Math.abs(nums[indexOne] - nums[queueOne.peekLast()]);
                    nums[queueOne.peekLast()] = nums[indexOne];
                    indexOne = queueOne.peekLast();
                    minimumSum += difference;
                }
            } else {
                flag = false;
            }
        }
        return minimumSum;
    }
}
