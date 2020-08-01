import binarytree.SymmetricBinaryTree;
import binarytree.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SymmetricBinaryTreeTest {

    private SymmetricBinaryTree symmetricBinaryTree;

    @Before
    public void setUp() {
        symmetricBinaryTree = new SymmetricBinaryTree();
    }

    @Test
    public void shouldCheckIfBinaryTreeIsSymmetricCaseOne() {
        TreeNode nodeOne = new TreeNode(1);
        TreeNode nodeTwo = new TreeNode(2);
        TreeNode nodeThree = new TreeNode(2);
        TreeNode nodeFour = new TreeNode(3);
        TreeNode nodeFive = new TreeNode(4);
        TreeNode nodeSix = new TreeNode(4);
        TreeNode nodeSeven = new TreeNode(3);


        nodeOne.setLeft(nodeTwo);
        nodeOne.setRight(nodeThree);

        nodeTwo.setLeft(nodeFour);
        nodeTwo.setRight(nodeFive);

        nodeThree.setLeft(nodeSix);
        nodeThree.setRight(nodeSeven);

        Assert.assertEquals(1, symmetricBinaryTree.isSymmetric(nodeOne));
    }

    @Test
    public void shouldCheckIfBinaryTreeIsSymmetricCaseTwo() {
        TreeNode nodeOne = new TreeNode(1);
        TreeNode nodeTwo = new TreeNode(2);
        TreeNode nodeThree = new TreeNode(2);
        TreeNode nodeFour = new TreeNode(3);
        TreeNode nodeFive = new TreeNode(4);


        nodeOne.setLeft(nodeTwo);
        nodeOne.setRight(nodeThree);

        nodeTwo.setRight(nodeFour);
        nodeThree.setRight(nodeFive);

        Assert.assertEquals(0, symmetricBinaryTree.isSymmetric(nodeOne));
    }

    @Test
    public void shouldCheckIfBinaryTreeIsSymmetricCaseThree() {
        TreeNode nodeOne = new TreeNode(1);
        Assert.assertEquals(1, symmetricBinaryTree.isSymmetric(nodeOne));
    }

}
