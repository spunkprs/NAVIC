import binarytree.DiagonalTravsersalOfBinaryTree;
import binarytree.TreeNode;
import org.junit.Before;
import org.junit.Test;

public class DiagonalTraversalOfBinaryTreeTest {

    private DiagonalTravsersalOfBinaryTree unit;

    @Before
    public void setUp() {
        unit = new DiagonalTravsersalOfBinaryTree();
    }

    @Test
    public void shouldDoDiagonalTravsersalOfBinaryTreeCaseOne() {
        TreeNode root = new TreeNode(10);
        TreeNode nodeOne = new TreeNode(20);
        TreeNode nodeTwo = new TreeNode(30);
        TreeNode nodeThree = new TreeNode(40);
        TreeNode nodeFour = new TreeNode(60);
        TreeNode nodeFive = new TreeNode(18);
        TreeNode nodeSix = new TreeNode(70);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);

        nodeTwo.setLeft(nodeFive);
        nodeThree.setRight(nodeSix);

        unit.diagonalPrint(root);
    }

}
