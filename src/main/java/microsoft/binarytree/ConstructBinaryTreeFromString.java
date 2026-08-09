package microsoft.binarytree;

import java.util.Stack;

/**
Problem : 536
Level : Medium
Link : https://leetcode.com/problems/construct-binary-tree-from-string/description/

You need to construct a binary tree from a string consisting of parenthesis and integers.

The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.

You always start to construct the left child node of the parent first if it exists.

 Constraints:-

a.) 0 <= s.length <= 3 * 10^4
b.) s consists of digits, '(', ')', and '-' only.
c.) All numbers in the tree have value at most than 2^30.
 * */

public class ConstructBinaryTreeFromString {

    static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int val) { this.val = val; }
    }

    public static void main(String ar[]) {
        ConstructBinaryTreeFromString unit = new ConstructBinaryTreeFromString();
        String inputString = "-402(2(3)(1))(6(5)(-70))";
        TreeNode resultNode = unit.str2tree(inputString);
        System.out.print(resultNode);
    }

    public TreeNode str2tree(String s) {
        if (s.length() == 0) {
            return null;
        } else if (!s.contains("(")) {
            return new TreeNode(Integer.parseInt(s));
        }
        return processToBuildTreeFromSerializedString(s);
    }

    private TreeNode processToBuildTreeFromSerializedString(String s) {

        TreeNode rootNode = null;
        char arr[] = s.toCharArray();
        int index = 0;

        Stack<TreeNode> interimStack = new Stack<>();
        boolean rootNodePrepared = false;
        StringBuilder interimString = new StringBuilder();

        while (index < arr.length) {

            if (!rootNodePrepared) {
                if (arr[index] == '(') {
                    rootNodePrepared = true;
                    int rootValue = Integer.parseInt(interimString.toString());
                    rootNode = new TreeNode(rootValue);
                    interimStack.push(rootNode);
                    interimString = new StringBuilder();
                } else {
                    interimString.append(arr[index]);
                }
            } else {
                if (arr[index] == '(' && interimString.length() != 0) {
                    updateStack(interimStack, interimString);
                    interimString = new StringBuilder();
                } else if (arr[index] == ')') {
                    if (interimString.length() != 0) {
                        updateStack(interimStack, interimString);
                        interimString = new StringBuilder();
                    }
                    interimStack.pop();
                } else {
                    if (arr[index] == '-' || Character.isDigit(arr[index])) {
                        interimString.append(arr[index]);
                    }
                }
            }
            index++;
        }
        return rootNode;
    }

    private void updateStack(Stack<TreeNode> interimStack, StringBuilder interimString) {
        int nodeValue = Integer.parseInt(interimString.toString());
        TreeNode parentNode = interimStack.peek();
        TreeNode childNode = new TreeNode(nodeValue);
        if (parentNode.left == null) {
            parentNode.left = childNode;
        } else {
            parentNode.right = childNode;
        }
        interimStack.push(childNode);
    }


}
