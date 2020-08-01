import binarytree.Node;
import binarytree.NumberOfTurns;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NumberOfTurnsTest {

    private Node root;

    @Before
    public void setUp() {
        root = new Node(1);
        Node nodeOne = new Node(2);
        Node nodeTwo = new Node(3);
        Node nodeThree = new Node(4);
        Node nodeFour = new Node(5);
        Node nodeFive = new Node(6);
        Node nodeSix = new Node(7);
        Node nodeSeven = new Node(8);
        Node nodeEight = new Node(9);
        Node nodeNine = new Node(10);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);
        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);
        nodeTwo.setLeft(nodeFive);
        nodeTwo.setRight(nodeSix);
        nodeThree.setLeft(nodeSeven);
        nodeFive.setLeft(nodeEight);
        nodeFive.setRight(nodeNine);
    }

    @Test
    public void shouldReturnNumberOfTurnsCaseOne() {
        Assert.assertEquals(3, NumberOfTurns.numberOfTurn(root, 5, 6));
    }

    @Test
    public void shouldReturnNumberOfTurnsCaseTwo() {
        Assert.assertEquals(4, NumberOfTurns.numberOfTurn(root, 5, 10));
    }

    @Test
    public void shouldReturnNumberOfTurnsCaseThree() {
        Assert.assertEquals(1, NumberOfTurns.numberOfTurn(root, 9, 7));
    }

    @Test
    public void shouldReturnNumberOfTurnsCaseFour() {
        Assert.assertEquals(0, NumberOfTurns.numberOfTurn(root, 9, 3));
    }

    @Test
    public void shouldReturnNumberOfTurnsCaseFive() {
        Assert.assertEquals(1, NumberOfTurns.numberOfTurn(root, 10, 3));
    }

}
