package microsoft.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
Problem : 515
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).

Constraints:-

a.) The number of nodes in the tree will be in the range [0, 10^4].
b.) -2^31 <= Node.val <= 2^31 - 1

Level : Medium
Link : https://leetcode.com/problems/find-largest-value-in-each-tree-row/description/?envType=problem-list-v2&envId=depth-first-search

Time Complexity : O(N)
Explicit Space Complexity : O(1)
Implicit Space Complexity : O(N), because of use of Recursive approach && in case tree is highly unbalanced then it could be O(N),
where N being depth of tree
 * */

public class FindLargestValueInEachTreeRow {

    public static void main(String ar[]) {
        FindLargestValueInEachTreeRow unit = new FindLargestValueInEachTreeRow();

        TreeNode root = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(3);
        TreeNode nodeTwo = new TreeNode(2);
        TreeNode nodeThree = new TreeNode(5);
        TreeNode nodeFour = new TreeNode(3);
        TreeNode nodeFive = new TreeNode(9);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.right = nodeFive;

        List<Integer> resultList = unit.largestValues(root);
        System.out.print(resultList);
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }


    public List<Integer> largestValues(TreeNode root) {
        List<Integer> resultList = new ArrayList<>();
            if (root != null) {
                processToFindLargestValueAgainstEachRow(root, resultList, 0);
            }
            return resultList;
    }

    private void processToFindLargestValueAgainstEachRow(TreeNode node, List<Integer> resultList, int depth) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (depth == 0) {
            resultList.add(node.val);
        } else {
            if (depth > resultList.size() - 1) {
                resultList.add(node.val);
            } else {
                int existingElement = resultList.get(depth);
                if (node.val > existingElement) {
                    resultList.set(depth, node.val);
                }
            }
        }

        if (leftNode != null) {
            processToFindLargestValueAgainstEachRow(leftNode, resultList, depth + 1);
        }

        if (rightNode != null) {
            processToFindLargestValueAgainstEachRow(rightNode, resultList, depth + 1);
        }
    }
}
