package leetcode75;

import java.util.HashMap;
import java.util.Map;

public class EquivalenceOfTwoExpressionTrees {


    public static void main(String ar[]) {
        EquivalenceOfTwoExpressionTrees unit = new EquivalenceOfTwoExpressionTrees();
        Node rootOne = new Node('+');
        Node nodeOne = new Node('a');
        Node nodeTwo = new Node('+');
        Node nodeThree = new Node('b');
        Node nodeFour = new Node('c');

        rootOne.left = nodeOne;
        rootOne.right = nodeTwo;
        nodeTwo.left = nodeThree;
        nodeTwo.right = nodeFour;


        Node rootTwo = new Node('+');
        Node nodeFive = new Node('+');
        Node nodeSix = new Node('a');
        Node nodeSeven = new Node('b');
        Node nodeEight = new Node('c');

        rootTwo.left = nodeFive;
        rootTwo.right = nodeSix;

        nodeFive.left = nodeSeven;
        nodeFive.right = nodeEight;

        unit.checkEquivalence(rootOne, rootTwo);

    }

    public boolean checkEquivalence(Node root1, Node root2) {
        String expressionOne = convertTreeToExpression(root1);
        String expressionTwo = convertTreeToExpression(root2);
        Map<Character, Integer> mapOne = prepareMap(expressionOne);
        Map<Character, Integer> mapTwo = prepareMap(expressionTwo);

        if (mapOne.size() != mapTwo.size()) {
            return false;
        }

        for (Character ch : mapOne.keySet()) {
            if (mapTwo.containsKey(ch)) {
                int updatedCount = mapTwo.get(ch) - mapOne.get(ch);
                if (updatedCount == 0) {
                    mapTwo.remove(ch);
                }
            }
        }

        return mapTwo.isEmpty();
    }

    private Map<Character, Integer> prepareMap(String expression) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : expression.toCharArray()) {
            if (ch != '+') {
                if (!map.containsKey(ch)) {
                    map.put(ch, 1);
                } else {
                    map.put(ch, map.get(ch) + 1);
                }
            }
        }
        return map;
    }

    private String  convertTreeToExpression(Node node) {
        return convert(node);
    }

    private String convert(Node node) {
        if (node.val != '+') {
            return String.valueOf(node.val);
        } else {
            Node left = node.left;
            Node right = node.right;
            return convert(left) + "+" + convert(right);
        }
    }


    static class Node {
       char val;
       Node left;
       Node right;
       Node(char val) { this.val = val; }
    }
}
