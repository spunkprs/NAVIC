package binarytree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

 For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
 Return the root of the reversed tree.

 A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

 The level of a node is the number of edges along the path between it and the root node.


 Constraints:

 1.) The number of nodes in the tree is in the range [1, pow(2,14)].
 2.) 0 <= Node.val <= pow(10,5)
 3.) root is a perfect binary tree.

 * */

public class ReverseOddLevelOfBinaryTree {

    public static void main(String ar[]) {
        ReverseOddLevelOfBinaryTree unit = new ReverseOddLevelOfBinaryTree();

        TreeNode rootNode = new TreeNode(2);
        TreeNode nodeOne = new TreeNode(3);
        TreeNode nodeTwo = new TreeNode(5);

        TreeNode nodeThree = new TreeNode(8);
        TreeNode nodeFour = new TreeNode(13);
        TreeNode nodeFive = new TreeNode(21);
        TreeNode nodeSix = new TreeNode(34);

        rootNode.left = nodeOne;
        rootNode.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.left = nodeFive;
        nodeTwo.right = nodeSix;

        System.out.println("Tree post reversing nodes at odd level is " + unit.reverseOddLevels(rootNode));
    }

    public TreeNode reverseOddLevels(TreeNode root) {
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        processToReverse(root, 0, map);

        if (map.size() != 0) {
            for (Integer level : map.keySet()) {
                List<TreeNode> nodeListAtLevel = map.get(level);
                reverseNodes(nodeListAtLevel);
            }
        }
        return root;
    }

    private void reverseNodes(List<TreeNode> nodeListAtLevel) {
        int startIndex = 0;
        int endIndex = nodeListAtLevel.size() - 1;

        while (startIndex < endIndex) {
            TreeNode nodeOne = nodeListAtLevel.get(startIndex);
            TreeNode nodeTwo = nodeListAtLevel.get(endIndex);

            int val = nodeOne.val;
            nodeOne.val = nodeTwo.val;
            nodeTwo.val = val;

            startIndex++;
            endIndex--;
        }
    }

    private void processToReverse(TreeNode node, int level, Map<Integer, List<TreeNode>> map) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            if ((level + 1) % 2 != 0) {
                insertNodeInMap(level + 1, leftNode, map);
            }
            processToReverse(leftNode, level + 1, map);
        }

        if (rightNode != null) {
            if ((level + 1) % 2 != 0) {
                insertNodeInMap(level + 1, rightNode, map);
            }
            processToReverse(rightNode, level + 1, map);
        }
    }

    private void insertNodeInMap(int level, TreeNode node, Map<Integer, List<TreeNode>> map) {
        if (!map.containsKey(level)) {
            List<TreeNode> nodeList = new ArrayList<>();
            nodeList.add(node);
            map.put(level, nodeList);
        } else {
            map.get(level).add(node);
        }
    }

}
