import binarytree.LevelOrder;
import binarytree.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class LevelOrderTest {

    public LevelOrder unit;

    @Before
    public void setUp() {
        unit = new LevelOrder();
    }

    @Test
    public void shouldDoLevelOrderTraversalCaseOne() {
        TreeNode root = new TreeNode(3);
        TreeNode nodeOne = new TreeNode(9);
        TreeNode nodeTwo = new TreeNode(20);
        TreeNode nodeThree = new TreeNode(15);
        TreeNode nodeFour = new TreeNode(7);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeTwo.setLeft(nodeThree);
        nodeTwo.setRight(nodeFour);

        ArrayList<ArrayList<Integer>> result = unit.levelOrder(root);
        Assert.assertNotNull(result);
    }

    @Test
    public void shouldDoLevelOrderTraversalCaseTwo() {
        TreeNode root = new TreeNode(3);
        TreeNode nodeOne = new TreeNode(9);
        TreeNode nodeTwo = new TreeNode(20);
        TreeNode nodeThree = new TreeNode(16);
        TreeNode nodeFour = new TreeNode(18);
        TreeNode nodeFive = new TreeNode(15);
        TreeNode nodeSix = new TreeNode(7);

        root.setLeft(nodeOne);
        root.setRight(nodeTwo);

        nodeOne.setLeft(nodeThree);
        nodeOne.setRight(nodeFour);
        nodeTwo.setLeft(nodeFive);
        nodeTwo.setRight(nodeSix);

        ArrayList<ArrayList<Integer>> result = unit.levelOrder(root);
        Assert.assertNotNull(result);
    }
}
