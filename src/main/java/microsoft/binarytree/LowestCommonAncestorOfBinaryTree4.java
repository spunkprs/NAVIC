package microsoft.binarytree;

import java.util.*;
import java.util.stream.Collectors;

public class LowestCommonAncestorOfBinaryTree4 {

    private boolean flag = false;
    private int finalDepth = 0;
    private boolean shallContinue = true;

    public static void main(String ar[]) {
        LowestCommonAncestorOfBinaryTree4 unit = new LowestCommonAncestorOfBinaryTree4();

        TreeNode root = new TreeNode(3);
        TreeNode nodeOne = new TreeNode(5);
        TreeNode nodeTwo = new TreeNode(1);
        TreeNode nodeThree = new TreeNode(6);
        TreeNode nodeFour = new TreeNode(2);
        TreeNode nodeFive = new TreeNode(0);
        TreeNode nodeSix = new TreeNode(8);
        TreeNode nodeSeven = new TreeNode(7);
        TreeNode nodeEight = new TreeNode(4);

        root.left = nodeOne;
        root.right = nodeTwo;

        nodeOne.left = nodeThree;
        nodeOne.right = nodeFour;

        nodeTwo.left = nodeFive;
        nodeTwo.right = nodeSix;

        nodeFour.left = nodeSeven;
        nodeFour.right = nodeEight;

        TreeNode nodes[] = {nodeSeven, nodeThree, nodeFour, nodeEight};

        //TreeNode nodes[] = {nodeEight, nodeSeven};

        //TreeNode nodes[] = {nodeThree, nodeSeven, nodeFive};

        TreeNode result = unit.lowestCommonAncestor(root, nodes);

        System.out.print("LCA for the tree where root is " + root.val +
                " and for the provided nodes is " + result.val);
    }


    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }


    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (nodes.length > 1) {
            Map<Integer, Node> intermittentMap = new HashMap<>();
            Set<Integer> existingNodes = Arrays.asList(nodes).stream()
                    .map(x -> x.val)
                    .collect(Collectors.toSet());

            processToPrepareIntermittentMap(root, intermittentMap, existingNodes);

            int minDepth = fetchMinDepth(intermittentMap);
            int maxDepth = fetchMaxDepth(intermittentMap);

            Node nodeOne = intermittentMap.get(minDepth);
            Node nodeTwo = intermittentMap.get(maxDepth);

            return fetchLCAProcess(nodeOne, nodeTwo, root);
        }
        return nodes[0];
    }

    private TreeNode fetchLCAProcess(Node nodeOne, Node nodeTwo, TreeNode node) {
        List<TreeNode> resultOne = new ArrayList<>();
        List<TreeNode> resultTwo = new ArrayList<>();

        fetchLca(nodeOne.leftValue, node, resultOne);
        flag = false;
        fetchLca(nodeTwo.rightValue, node, resultTwo);

        int indexOne = 0;
        int indexTwo = 0;

        while (indexOne < resultOne.size() &&
                indexTwo < resultTwo.size() &&
                resultOne.get(indexOne) == resultTwo.get(indexTwo)) {
            indexOne++;
            indexTwo++;
        }

        TreeNode resultLcaOne = resultOne.get(indexOne - 1);

        resultOne = new ArrayList<>();
        resultTwo = new ArrayList<>();

        indexOne = 0;
        indexTwo = 0;

        flag = false;
        fetchLca(nodeOne.rightValue, node, resultOne);
        flag = false;
        fetchLca(nodeTwo.leftValue, node, resultTwo);


        while (indexOne < resultOne.size() &&
                indexTwo < resultTwo.size() &&
                resultOne.get(indexOne) == resultTwo.get(indexTwo)) {
            indexOne++;
            indexTwo++;
        }

        TreeNode resultLcaTwo = resultOne.get(indexOne - 1);

        if (resultLcaOne.val == resultLcaTwo.val) {
            return resultLcaOne;
        } else {
            fetchDepth(node, resultLcaOne.val, 0);
            int depthOne = finalDepth;
            finalDepth = 0;
            shallContinue = true;

            fetchDepth(node, resultLcaTwo.val, 0);
            int depthTwo = finalDepth;

            return depthOne < depthTwo ? resultLcaOne : resultLcaTwo;
        }
    }

    private void fetchDepth(TreeNode node, int value, int depth) {
        if (node.val == value) {
            finalDepth = depth;
            shallContinue = false;
        } else {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;

            if (leftNode != null && shallContinue) {
                fetchDepth(leftNode, value, depth + 1);
            }

            if (rightNode != null && shallContinue) {
                fetchDepth(rightNode, value, depth + 1);
            }
        }
    }

    private void fetchLca(Integer value, TreeNode node, List<TreeNode> result) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (node.val != value) {
            result.add(node);

            if (leftNode != null && !flag) {
                fetchLca(value, leftNode, result);
            }

            if (rightNode != null && !flag) {
                fetchLca(value, rightNode, result);
            }
            if (!flag) {
                result.remove(result.size() - 1);
            }
        } else {
            result.add(node);
            flag = true;
        }
    }

    private int fetchMinDepth(Map<Integer, Node> intermittentMap) {
        return intermittentMap.keySet().stream().min(Comparator.naturalOrder()).get();
    }

    private int fetchMaxDepth(Map<Integer, Node> intermittentMap) {
        return intermittentMap.keySet().stream().max(Comparator.naturalOrder()).get();
    }

    private void processToPrepareIntermittentMap(TreeNode node,
                                                 Map<Integer, Node> intermittentMap,
                                                 Set<Integer> existingNodes) {

        process(node, 0, 0, intermittentMap, existingNodes);
    }

    private void process(TreeNode node, int depth, int horizontalAlignment,
                         Map<Integer, Node> intermittentMap,
                         Set<Integer> existingNodes) {

        if (existingNodes.contains(node.val)) {
            if (!intermittentMap.containsKey(depth)) {
                Node n = new Node(node.val, node.val);
                intermittentMap.put(depth, n);
            } else {
                Node n = intermittentMap.get(depth);
                n.rightValue = node.val;
            }
        }

        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;

        if (leftNode != null) {
            process(leftNode, depth + 1, horizontalAlignment - 1,
                    intermittentMap, existingNodes);
        }

        if (rightNode != null) {
            process(rightNode, depth + 1, horizontalAlignment + 1,
                    intermittentMap, existingNodes);
        }
    }

    static class Node {
        private Integer leftValue;
        private Integer rightValue;

        public Node(Integer leftValue, Integer rightValue) {
            this.leftValue = leftValue;
            this.rightValue = rightValue;
        }
    }

}
