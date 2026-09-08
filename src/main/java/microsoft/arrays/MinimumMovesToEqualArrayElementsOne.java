package microsoft.arrays;

import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsOne {

    public static void main(String ar[]) {
        MinimumMovesToEqualArrayElementsOne unit = new MinimumMovesToEqualArrayElementsOne();
        int elements[] = {-8, 16, 12, 10};
        System.out.print("Minimum moves to equal array elements is " + unit.minMoves(elements));
    }

    public int minMoves(int[] nums) {
        int result = 0;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            result += nums[i] - nums[0];
        }
        return result;
    }
}
