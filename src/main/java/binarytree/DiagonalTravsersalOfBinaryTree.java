package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DiagonalTravsersalOfBinaryTree {

    public void diagonalPrint(TreeNode root) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        traverseAndUpdateMap(map, root, 1);
        printData(map);
    }

    private void printData(Map<Integer, ArrayList<Integer>> map) {
        for (int i = 1; i <= map.size(); i++) {
            for (Integer val : map.get(i)) {
                System.out.print(val);
                System.out.print(" ");
            }
        }
    }

    private void traverseAndUpdateMap(Map<Integer, ArrayList<Integer>> map, TreeNode node, int diagonalLevel) {
        if (!map.containsKey(diagonalLevel)) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(node.val);
            map.put(diagonalLevel, list);
        } else {
            map.get(diagonalLevel).add(node.val);
        }

        TreeNode leftNode = node.left;

        TreeNode rightNode = node.right;

        if (leftNode != null) {
            traverseAndUpdateMap(map, leftNode, diagonalLevel + 1);
        }

        if (rightNode != null) {
            traverseAndUpdateMap(map, rightNode, diagonalLevel);
        }
    }
}
