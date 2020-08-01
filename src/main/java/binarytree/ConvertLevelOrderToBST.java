package binarytree;

public class ConvertLevelOrderToBST {

    public Node constructBST(int[] arr){
        return prepareBST(arr);
    }

    private Node prepareBST(int[] arr) {
        Node root = null;
        for (int i = 0; i < arr.length; i++) {
            if (root == null && i == 0) {
                root = new Node(arr[i]);
            }
            if (i != 0) {
                insertNodeInBST(root, arr[i]);
            }
        }
        return root;
    }

    private void insertNodeInBST(Node root, int num) {
        Node node = root;
        Node parent = null;
        int flag = 0;
        while (node != null) {
            parent = node;
            if (num <= node.data) {
                node = node.left;
                flag = 1;
            } else {
                node = node.right;
                flag = 2;
            }
        }

        if (flag == 1) {
            Node n = new Node(num);
            parent.left = n;
        } else if (flag == 2) {
            Node n = new Node(num);
            parent.right = n;
        }
    }
}