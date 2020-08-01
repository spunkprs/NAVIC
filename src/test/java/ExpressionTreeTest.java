import binarytree.ExpressionTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExpressionTreeTest {

    private ExpressionTree unit;

    @Before
    public void setUp() {
        unit = new ExpressionTree();
    }

    @Test
    public void shouldComputeExpressionCaseOne() {
        ExpressionTree.Node root = new ExpressionTree.Node("+");
        ExpressionTree.Node nodeOne = new ExpressionTree.Node("*");
        ExpressionTree.Node nodeTwo = new ExpressionTree.Node("-");
        ExpressionTree.Node nodeThree = new ExpressionTree.Node("5");
        ExpressionTree.Node nodeFour = new ExpressionTree.Node("4");
        ExpressionTree.Node nodeFive = new ExpressionTree.Node("100");
        ExpressionTree.Node nodeSix = new ExpressionTree.Node("20");

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);
        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);
        nodeTwo.setLeft(nodeFive);
        nodeTwo.setRight(nodeSix);
        Assert.assertEquals(100, unit.evalTree(root));
    }

    @Test
    public void shouldComputeExpressionCaseTwo() {
        ExpressionTree.Node root = new ExpressionTree.Node("-");
        ExpressionTree.Node nodeOne = new ExpressionTree.Node("4");
        ExpressionTree.Node nodeTwo = new ExpressionTree.Node("7");

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);
        Assert.assertEquals(-3, unit.evalTree(root));
    }
}
