import binarytree.Node;
import binarytree.NodesHavingKLeaves;
import org.junit.Before;
import org.junit.Test;

public class NodesHavingKLeavesTest {

    private NodesHavingKLeaves unit;

    @Before
    public void setUp() {
        unit = new NodesHavingKLeaves();
    }

    @Test
    public void shouldPrintNodesWithKLeavesCaseOne() {
        Node root = new Node(0);
        Node nodeOne = new Node(1);
        Node nodeTwo = new Node(2);
        Node nodeThree = new Node(4);
        Node nodeFour = new Node(3);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeTwo.setLeft(nodeThree);
        nodeTwo.setRight(nodeFour);

        unit.btWithKleaves(root, 2);
    }

    @Test
    public void shouldPrintNodesWithKLeavesCaseTwo() {
        Node root = new Node(0);
        Node nodeOne = new Node(1);
        Node nodeTwo = new Node(2);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        unit.btWithKleaves(root, 1);
    }
}
