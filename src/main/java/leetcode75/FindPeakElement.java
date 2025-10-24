package leetcode75;

public class FindPeakElement {

    private int peakElementIndex = -1;
    private int peakElement = Integer.MIN_VALUE;

    public static void main(String ar[]) {
        FindPeakElement unit = new FindPeakElement();
        int nums[] = {1, 2, 3, 4};
        System.out.print("Peak element having index = " + unit.findPeakElement(nums));
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        } else if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                peakElementIndex = 0;
            } else {
                peakElementIndex = 1;
            }
        }
        processToFindPeakElement(nums);
        return peakElementIndex;
    }

    private void processToFindPeakElement(int nums[]) {
        process(nums, 0, nums.length - 1);
    }

    private void process(int nums[], int beginIndex, int endIndex) {
        if (endIndex - beginIndex > 1) {
            int midIndex = (beginIndex + endIndex) / 2;
            process(nums, beginIndex, midIndex);
            process(nums, midIndex + 1, endIndex);

            if ((nums[midIndex] > nums[midIndex - 1]) &&
                    (nums[midIndex] > nums[midIndex + 1])) {
                updateIndex(midIndex, nums[midIndex]);
            }

            if (midIndex - 1 == 0) {
                if (nums[midIndex - 1] > nums[midIndex]) {
                    updateIndex(midIndex - 1, nums[midIndex - 1]);
                }
            }

            if (midIndex + 2 < nums.length) {
                if ((nums[midIndex + 1] > nums[midIndex + 2]) &&
                        (nums[midIndex + 1] > nums[midIndex])) {
                    updateIndex(midIndex + 1, nums[midIndex + 1]);
                }
                if (midIndex + 2 == nums.length - 1) {
                    if (nums[midIndex + 2] > nums[midIndex + 1]) {
                        updateIndex(midIndex + 2, nums[midIndex + 2]);
                    }
                }
            } else if (midIndex + 2 == nums.length) {
                if (nums[midIndex + 1] > nums[midIndex]) {
                    updateIndex(midIndex + 1, nums[midIndex + 1]);
                }
            }
        }
    }

    private void updateIndex(int elementIndex, int element) {
        if (element > peakElement) {
            peakElementIndex = elementIndex;
            peakElement = element;
        }
    }

}
