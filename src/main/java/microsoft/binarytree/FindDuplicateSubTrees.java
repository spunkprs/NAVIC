package microsoft.binarytree;

import java.util.*;

public class FindDuplicateSubTrees {

    public static void main(String ar[]) {
        FindDuplicateSubTrees unit = new FindDuplicateSubTrees();
        TreeNode rootNode = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(2);
        TreeNode nodeTwo = new TreeNode(3);
        TreeNode nodeThree = new TreeNode(4);
        TreeNode nodeFour = new TreeNode(2);
        TreeNode nodeFive = new TreeNode(4);
        TreeNode nodeSix = new TreeNode(4);

        rootNode.left = nodeOne;
        rootNode.right = nodeTwo;

        nodeOne.left = nodeThree;

        nodeTwo.left = nodeFour;
        nodeTwo.right = nodeFive;

        nodeFour.left = nodeSix;

        List<TreeNode> treeNodeList = unit.findDuplicateSubtrees(rootNode);
        System.out.print(treeNodeList);
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> resultList = new ArrayList<>();
        Map<String, Integer> visitedStringMap = new HashMap<>();

        processToFindDuplicateSubTrees(root, resultList, visitedStringMap);
        return resultList;
    }

    private String processToFindDuplicateSubTrees(TreeNode node, List<TreeNode> resultList, Map<String, Integer> visitedStringMap) {
        StringBuilder sb = new StringBuilder();
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            sb.append("(");
            sb.append(processToFindDuplicateSubTrees(leftNode, resultList, visitedStringMap));
            sb.append(")");
        } else {
            sb.append("()");
        }

        sb.append(node.val);

        if (rightNode != null) {
            sb.append("(");
            sb.append(processToFindDuplicateSubTrees(rightNode, resultList, visitedStringMap));
            sb.append(")");
        } else {
            sb.append("()");
        }

        String resultString = sb.toString();

        if (!visitedStringMap.containsKey(resultString)) {
            visitedStringMap.put(resultString, 1);
        } else {
            if (visitedStringMap.get(resultString) == 1) {
                visitedStringMap.put(resultString, 2);
                resultList.add(node);
            }
        }

        return resultString;
    }
}
