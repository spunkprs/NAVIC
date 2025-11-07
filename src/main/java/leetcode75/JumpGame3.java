package leetcode75;

import java.util.*;

public class JumpGame3 {


    public static void main(String ar[]) {
        JumpGame3 unit = new JumpGame3();
        int arr[] = {4,2,3,0,3,1,2};
        int startIndex = 5;

        System.out.print("Can reach to index having value 0 from startIndex " + startIndex + " " + unit.canReach(arr, startIndex));
    }

    public boolean canReach(int[] arr, int start) {
        if (arr.length == 1) {
            if (arr[0] == 0) {
                return true;
            }
            return false;
        }
        if (!checkForTheExistenceOfZero(arr)) {
            return false;
        } else {
            return processToComputeReachability(arr, start);
        }
    }

    private boolean checkForTheExistenceOfZero(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean processToComputeReachability(int arr[], int startIndex) {
        boolean result = false;
        Set<Integer> visitedIndexes = new HashSet();

        Queue<Integer> queue = new LinkedList();
        queue.add(startIndex);
        visitedIndexes.add(startIndex);

        while (!queue.isEmpty()) {
            int peekIndex = queue.peek();
            if (arr[peekIndex] != 0) {
                List<Integer> childIndexes = fetchChildIndexes(visitedIndexes, arr, peekIndex);
                for (Integer childIndex : childIndexes) {
                    queue.add(childIndex);
                    visitedIndexes.add(childIndex);
                }
                queue.poll();
            } else {
                result = true;
                break;
            }
        }
        return result;
    }

    private List<Integer> fetchChildIndexes(Set<Integer> visitedIndexes, int arr[], int index) {
        List<Integer> childIndexes = new ArrayList();
        if ((index + arr[index] < arr.length) && !(visitedIndexes.contains(index + arr[index]))) {
            childIndexes.add(index + arr[index]);
        }

        if ((index - arr[index] >= 0) && !(visitedIndexes.contains(index - arr[index]))) {
            childIndexes.add(index - arr[index]);
        }
        return childIndexes;
    }
}
