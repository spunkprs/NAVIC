import binarytree.LeavesToDLL;
import binarytree.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LeavesToDLLTest {

    private LeavesToDLL unit;

    @Before
    public void setUp() {
        unit = new LeavesToDLL();
    }

    @Test
    public void shouldConvertLevesToDLLCaseOne() {
        Node root = new Node(1);
        Node nodeOne = new Node(2);
        Node nodeTwo = new Node(3);
        Node nodeThree = new Node(4);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);
        nodeOne.setLeft(nodeThree);

        Node head = unit.convertToDLL(root);
        Assert.assertNotNull(head);
    }

    @Test
    public void shouldConvertLevesToDLLCaseTwo() {
        Node root = new Node(1);
        Node nodeOne = new Node(2);
        Node nodeTwo = new Node(3);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);


        Node head = unit.convertToDLL(root);
        Assert.assertNotNull(head);
    }
}
