package microsoft.binarytree;

import java.util.Stack;

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
