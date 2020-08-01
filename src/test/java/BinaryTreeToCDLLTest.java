import binarytree.BinaryTreeToCDLL;
import binarytree.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeToCDLLTest {

    private BinaryTreeToCDLL unit;

    @Before
    public void setUp() {
        unit = new BinaryTreeToCDLL();
    }

    @Test
    public void shouldConvertBinaryTreeTOCDLLCaseOne() {
        Node root = new Node(10);
        Node nodeOne = new Node(20);
        Node nodeTwo = new Node(30);
        Node nodeThree = new Node(40);
        Node nodeFour = new Node(60);
        Node nodeFive = new Node(18);
        Node nodeSix = new Node(44);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);

        nodeTwo.setLeft(nodeFive);
        nodeTwo.setRight(nodeSix);

        Node headOfCDLL = unit.bTreeToClist(root);
        Assert.assertNotNull(headOfCDLL);
    }
}
