package microsoft.binarytree;


/**
Problem : 606
Level : Medium
Link : https://leetcode.com/problems/construct-string-from-binary-tree/description/

Given the root node of a binary tree, your task is to create a string representation of the tree following a specific set of formatting rules.
The representation should be based on a preorder traversal of the binary tree and must adhere to the following guidelines:

a.) Node Representation: Each node in the tree should be represented by its integer value.

b.) Parentheses for Children: If a node has at least one child (either left or right), its children should be represented inside parentheses. Specifically:

 p.) If a node has a left child, the value of the left child should be enclosed in parentheses immediately following the node's value.
 q.) If a node has a right child, the value of the right child should also be enclosed in parentheses. The parentheses for the right child
 should follow those of the left child.

c.) Omitting Empty Parentheses: Any empty parentheses pairs (i.e., ()) should be omitted from the final string representation of the tree,
with one specific exception: when a node has a right child but no left child. In such cases, you must include an empty pair of parentheses
to indicate the absence of the left child. This ensures that the one-to-one mapping between the string representation and the original binary tree
structure is maintained.

In summary, empty parentheses pairs should be omitted when a node has only a left child or no children. However, when a node has a right child
but no left child, an empty pair of parentheses must precede the representation of the right child to reflect the tree's structure accurately.

Constraints:-

a.) The number of nodes in the tree is in the range [1, 10^4].
b.) -1000 <= Node.val <= 1000


Time Complexity : O(N)
Explicit Space Complexity : O(1)
Implicit Space Complexity : O(N), because of use of Recursive approach && in case tree is highly unbalanced then it could be O(N),
where N being depth of tree
 * */

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
