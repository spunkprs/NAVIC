package leetcode75;

import java.util.*;

/**
Problem : 45

You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.

Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i, you can jump to any index (i + j) where:

a.) 0 <= j <= nums[i] and
b.) i + j < n

Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1

Constraints:-

a.) 1 <= nums.length <= 104
b.) 0 <= nums[i] <= 1000
c.) It's guaranteed that you can reach nums[n - 1]

Note : Need to improve time complexity of the solution, improvised time is O(n * n)
 * */

public class JumpGame2 {

    public int jump(int[] nums) {

        int distance = 0;

        if (nums.length == 1) {
            return 0;
        }

        Node startNode = new Node(nums[0], 0, 0);

        Queue<Node> queue = new LinkedList();

        queue.add(startNode);

        Set<Node> exploredNodes = new HashSet();

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();

            for (Node childNode : fetchChildren(peekedNode, nums, exploredNodes)) {
                queue.add(childNode);
            }

            if (peekedNode.index == nums.length - 1) {
                distance = peekedNode.distance;
                break;
            }
            queue.poll();
        }

        return distance;
    }

    private List<Node> fetchChildren(Node peekedNode, int nums[], Set<Node> exploredNodes) {
        int index = peekedNode.index;
        int num = peekedNode.num;

        List<Node> childNodes = new ArrayList();

        int startIndex = 1;

        while (index + startIndex < nums.length && startIndex <= num) {
            Node preparedNode = new Node(nums[index + startIndex], index + startIndex, peekedNode.distance + 1);
            if (!exploredNodes.contains(preparedNode))  {
                childNodes.add(preparedNode);
            }
            startIndex++;
        }

        return childNodes;
    }

    class Node {
        private int num;
        private int index;
        private int distance;

        public Node (int num, int index, int distance) {
            this.num = num;
            this.index = index;
            this.distance = distance;
        }

        public int hashCode() {
            return Objects.hash(index);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof Node)) {
                return false;
            }

            Node other = (Node)obj;
            return this.index == other.index ? true : false;
        }
    }
}
