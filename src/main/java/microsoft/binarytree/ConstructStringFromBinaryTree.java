package microsoft.binarytree;

public class ConstructStringFromBinaryTree {

    private StringBuilder sb = new StringBuilder();

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }


    public static void main(String ar[]) {
        ConstructStringFromBinaryTree unit = new ConstructStringFromBinaryTree();
        TreeNode rootNode = new TreeNode(1);
        TreeNode nodeOne = new TreeNode(2);
        TreeNode nodeTwo = new TreeNode(3);
        TreeNode nodeThree = new TreeNode(4);

        rootNode.left = nodeOne;
        rootNode.right = nodeTwo;

        nodeOne.left = nodeThree;

        String resultString = unit.tree2str(rootNode);
        System.out.print(resultString);
    }

    public String tree2str(TreeNode root) {
        processToGenerateStringFromBinaryTree(root);
        return sb.toString();
    }

    private void processToGenerateStringFromBinaryTree(TreeNode node) {
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        sb.append(node.val);

        if (leftNode != null && rightNode != null) {
                sb.append("(");
                processToGenerateStringFromBinaryTree(leftNode);
                sb.append(")");

                sb.append("(");
                processToGenerateStringFromBinaryTree(rightNode);
                sb.append(")");
        } else if (leftNode == null && rightNode != null) {
            sb.append("()");
            sb.append("(");
            processToGenerateStringFromBinaryTree(rightNode);
            sb.append(")");
        } else if (leftNode != null && rightNode == null) {
            sb.append("(");
            processToGenerateStringFromBinaryTree(leftNode);
            sb.append(")");
        }
    }
}
