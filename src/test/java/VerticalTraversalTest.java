import binarytree.TreeNode;
import binarytree.VerticalTraversal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VerticalTraversalTest {

    private VerticalTraversal unit;

    @Before
    public void setUp() {
        unit = new VerticalTraversal();
    }

    @Test
    public void shouldReturnVerticalTraversalOfBinaryTreeCaseOne() {
        TreeNode root = new TreeNode(3);
        TreeNode childOne = new TreeNode(9);
        TreeNode childTwo = new TreeNode(20);
        TreeNode childThree = new TreeNode(15);
        TreeNode childFour = new TreeNode(7);

        root.setLeft(childOne);
        root.setRight(childTwo);
        childTwo.setLeft(childThree);
        childTwo.setRight(childFour);

        List<Integer> listOne = new ArrayList<Integer>();
        List<Integer> listTwo = new ArrayList<Integer>();
        List<Integer> listThree = new ArrayList<Integer>();
        List<Integer> listFour = new ArrayList<Integer>();

        List<List<Integer>> expectedOutput = new ArrayList<List<Integer>>();

        listOne.add(9);
        listTwo.add(3);
        listTwo.add(15);
        listThree.add(20);
        listFour.add(7);

        expectedOutput.add(listOne);
        expectedOutput.add(listTwo);
        expectedOutput.add(listThree);
        expectedOutput.add(listFour);

        List<List<Integer>> actualOutput = unit.verticalTraversal(root);
        verify(expectedOutput, actualOutput);
    }

    @Test
    public void shouldReturnVerticalTraversalOfBinaryTreeCaseTwo() {
        TreeNode root = new TreeNode(0);
        TreeNode childOne = new TreeNode(8);
        TreeNode childTwo = new TreeNode(1);
        TreeNode childThree = new TreeNode(3);
        TreeNode childFour = new TreeNode(2);

        TreeNode childFive = new TreeNode(4);
        TreeNode childSix = new TreeNode(5);
        TreeNode childSeven = new TreeNode(7);
        TreeNode childEight = new TreeNode(6);

        root.setLeft(childOne);
        root.setRight(childTwo);
        childTwo.setLeft(childThree);
        childTwo.setRight(childFour);
        childThree.setRight(childFive);
        childFour.setLeft(childSix);
        childFive.setRight(childSeven);
        childSix.setLeft(childEight);

        List<Integer> listOne = new ArrayList<Integer>();
        List<Integer> listTwo = new ArrayList<Integer>();
        List<Integer> listThree = new ArrayList<Integer>();
        List<Integer> listFour = new ArrayList<Integer>();

        List<List<Integer>> expectedOutput = new ArrayList<List<Integer>>();

        listOne.add(8);
        listTwo.add(0);
        listTwo.add(3);
        listTwo.add(6);
        listThree.add(1);
        listThree.add(4);
        listThree.add(5);
        listFour.add(2);
        listFour.add(7);

        expectedOutput.add(listOne);
        expectedOutput.add(listTwo);
        expectedOutput.add(listThree);
        expectedOutput.add(listFour);

        List<List<Integer>> actualOutput = unit.verticalTraversal(root);
        verify(expectedOutput, actualOutput);
    }

    private void verify(List<List<Integer>> expectedOutput, List<List<Integer>> actualOutput) {
        Assert.assertEquals(expectedOutput.size(), actualOutput.size());
        for (int i = 0; i < expectedOutput.size(); i++) {
            Assert.assertEquals(expectedOutput.get(i).size(), actualOutput.get(i).size());
            for (int j = 0; j < expectedOutput.get(i).size(); j++) {
                Assert.assertEquals(expectedOutput.get(i).get(j), actualOutput.get(i).get(j));
            }
        }
    }
}
