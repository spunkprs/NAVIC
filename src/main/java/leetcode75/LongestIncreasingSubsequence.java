package leetcode75;

import java.util.*;

public class LongestIncreasingSubsequence {

    public static void main(String ar[]) {
        LongestIncreasingSubsequence unit = new LongestIncreasingSubsequence();
        int nums[] = {1,3,6,7,9,4,10,5,6};
        System.out.print("Length of longest increasing subsequence is " + unit.lengthOfLISOptimisricApproach(nums));
    }

    public int lengthOfLISOptimisricApproach(int[] nums) {
        int result = 0;
        int numsStorage[] = new int[nums.length];
        TreeMap<Node, List<Integer>> treeMap = new TreeMap<>(new NodeComparator());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> {
            return a < b ? 1 : a > b ? -1 : 0;
        });

        Node node = new Node(nums[0], 0);
        numsStorage[0] = 1;

        List<Integer> list = new ArrayList<>();
        list.add(0);
        treeMap.put(node, list);


        for (int j = 1; j < nums.length; j++) {
            Node generatedNode = new Node(nums[j], j);
            Node lowerNode = treeMap.lowerKey(generatedNode);

            if (lowerNode != null) {
                List<Integer> indexes = treeMap.get(lowerNode);
                for (Integer index : indexes) {
                    maxHeap.add(numsStorage[index]);
                }
                numsStorage[j] = maxHeap.peek() + 1;
                maxHeap.clear();
            } else {
                numsStorage[j] = 1;
            }

            if (!treeMap.containsKey(generatedNode)) {
                List<Integer> listOne = new ArrayList<>();
                listOne.add(j);
                treeMap.put(generatedNode, listOne);
            } else {
                treeMap.get(generatedNode).add(j);
            }
        }

        for (int i = 0; i < numsStorage.length; i++) {
            result = numsStorage[i] > result ? numsStorage[i] : result;
        }
        return result;
    }

    static class Node {
        private int value;
        private int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

    }

    class NodeComparator implements Comparator<Node> {
       public int compare(Node nOne, Node nTwo) {
            return nOne.value < nTwo.value ? -1 : nOne.value > nTwo.value ? 1 : 0;
        }
    }
}
