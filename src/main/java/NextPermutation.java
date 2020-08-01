public class NextPermutation {

    public void nextPermutation(int[] nums) {
        boolean flag = true;
        int index = -1;
        for (int j = nums.length - 1; j >= 1; j--) {
            if (nums[j] > nums[j - 1]) {
                flag = false;
                index = j;
                break;
            }
        }

        if (flag) {
            sortByIndex(0, nums.length - 1, nums);
        } else {
            int greaterIndex = lookForNextGreaterIndex(index - 1, nums);
            int element = nums[index - 1];
            nums[index - 1] = nums[greaterIndex];
            nums[greaterIndex] = element;
            sortByIndex(index, nums.length - 1, nums);
        }
    }

    private int lookForNextGreaterIndex(int startingIndex, int[] nums) {
        int endIndex = nums.length - 1;
        while (nums[endIndex] <= nums[startingIndex] && endIndex >= startingIndex) {
            endIndex--;
        }
        return endIndex;
    }

    private void sortByIndex(int startIndex, int endIndex, int[] nums) {
        while (startIndex < endIndex) {
            int element = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = element;
            startIndex++;
            endIndex--;
        }
    }
}
