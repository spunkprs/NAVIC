package binarytree;

import java.util.*;

/**
 In an infinite binary tree where every node has two children, the nodes are labelled in row order.

 In the odd numbered rows (ie., the first, third, fifth,...), the labelling is left to right,
 while in the even numbered rows (second, fourth, sixth,...), the labelling is right to left.

 Given the label of a node in this tree, return the labels in the path from the root of the tree
 to the node with that label.

 Constraints:

 1 <= label <= 10^6

 Source : LeetCode

 Time Complexity : O(log n), where n being the label/num
 Space Complexity : O(log n), where n being the label/num

 * */

public class PathInZigZagLabelledBinaryTree {

    public static void main(String ar[]) {
        PathInZigZagLabelledBinaryTree unit = new PathInZigZagLabelledBinaryTree();
        int label = 14;
        System.out.println("Path for label " + label + " is " + unit.pathInZigZagTree(label));
    }

    public List<Integer> pathInZigZagTree(int label) {
        int parentLevel =  fetchLevel(label);
        List<Integer> result = new ArrayList<>();
        Node node = new Node(label, parentLevel);

        if (parentLevel > 1) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(node);

            Stack<Integer> path = new Stack<>();

            while (!queue.isEmpty()) {
                Node peekedNode = queue.peek();
                int levelOfChild = peekedNode.level - 1;
                int childNum = peekedNode.num/2;
                if (levelOfChild > 1) {
                    Double maxChildDouble = Math.pow(2, levelOfChild) - 1;
                    int maxChild = maxChildDouble.intValue();
                    Double minChildDouble = Math.pow(2, levelOfChild - 1);
                    int minChild = minChildDouble.intValue();

                    int middleElement = (minChild + maxChild) / 2;

                    if (childNum <= middleElement) {
                        int updatedNum = maxChild - (childNum - minChild);
                        queue.add(new Node(updatedNum, levelOfChild));
                    } else {
                        int updatedNum = minChild + (maxChild - childNum);
                        queue.add(new Node(updatedNum, levelOfChild));
                    }
                } else if (levelOfChild == 1) {
                    queue.add(new Node(1, 1));
                }
                path.add(queue.poll().num);
            }
            populateList(path, result);
        } else {
            result.add(1);
        }
        return result;
    }

    private void populateList(Stack<Integer> path, List<Integer> result) {
        while (!path.isEmpty()) {
            result.add(path.pop());
        }
    }

    private int fetchLevel(int num) {
        Queue<Integer> queue = new LinkedList<>();
        while (num != 1) {
            queue.add(num);
            num /= 2;
        }
        queue.add(1);
        return queue.size();
    }

    static class Node {
        private int num;
        private int level;

        public Node(int num, int level) {
            this.num = num;
            this.level = level;
        }
    }


}
