import binarytree.Node;
import binarytree.NodesAtDistanceK;
import org.junit.Test;

public class NodesAtDistanceKTest {

    @Test
    public void shouldPrintNodesAtDistanceKCaseOne() {
        Node root = new Node(20);
        Node nodeOne = new Node(8);
        Node nodeTwo = new Node(22);
        Node nodeThree = new Node(4);
        Node nodeFour = new Node(12);
        Node nodeFive = new Node(10);
        Node nodeSix = new Node(14);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);

        nodeFour.setLeft(nodeFive);
        nodeFour.setRight(nodeSix);

        NodesAtDistanceK.printkdistanceNode(root, nodeOne, 2);
    }

    @Test
    public void shouldPrintNodesAtDistanceKCaseTwo() {
        Node root = new Node(15);
        Node nodeOne = new Node(8);
        Node nodeTwo = new Node(17);
        Node nodeThree = new Node(16);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeTwo.setLeft(nodeThree);

        NodesAtDistanceK.printkdistanceNode(root, nodeTwo, 1);
    }

    @Test
    public void shouldPrintNodesAtDistanceKCaseFour() {
        Node root = new Node(11);
        Node nodeOne = new Node(10);
        Node nodeTwo = new Node(14);
        Node nodeThree = new Node(4);
        Node nodeFour = new Node(7);
        Node nodeFive = new Node(16);
        Node nodeSix = new Node(9);
        Node nodeSeven = new Node(18);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);

        nodeTwo.setRight(nodeFive);

        nodeFour.setRight(nodeSix);
        nodeFive.setRight(nodeSeven);

        NodesAtDistanceK.printkdistanceNode(root, nodeFive, 1);
    }
}
