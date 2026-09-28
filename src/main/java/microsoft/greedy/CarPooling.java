package microsoft.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CarPooling {

    public static void main(String ar[]) {
        CarPooling unit = new CarPooling();
        int trips[][] = {{2, 1, 5}, {3, 3, 7}};
        int capacity = 4;

        System.out.print("Will the car pooling be able to happen " + unit.carPooling(trips, capacity));
    }

    public boolean carPooling(int[][] trips, int capacity) {
        return processToComputeIsCarPoolingPossible(trips, capacity);
    }

    private boolean processToComputeIsCarPoolingPossible(int[][] trips, int capacity) {
        List<Node> resultNodes = prepareNodes(trips);

        boolean result = true;
        int index = 0;
        int currentCapacity = capacity;

        while (index < resultNodes.size()) {
            Node nodeAtIndex = resultNodes.get(index);
            if (nodeAtIndex.getType().equals("S")) {
                currentCapacity -= nodeAtIndex.getCapacity();
                if (currentCapacity < 0) {
                    result = false;
                    break;
                }
            } else {
                currentCapacity += nodeAtIndex.getCapacity();
            }
            index++;
        }
        return result;
    }

    private List<Node> prepareNodes(int[][] trips) {
        List<Node> resultNodes = new ArrayList<>();
        for (int i = 0; i < trips.length; i++) {
            int capacity = trips[i][0];
            int fromTime = trips[i][1];
            int toTime = trips[i][2];

            Node nodeOne = new Node(fromTime, "S", capacity);
            Node nodeTwo = new Node(toTime, "E", capacity);
            resultNodes.add(nodeOne);
            resultNodes.add(nodeTwo);
        }

        resultNodes.sort(Comparator.comparing(Node::getTime).thenComparing(Node::getType, Comparator.naturalOrder()));
        return resultNodes;
    }

    static class Node {
        private int time;
        private String type;
        private int capacity;

        public Node(int time, String type, int capacity) {
            this.time = time;
            this.type = type;
            this.capacity = capacity;
        }

        public int getTime() {
            return time;
        }

        public String getType() {
            return type;
        }

        public int getCapacity() {
            return capacity;
        }
    }
}
