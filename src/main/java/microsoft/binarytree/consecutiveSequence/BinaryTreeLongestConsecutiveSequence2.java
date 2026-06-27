package microsoft.binarytree.consecutiveSequence;


/**
Problem : 549
Link : https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/description/

Given the root of a binary tree, return the length of the longest consecutive path in the tree.

A consecutive path is a path where the values of the consecutive nodes in the path differ by one. This path can be either increasing or decreasing.

For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.


Constraints:-

a.) The number of nodes in the tree is in the range [1, 3 * 10^4].
b.) -3 * 10^4 <= Node.val <= 3 * 10^4

Time Complexity : O(N^2), where N being number of nodes in the tree in the worst case but this can be solved in O(N) as well
Space Complexity : O(H), where H could be N being number of nodes in the tree in the worst case
 * */

public class BinaryTreeLongestConsecutiveSequence2 {

    private int result = 0;
    private int intermittentResult = 0;

    public static void main(String ar[]) {
        BinaryTreeLongestConsecutiveSequence2 unit = new BinaryTreeLongestConsecutiveSequence2();

        TreeNode root = new TreeNode(1);
        TreeNode leftNode = new TreeNode(2);
        TreeNode rightNode = new TreeNode(3);

        root.left = leftNode;
        root.right = rightNode;

        System.out.print("Longest consecutive sequence is " + unit.longestConsecutive(root));
    }

    public int longestConsecutive(TreeNode root) {
        if (root != null) {
            processToComputeLongestConsecutiveSequencePhaseOne(root, 1, "");
            processToComputeLongestConsecutiveSequencePhaseTwo(root, 1, "");
        }
        return result;
    }

    private void processToComputeLongestConsecutiveSequencePhaseOne(TreeNode node, int depth, String path) {
        updateResult(depth);

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            if (leftNode.val - node.val == 1) {
                if (path.isEmpty() || path.equals("I")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(leftNode, depth + 1, "I");
                } else if (path.equals("D")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(leftNode, 2, "I");
                }
            } else if (node.val - leftNode.val == 1) {
                if (path.isEmpty() || path.equals("D")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(leftNode, depth + 1, "D");
                } else if (path.equals("I")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(leftNode, 2, "D");
                }
            } else {
                processToComputeLongestConsecutiveSequencePhaseOne(leftNode, 1, "");
            }
        }

        if (rightNode != null) {
            if (rightNode.val - node.val == 1) {
                if (path.isEmpty() || path.equals("I")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(rightNode, depth + 1, "I");
                } else if (path.equals("D")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(rightNode, 2, "I");
                }
            } else if (node.val - rightNode.val == 1) {
                if (path.isEmpty() || path.equals("D")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(rightNode, depth + 1, "D");
                } else if (path.equals("I")) {
                    processToComputeLongestConsecutiveSequencePhaseOne(rightNode, 2, "D");
                }
            } else {
                processToComputeLongestConsecutiveSequencePhaseOne(rightNode, 1, "");
            }
        }
    }

    private void processToComputeLongestConsecutiveSequencePhaseTwo(TreeNode node, int depth, String path) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null && rightNode != null) {
            if (leftNode.val - node.val == 1 && node.val - rightNode.val == 1) {
                int valOne, valTwo;
                process(leftNode, 2, "I");
                valOne = intermittentResult;
                intermittentResult = 0;
                process(rightNode, 2, "D");
                valTwo = intermittentResult;
                intermittentResult = 0;
                updateResult(valOne + valTwo - 1);
            } else if (node.val - leftNode.val == 1 && rightNode.val - node.val == 1) {
                int valOne, valTwo;
                process(leftNode, 2, "D");
                valOne = intermittentResult;
                intermittentResult = 0;
                process(rightNode, 2, "I");
                valTwo = intermittentResult;
                intermittentResult = 0;
                updateResult(valOne + valTwo - 1);
            }
        }

        if (leftNode != null) {
            processToComputeLongestConsecutiveSequencePhaseTwo(leftNode, 1, "");
        }

        if (rightNode != null) {
            processToComputeLongestConsecutiveSequencePhaseTwo(rightNode, 1, "");
        }
    }

    private void process(TreeNode node, int depth, String path) {
        updateIntermittentResult(depth);

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (path.equals("I")) {
            if (leftNode != null && leftNode.val - node.val == 1) {
                process(leftNode, depth + 1, "I");
            }
            if (rightNode != null && rightNode.val - node.val == 1) {
                process(rightNode, depth + 1, "I");
            }
        } else {
            if (leftNode != null && node.val - leftNode.val == 1) {
                process(leftNode, depth + 1, "D");
            }
            if (rightNode != null && node.val - rightNode.val == 1) {
                process(rightNode, depth + 1, "D");
            }
        }
    }

    private void updateResult(int depth) {
        result = depth > result ? depth : result;
    }

    private void updateIntermittentResult(int depth) {
        intermittentResult = depth > intermittentResult ? depth : intermittentResult;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) { this.val = val; }
    }

}
