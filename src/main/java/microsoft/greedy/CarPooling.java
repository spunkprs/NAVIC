package microsoft.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
Problem : 1094
Level : Medium
Link : https://leetcode.com/problems/car-pooling/description/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days

There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trips[i] = [numPassengersi, fromi, toi] indicates that
the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi
respectively. The locations are given as the number of kilometers due east from the car's initial location.

Passengers are dropped off before new passengers are picked up at the same location. At every point along the route,
the total number of passengers in the car must not exceed capacity.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.

Constraints:-

a.) 1 <= trips.length <= 1000
b.) trips[i].length == 3
c.) 1 <= numPassengersi <= 100
d.) 0 <= fromi < toi <= 1000
e.) 1 <= capacity <= 10^5

Time Complexity = O(N * log(N))
Space Complexity = O(N)
Where N = trips.length * 2
 * */

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
