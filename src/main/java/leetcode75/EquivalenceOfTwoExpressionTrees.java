package leetcode75;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 1612
 A binary expression tree is a kind of binary tree used to represent arithmetic expressions.
 Each node of a binary expression tree has either zero or two children. Leaf nodes (nodes with 0 children)
 correspond to operands (variables), and internal nodes (nodes with two children) correspond to the operators.
 In this problem, we only consider the '+' operator (i.e. addition).

 You are given the roots of two binary expression trees, root1 and root2.
 Return true if the two binary expression trees are equivalent. Otherwise, return false.

 Two binary expression trees are equivalent if they evaluate to the same value regardless of
 what the variables are set to

 Constraints:-

a.) The number of nodes in both trees are equal, odd and, in the range [1, 4999].
b.) Node.val is '+' or a lower-case English letter.
c.) It's guaranteed that the tree given is a valid binary expression tree

 Time Complexity : O(N)
 Space Complexity : O(N), where N being number of operators + number of operands
 * */

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
