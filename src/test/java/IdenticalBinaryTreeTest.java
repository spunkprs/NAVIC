import binarytree.IdenticalBinaryTree;
import binarytree.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IdenticalBinaryTreeTest {

    private IdenticalBinaryTree identicalBinaryTree;

    @Before
    public void setUp() {
        identicalBinaryTree = new IdenticalBinaryTree();
    }

    @Test
    public void shouldCheckWhetherGivenTwoBinaryTreeAreBinaryCaseOne() {
        TreeNode treeNodeOneNodeOne = new TreeNode(1);
        TreeNode treeNodeOneNodeTwo = new TreeNode(2);
        TreeNode treeNodeOneNodeThree = new TreeNode(3);
        TreeNode treeNodeOneNodeFour = new TreeNode(4);
        TreeNode treeNodeOneNodeFive = new TreeNode(5);

        treeNodeOneNodeOne.setLeft(treeNodeOneNodeTwo);
        treeNodeOneNodeOne.setRight(treeNodeOneNodeThree);
        treeNodeOneNodeTwo.setLeft(treeNodeOneNodeFour);
        treeNodeOneNodeTwo.setLeft(treeNodeOneNodeFive);

        TreeNode treeNodeTwoNodeOne = new TreeNode(1);
        TreeNode treeNodeTwoNodeTwo = new TreeNode(2);
        TreeNode treeNodeTwoNodeThree = new TreeNode(3);
        TreeNode treeNodeTwoNodeFour = new TreeNode(4);
        TreeNode treeNodeTwoNodeFive = new TreeNode(5);

        treeNodeTwoNodeOne.setLeft(treeNodeTwoNodeTwo);
        treeNodeTwoNodeOne.setRight(treeNodeTwoNodeThree);
        treeNodeTwoNodeTwo.setLeft(treeNodeTwoNodeFour);
        treeNodeTwoNodeTwo.setLeft(treeNodeTwoNodeFive);

        Assert.assertEquals(1, identicalBinaryTree.isSameTree(treeNodeOneNodeOne, treeNodeTwoNodeOne));
    }

    @Test
    public void shouldCheckWhetherGivenTwoBinaryTreeAreBinaryCaseTwo() {
        TreeNode treeNodeOneNodeOne = new TreeNode(1);
        TreeNode treeNodeOneNodeTwo = new TreeNode(2);
        TreeNode treeNodeOneNodeThree = new TreeNode(3);


        treeNodeOneNodeOne.setLeft(treeNodeOneNodeTwo);
        treeNodeOneNodeOne.setRight(treeNodeOneNodeThree);

        TreeNode treeNodeTwoNodeOne = new TreeNode(1);
        TreeNode treeNodeTwoNodeTwo = new TreeNode(2);
        TreeNode treeNodeTwoNodeThree = new TreeNode(3);
        TreeNode treeNodeTwoNodeFour = new TreeNode(4);
        TreeNode treeNodeTwoNodeFive = new TreeNode(5);

        treeNodeTwoNodeOne.setLeft(treeNodeTwoNodeTwo);
        treeNodeTwoNodeOne.setRight(treeNodeTwoNodeThree);
        treeNodeTwoNodeTwo.setLeft(treeNodeTwoNodeFour);
        treeNodeTwoNodeTwo.setLeft(treeNodeTwoNodeFive);

        Assert.assertEquals(0, identicalBinaryTree.isSameTree(treeNodeOneNodeOne, treeNodeTwoNodeOne));
    }

    @Test
    public void shouldCheckWhetherGivenTwoBinaryTreeAreBinaryCaseThree() {
        TreeNode treeNodeOneNodeOne = new TreeNode(1);
        TreeNode treeNodeOneNodeTwo = new TreeNode(2);
        TreeNode treeNodeOneNodeThree = new TreeNode(3);


        treeNodeOneNodeOne.setLeft(treeNodeOneNodeTwo);
        treeNodeOneNodeOne.setRight(treeNodeOneNodeThree);

        TreeNode treeNodeTwoNodeOne = new TreeNode(1);
        TreeNode treeNodeTwoNodeTwo = new TreeNode(3);
        TreeNode treeNodeTwoNodeThree = new TreeNode(2);


        treeNodeTwoNodeOne.setLeft(treeNodeTwoNodeTwo);
        treeNodeTwoNodeOne.setRight(treeNodeTwoNodeThree);

        Assert.assertEquals(0, identicalBinaryTree.isSameTree(treeNodeOneNodeOne, treeNodeTwoNodeOne));
    }
}
