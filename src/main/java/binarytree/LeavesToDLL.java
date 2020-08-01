package binarytree;

public class LeavesToDLL {

    private Node head;
    private Node previous;

    public Node convertToDLL(Node root) {
        transformToDLL(root);
        return head;
    }

    public void pritntDLL() {

        Node n = previous;

        while (n != null) {
            System.out.print(n.data);
            System.out.print(" ");
            n = n.right;
        }

        System.out.println();

        n = head;
        while (n != null) {
            System.out.print(n.data);
            System.out.print(" ");
            n = n.left;
        }
    }

    private void transformToDLL(Node node) {
        Node leftNode = node.left;
        Node rightNode = node.right;

        if (leftNode == null && rightNode == null) {
            if (head == null) {
                head = node;
                previous = head;
            } else {
                node.right = previous;
                previous.left = node;
                previous = previous.left;
            }
        } else {

            if (rightNode != null) {
                transformToDLL(rightNode);
            }

            if (leftNode != null) {
                transformToDLL(leftNode);
            }

        }
    }
}
