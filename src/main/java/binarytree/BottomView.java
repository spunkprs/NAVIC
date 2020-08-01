package binarytree;

import java.util.HashMap;
import java.util.Map;

public class BottomView {

    private int minimum = 0;
    private int maximum = 0;

    public void bottomView(Node root) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        populateMap(map, root, 0);
        printBottomView(map);
    }

    private void printBottomView(Map<Integer, Integer> map) {
        for (int i = minimum; i <= maximum; i++) {
            System.out.print(map.get(i));
            System.out.print(" ");
        }
    }

    private void populateMap(Map<Integer, Integer> map, Node node, int num) {
        if (num <= minimum) {
            minimum = num;
        }

        if (num > maximum) {
            maximum = num;
        }
        map.put(num, node.data);

        Node leftNode = node.left;
        Node rightNode = node.right;

        if (leftNode != null) {
            populateMap(map, leftNode, num - 1);
        }

        if (rightNode != null) {
            populateMap(map, rightNode, num + 1);
        }
    }
}
