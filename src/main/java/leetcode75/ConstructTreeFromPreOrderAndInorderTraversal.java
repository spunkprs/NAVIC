package leetcode75;

import java.util.HashMap;
import java.util.Map;

public class ConstructTreeFromPreOrderAndInorderTraversal {


    public static void main(String ar[]) {
        ConstructTreeFromPreOrderAndInorderTraversal unit = new ConstructTreeFromPreOrderAndInorderTraversal();
        int preorder[] = {10, 20, 40, 50, 90, 100, 30, 60};
        int inorder[] = {50, 40, 20, 90, 100, 10, 30, 60};
        TreeNode result = unit.buildTree(preorder, inorder);
        System.out.print(result);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = prepareMapAgainstInorder(inorder);
        TreeNode root = new TreeNode(preorder[0]);
        Pair pair = new Pair(0, inorder.length - 1);
        iterateThroughPreorderAndBuildTree(preorder, map, root, 1, pair);
        return root;
    }

    private TreeNode iterateThroughPreorderAndBuildTree(int[] preorder, Map<Integer, Integer> map,
                                                    TreeNode parentNode, int startIndex, Pair parentPair) {

        if (startIndex < preorder.length) {
            int parentNodeIndex = map.get(parentNode.val);
            Pair leftPair = new Pair(parentPair.leftIndex, parentNodeIndex - 1);
            Pair rightPair = new Pair(parentNodeIndex + 1,  parentPair.rightIndex);

            int valIndex = map.get(preorder[startIndex]);

            if (leftPair.leftIndex <= leftPair.rightIndex && leftPair.leftIndex >= 0 && leftPair.rightIndex >= 0 &&
                    valIndex >= leftPair.leftIndex && valIndex <= leftPair.rightIndex) {
                TreeNode leftNode = iterateThroughPreorderAndBuildTree(preorder, map, new TreeNode(preorder[startIndex]),
                        startIndex + 1, leftPair);
                parentNode.left = leftNode;
                if (startIndex + (leftPair.rightIndex - leftPair.leftIndex) + 1 < preorder.length) {
                    startIndex += (leftPair.rightIndex - leftPair.leftIndex) + 1;
                    valIndex = map.get(preorder[startIndex]);
                }
            }

            if (rightPair.leftIndex <= rightPair.rightIndex && rightPair.leftIndex < preorder.length &&
                    rightPair.rightIndex < preorder.length && valIndex >= rightPair.leftIndex && valIndex <= rightPair.rightIndex) {
                TreeNode rightNode = iterateThroughPreorderAndBuildTree(preorder, map, new TreeNode(preorder[startIndex]),
                        startIndex + 1, rightPair);
                parentNode.right = rightNode;
            }
        }
        return parentNode;
    }

    static class Pair {
        int leftIndex;
        int rightIndex;

        public Pair(int leftIndex, int rightIndex) {
            this.leftIndex = leftIndex;
            this.rightIndex = rightIndex;
        }
    }

    private Map<Integer, Integer> prepareMapAgainstInorder(int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return map;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }
}
