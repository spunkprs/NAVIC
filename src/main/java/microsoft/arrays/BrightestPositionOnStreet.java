package microsoft.arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
Problem : 2021
Level : Medium
Link : https://leetcode.com/problems/brightest-position-on-street/description/

A perfectly straight street is represented by a number line. The street has street lamp(s) on it
and is represented by a 2D integer array lights. Each lights[i] = [positioni, rangei] indicates
that there is a street lamp at position positioni that lights up the area
from [positioni - rangei, positioni + rangei] (inclusive).

The brightness of a position p is defined as the number of street lamp that light up the position p.

Given lights, return the brightest position on the street. If there are multiple brightest positions,
return the smallest one.

Constraints:-

a.) 1 <= lights.length <= 10^5
b.) lights[i].length == 2
c.) -10^8 <= positioni <= 10^8
d.) 0 <= rangei <= 10^8

 * */

public class BrightestPositionOnStreet {

    public static void main(String ar[]) {
        BrightestPositionOnStreet unit = new BrightestPositionOnStreet();
        //int lights[][] = {{-3, 2}, {1, 2}, {3, 3}};
        //int lights[][] = {{1, 0}, {0, 1}};
        int lights[][] = {{1, 2}};

        System.out.print("Brightest position on the street is " + unit.brightestPosition(lights));
    }

    public int brightestPosition(int[][] lights) {

        List<Node> nodeList = prepareNodes(lights);

        int highestCount = 0;
        int count = 0;
        int minIndex = 0;

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getType().equals("S")) {
                count++;
                if (count > highestCount) {
                    highestCount = count;
                    minIndex = nodeList.get(i).getIndex();
                }
            } else {
                count--;
            }
        }
        return minIndex;
    }

    private List<Node> prepareNodes(int[][] lights) {
        List<Node> nodeList = new ArrayList<>();

        for (int i = 0; i < lights.length; i++) {
            int position = lights[i][0];
            int range = lights[i][1];
            int startInterval = position - range;
            int endInterval = position + range;
            nodeList.add(new Node(startInterval, "S"));
            nodeList.add(new Node(endInterval, "E"));
        }

        nodeList.sort(Comparator.comparing(Node::getIndex, Comparator.naturalOrder())
                .thenComparing(Comparator.comparing(Node::getType, Comparator.reverseOrder())));

        return nodeList;
    }

    static class Node {
        private int index;
        private String type;

        public Node(int index, String type) {
            this.index = index;
            this.type = type;
        }

        public int getIndex() {
            return index;
        }

        public String getType() {
            return type;
        }
    }


}
