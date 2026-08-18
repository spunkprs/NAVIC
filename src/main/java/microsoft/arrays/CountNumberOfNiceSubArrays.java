package microsoft.arrays;

import java.util.LinkedList;
import java.util.Queue;

public class CountNumberOfNiceSubArrays {

    public static void main(String ar[]) {
        CountNumberOfNiceSubArrays unit = new CountNumberOfNiceSubArrays();
        int arr[] = {2,4,6};
        int k = 1;

        //int arr[] = {2,2,2,1,2,2,1,2,2,2};
        //int k = 2;

        System.out.println("Count of nice subarrays is " + unit.numberOfSubarrays(arr, k));
    }

    public int numberOfSubarrays(int[] nums, int k) {
        return processToComputeNumberOfNiceSubArrays(nums, k);
    }

    private int processToComputeNumberOfNiceSubArrays(int[] nums, int k) {
        int niceSubArrayCount = 0;
        int lastPulledIndex = -1;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0) {
                queue.add(i);
                if (queue.size() == k) {
                    niceSubArrayCount += queue.peek() - lastPulledIndex;
                } else if (queue.size() > k) {
                    lastPulledIndex = queue.poll();
                    niceSubArrayCount += queue.peek() - lastPulledIndex;
                }
            } else {
                if (queue.size() == k) {
                    niceSubArrayCount += queue.peek() - lastPulledIndex;
                }
            }
        }

        return niceSubArrayCount;
    }
}
