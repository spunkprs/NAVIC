package arrays.fastandslowpointers;

/*
*
There is a circular list of non-zero integers called nums. Each number in the list tells you how many steps to move forward or backward from your current position:

If nums[i] is positive, move nums[i] steps forward.

If nums[i] is negative, move nums[i] steps backward.

As the list is circular:

Moving forward from the last element takes you back to the first element.

Moving backward from the first element takes you to the last element.

A cycle in this list means:

You keep moving according to the numbers, and you end up repeating a sequence of indexes.

All numbers in the cycle have the same sign (either all positive or all negative).

The cycle length is greater than 1 (it involves at least two indexes).

Return true if such a cycle exists in the list or false otherwise.

Constraints:
1≤ nums.length ≤ pow(10, 3)
−5000 ≤ nums[i] ≤ 5000
nums[i] !=0

* * */

import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {

    private boolean cycleFound = false;

    public static void main(String ar[]) {
        CircularArrayLoop unit = new CircularArrayLoop();
        int nums[] = {5,4,-2,-1,3};
        //int nums[] = {1, 2, -3, 4, -2};
        System.out.print("Cycle detected in this circular array " + unit.circularArrayLoop(nums));
    }

    public boolean circularArrayLoop(int[] nums) {
        cycleFound = false;
        if (nums.length > 1) {
            processToCheckLoopInCircularArray(nums);
            return cycleFound;
        }
        return false;
    }

    private void processToCheckLoopInCircularArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(i)) {
                set.add(i);
                if (!cycleFound) {
                   checkExistenceOfLoop(nums, i,  i, set);
                }
            }
        }
    }

    private void checkExistenceOfLoop(int nums[], int slowPointerIndex, int fastPointerIndex, Set<Integer> set) {

        int fastPointerIndexNextIntermittent = fetchUpdatedIndex(nums, fastPointerIndex);
        int fastPointerIndexNext = fetchUpdatedIndex(nums, fastPointerIndexNextIntermittent);

        int slowPointerIndexNext = fetchUpdatedIndex(nums, slowPointerIndex);

        if (slowPointerIndex != slowPointerIndexNext && slowPointerIndexNext != -1) {
            set.add(slowPointerIndexNext);
        }

        if ((slowPointerIndex == slowPointerIndexNext) || (fastPointerIndexNextIntermittent == fastPointerIndexNext)) {
            return;
        }

        if (fastPointerIndexNext == slowPointerIndexNext) {
             cycleFound = true;
        } else {
            checkExistenceOfLoop(nums, slowPointerIndexNext, fastPointerIndexNext, set);
        }
    }

    private int fetchUpdatedIndex(int nums[], int index) {
        if (index != -1) {
            int num = nums[index];
            int stepsToJump = Math.abs(num);
            int updatedIndex = -1;
            if (num > 0) {
                if (stepsToJump % nums.length == 0) {
                    updatedIndex = index;
                } else {
                    int step = stepsToJump % nums.length;
                    int diffSteps = nums.length - 1 - index;
                    if (step > diffSteps) {
                        updatedIndex = step - diffSteps - 1;
                    } else {
                        updatedIndex = index + step;
                    }
                }

                if (nums[updatedIndex] < 0) {
                    updatedIndex = -1;
                }

            } else {
                if (stepsToJump % nums.length == 0) {
                    updatedIndex = index;
                } else {
                    int step = stepsToJump % nums.length;
                    if (step > index) {
                        updatedIndex = nums.length - (step - index);
                    } else {
                        updatedIndex = index - step;
                    }
                }

                if (nums[updatedIndex] > 0) {
                    updatedIndex = -1;
                }
            }
            return updatedIndex;
        } else {
            return -1;
        }
    }
}
