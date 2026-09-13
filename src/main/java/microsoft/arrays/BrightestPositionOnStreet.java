package microsoft.arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
