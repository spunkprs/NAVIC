package leetcode75.dp;

import java.util.*;

/**
Problem : 309
You are given an array prices where prices[i] is the price of a given stock on the ith day.

Find the maximum profit you can achieve. You may complete as many transactions as you
like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again)

Constraints:-

a.) 1 <= prices.length <= 5000
b.) 0 <= prices[i] <= 1000
 * */

public class BestTimeToBuyAndSellStockWithCoolDown {

    public int maxProfit(int[] prices) {
        return processToComputeMaxProfit(prices);
    }

    private int processToComputeMaxProfit(int[] prices) {
        Map<Node, Integer> map = new HashMap<>();
        Node parentNode = new Node(-1, 'f');

        return process(parentNode, map, prices);
    }

    private int process(Node parentNode, Map<Node, Integer> map, int[] prices) {

        if (parentNode.index == prices.length - 1) {
            populateMap(parentNode, map, prices, 0);
            return map.get(parentNode);
        } else {
            List<Node> childNodes = findPotentialChildNodes(parentNode, prices, map);
            int sum = Integer.MIN_VALUE;
            for (Node childNode : childNodes) {
                if (map.containsKey(childNode)) {
                    sum = updateSum(sum, map.get(childNode));
                } else {
                    int returnedSum = process(childNode, map, prices);
                    sum = updateSum(sum, returnedSum);
                }
            }

            if (parentNode.index >= 0) {
                populateMap(parentNode, map, prices, sum);
            } else {
                map.put(parentNode, sum);
            }
        }

        return map.get(parentNode);
    }

    private void populateMap(Node node, Map<Node, Integer> map, int prices[], int sumCollected) {
        if (node.state == 'b') {
            map.put(node, (prices[node.index] * -1) + sumCollected);
        } else if (node.state == 's') {
            map.put(node, prices[node.index] + sumCollected);
        } else if (node.state == 'h') {
            map.put(node, sumCollected);
        } else if (node.state == 'f') {
            map.put(node, sumCollected);
        }
    }

    private int updateSum(int sum, Integer computedSum) {
        return computedSum > sum ? computedSum : sum;
    }

    private List<Node> findPotentialChildNodes(Node parentNode, int[] prices, Map<Node, Integer> map) {
        List<Node> childNodes = new ArrayList<>();

        if (parentNode.index < prices.length - 1) {
            if (parentNode.state == 'b') {
                Node childNodeOne = new Node(parentNode.index + 1, 's');
                Node childNodeTwo = new Node(parentNode.index + 1, 'h');
                childNodes.add(childNodeOne);
                childNodes.add(childNodeTwo);
            } else if (parentNode.state == 's') {
                Node childNodeOne = new Node(parentNode.index + 2, 'b');
                Node childNodeTwo = new Node(parentNode.index + 1, 'f');
                if (childNodeOne.index < prices.length) {
                    childNodes.add(childNodeOne);
                }
                    childNodes.add(childNodeTwo);

            } else if (parentNode.state == 'h') {
                Node childNodeOne = new Node(parentNode.index + 1, 'h');
                Node childNodeTwo = new Node(parentNode.index + 1, 's');

                childNodes.add(childNodeOne);
                childNodes.add(childNodeTwo);

            } else if (parentNode.state == 'f') {
                Node childNodeOne = new Node(parentNode.index + 1, 'f');
                Node childNodeTwo = new Node(parentNode.index + 1, 'b');

                childNodes.add(childNodeOne);
                childNodes.add(childNodeTwo);
            }
        }

        return childNodes;
    }


    static class Node {
        private int index;
        private char state;

        public Node(int index, char state) {
            this.index = index;
            this.state = state;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index && state == node.state;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, state);
        }
    }
}
