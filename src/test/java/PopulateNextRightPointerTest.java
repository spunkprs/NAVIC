import binarytree.PopulateNextRightPointer;
import org.junit.Before;
import org.junit.Test;

public class PopulateNextRightPointerTest {

    private PopulateNextRightPointer unit;

    @Before
    public void setUp() {
        unit = new PopulateNextRightPointer();
    }

    @Test
    public void shouldPopulateNextRightPointerIterativeApproachCaseOne() {
        PopulateNextRightPointer.TreeLinkNode root = new PopulateNextRightPointer.TreeLinkNode(10);
        PopulateNextRightPointer.TreeLinkNode nodeOne = new PopulateNextRightPointer.TreeLinkNode(8);
        PopulateNextRightPointer.TreeLinkNode nodeTwo = new PopulateNextRightPointer.TreeLinkNode(2);
        PopulateNextRightPointer.TreeLinkNode nodeThree = new PopulateNextRightPointer.TreeLinkNode(3);
        PopulateNextRightPointer.TreeLinkNode nodeFour = new PopulateNextRightPointer.TreeLinkNode(90);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);
        nodeOne.setLeft(nodeThree);
        nodeTwo.setRight(nodeFour);

        unit.connect(root);
    }

    @Test
    public void shouldPopulateNextRightPointerIterativeApproachCaseTwo() {
        PopulateNextRightPointer.TreeLinkNode root = new PopulateNextRightPointer.TreeLinkNode(1);
        PopulateNextRightPointer.TreeLinkNode nodeOne = new PopulateNextRightPointer.TreeLinkNode(2);
        PopulateNextRightPointer.TreeLinkNode nodeTwo = new PopulateNextRightPointer.TreeLinkNode(3);
        PopulateNextRightPointer.TreeLinkNode nodeThree = new PopulateNextRightPointer.TreeLinkNode(4);
        PopulateNextRightPointer.TreeLinkNode nodeFour = new PopulateNextRightPointer.TreeLinkNode(5);
        PopulateNextRightPointer.TreeLinkNode nodeFive = new PopulateNextRightPointer.TreeLinkNode(6);
        PopulateNextRightPointer.TreeLinkNode nodeSix = new PopulateNextRightPointer.TreeLinkNode(7);
        PopulateNextRightPointer.TreeLinkNode nodeSeven = new PopulateNextRightPointer.TreeLinkNode(9);
        PopulateNextRightPointer.TreeLinkNode nodeEight = new PopulateNextRightPointer.TreeLinkNode(10);
        PopulateNextRightPointer.TreeLinkNode nodeNine = new PopulateNextRightPointer.TreeLinkNode(20);
        PopulateNextRightPointer.TreeLinkNode nodeTen = new PopulateNextRightPointer.TreeLinkNode(68);
        PopulateNextRightPointer.TreeLinkNode nodeEleven = new PopulateNextRightPointer.TreeLinkNode(30);
        PopulateNextRightPointer.TreeLinkNode nodeTwelve = new PopulateNextRightPointer.TreeLinkNode(50);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);
        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);
        nodeTwo.setLeft(nodeFive);
        nodeTwo.setRight(nodeSix);
        nodeFour.setLeft(nodeSeven);
        nodeFour.setRight(nodeEight);
        nodeSix.setRight(nodeNine);
        nodeSeven.setLeft(nodeTen);
        nodeNine.setLeft(nodeEleven);
        nodeNine.setRight(nodeTwelve);
        unit.connect(root);
    }
}
