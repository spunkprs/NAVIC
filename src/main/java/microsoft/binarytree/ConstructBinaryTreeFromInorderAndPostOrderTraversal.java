package microsoft.binarytree;

public class ConstructBinaryTreeFromInorderAndPostOrderTraversal {

    private TreeNode root = null;
    private int postOrderCurrentIndex = -1;

    public static void main(String ar[]) {

        ConstructBinaryTreeFromInorderAndPostOrderTraversal unit = new ConstructBinaryTreeFromInorderAndPostOrderTraversal();

        int inOrder[] = {18, 22, 15, 20, 16, 24};
        int postOrder[] = {22, 18, 20, 24, 16, 15};

        TreeNode root = unit.buildTree(inOrder, postOrder);
        System.out.print(root);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        processToBuildTree(postorder, inorder, 0, inorder.length - 1, "", null);
        return root;
    }

    private void processToBuildTree(int[] postorder, int[] inorder, int inorderStartIndex, int inorderEndIndex, String notifier, TreeNode currentNode) {
        if (notifier.equals("")) {
            postOrderCurrentIndex = postorder.length - 1;
            currentNode = new TreeNode(postorder[postOrderCurrentIndex]);
            root = currentNode;

            int parentIndex = fetchIndexOfParentInInorder(postorder[postOrderCurrentIndex], inorder, inorderStartIndex, inorderEndIndex);

            if (parentIndex + 1 <= inorderEndIndex) {
                processToBuildTree(postorder, inorder, parentIndex + 1, inorderEndIndex, "R", currentNode);
            }

            if (parentIndex - 1 >= inorderStartIndex) {
                processToBuildTree(postorder, inorder, inorderStartIndex, parentIndex - 1, "L", currentNode);
            }
        } else if (notifier.equals("R")) {
            postOrderCurrentIndex -= 1;
            TreeNode node = new TreeNode(postorder[postOrderCurrentIndex]);
            currentNode.right = node;
            int parentIndex = fetchIndexOfParentInInorder(postorder[postOrderCurrentIndex], inorder, inorderStartIndex, inorderEndIndex);

            if (parentIndex + 1 <= inorderEndIndex) {
                processToBuildTree(postorder, inorder, parentIndex + 1, inorderEndIndex, "R", node);
            }

            if (parentIndex - 1 >= inorderStartIndex) {
                processToBuildTree(postorder, inorder, inorderStartIndex, parentIndex - 1, "L", node);
            }
        } else {
            postOrderCurrentIndex -= 1;
            TreeNode node = new TreeNode(postorder[postOrderCurrentIndex]);
            currentNode.left = node;

            int parentIndex = fetchIndexOfParentInInorder(postorder[postOrderCurrentIndex], inorder, inorderStartIndex, inorderEndIndex);

            if (parentIndex + 1 <= inorderEndIndex) {
                processToBuildTree(postorder, inorder, parentIndex + 1, inorderEndIndex, "R", node);
            }

            if (parentIndex - 1 >= inorderStartIndex) {
                processToBuildTree(postorder, inorder, inorderStartIndex, parentIndex - 1, "L", node);
            }
        }
    }

    private int fetchIndexOfParentInInorder(int value, int[] inorder, int startIndex, int endIndex) {
        int index = -1;
        for (int i = startIndex; i <= endIndex; i++) {
            if (inorder[i] == value) {
                index = i;
                break;
            }
        }
        return index;
    }

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        TreeNode(int val) { this.val = val; }
    }
}
