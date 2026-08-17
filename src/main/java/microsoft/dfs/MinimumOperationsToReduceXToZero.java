package microsoft.dfs;

import java.util.*;

public class MinimumOperationsToReduceXToZero {

    public static void main(String ar[]) {
        MinimumOperationsToReduceXToZero unit = new MinimumOperationsToReduceXToZero();
        int x = 4;
        int nums[] = {5,6,7,8,9};
        System.out.print("Minimum operations to reduce x to zero is " + unit.minOperations(nums, x));
    }

    public int minOperations(int[] nums, int x) {
        if (nums.length == 1) {
            return nums[0] == x ? 1 : -1;
        }
        Node node = new Node(0, nums.length - 1, x);
        Map<Node, Integer> memoizationMap = new HashMap<>();
        int result = processToComputeMinOperations(node, memoizationMap,  nums);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int processToComputeMinOperations(Node parentNode, Map<Node, Integer> memoizationMap, int nums[]) {
        if (!memoizationMap.containsKey(parentNode)) {
            if (parentNode.sum != 0) {
                int result = Integer.MAX_VALUE;

                for (Node childNode : fetchPossibleChildren(parentNode, nums)) {
                    int intermittentResult = processToComputeMinOperations(childNode, memoizationMap, nums);
                    result = Math.min(result, intermittentResult);
                }

                if (result != Integer.MAX_VALUE) {
                    memoizationMap.put(parentNode, result + 1);
                } else {
                    memoizationMap.put(parentNode, result);
                }
            } else {
                memoizationMap.put(parentNode, 0);
            }
        } else {
            return memoizationMap.get(parentNode);
        }
        return memoizationMap.get(parentNode);
    }

    private List<Node> fetchPossibleChildren(Node parentNode, int nums[]) {
        List<Node> childNodes = new ArrayList<>();
        int leftIndex = parentNode.leftIndex;
        int rightIndex = parentNode.rightIndex;
        int parentSum = parentNode.sum;

        Node childNodeOne = null;
        Node childNodeTwo = null;

        if (nums[leftIndex] <= parentSum) {
            childNodeOne = new Node(leftIndex + 1, rightIndex, parentSum - nums[leftIndex]);
        }

        if (nums[rightIndex] <= parentSum) {
             childNodeTwo = new Node(leftIndex, rightIndex - 1, parentSum - nums[rightIndex]);
        }

        if (childNodeOne != null && childNodeOne.leftIndex <= childNodeOne.rightIndex) {
            childNodes.add(childNodeOne);
        }

        if (childNodeTwo != null && childNodeTwo.leftIndex <= childNodeTwo.rightIndex) {
            childNodes.add(childNodeTwo);
        }

        return childNodes;
    }

    static class Node {
        private int leftIndex;
        private int rightIndex;
        private int sum;

        public Node(int leftIndex, int rightIndex, int sum) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return leftIndex == node.leftIndex && rightIndex == node.rightIndex && sum == node.sum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(leftIndex, rightIndex, sum);
        }
    }
}
