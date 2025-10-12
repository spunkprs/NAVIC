package leetcode75;



import java.util.LinkedList;
import java.util.Queue;

public class MInimumSizeSubArraySum {

    public static void main(String ar[]) {

        MInimumSizeSubArraySum unit = new MInimumSizeSubArraySum();
        int nums[] = {2,3,1,2,4,3};
        int target = 7;

        System.out.print(unit.minSubArrayLen(target, nums));
    }

    public int minSubArrayLen(int target, int[] nums) {

        Queue<Integer> linkedList = new LinkedList();

        return processToFindMinSubArray(nums, target, linkedList);
    }

    private int processToFindMinSubArray(int nums[], int target, Queue<Integer> linkedList) {
        int minimumLength = Integer.MAX_VALUE;
        int sum = 0;

        int index = 0;
        while (index < nums.length) {
            sum += nums[index];
            linkedList.add(index);
            if (sum >= target) {
                while (sum >= target) {
                    minimumLength = updateSize(index - linkedList.peek() + 1, minimumLength);
                    sum -= nums[linkedList.peek()];
                    linkedList.poll();
                }
            }
            index++;
        }
        return minimumLength == Integer.MAX_VALUE ? 0 : minimumLength;
    }

    private int updateSize(int size, int maxSize) {
        return size <= maxSize ? size : maxSize;
    }
}
