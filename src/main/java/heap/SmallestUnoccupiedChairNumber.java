package heap;

import java.util.*;

public class SmallestUnoccupiedChairNumber {

    public static void main(String ar[]) {
        SmallestUnoccupiedChairNumber unit = new SmallestUnoccupiedChairNumber();

        int times[][] = {{5,15},{6,16},{7,17},{8,18},{9,19}};
        int targetFriend = 4;

        System.out.println("Smallest chair index on which target friend " + " " + targetFriend +
                " will sit is :: " + unit.smallestChair(times, targetFriend));
    }

    public int smallestChair(int[][] times, int targetFriend) {

        // Replace this placeholder return statement with your code

        int smallestChairAlloted = -1;
        int maxAvailableChair = -1;

        PriorityQueue<Integer> availableChairs = new PriorityQueue<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(new DepartureTimeComparator());

        List<ArrivalTimeWithIndex> arrivalTimeWithIndexList = new ArrayList<>();

        populateArrivalTimes(arrivalTimeWithIndexList, times);

        for (ArrivalTimeWithIndex arrivalTimeWithIndex : arrivalTimeWithIndexList) {
            int arrivalTime = arrivalTimeWithIndex.arrivalTime;
            int friendIndex = arrivalTimeWithIndex.index;

            if (priorityQueue.isEmpty()) {
                Node node = new Node(arrivalTimeWithIndex.departureTime, 0);
                priorityQueue.add(node);
                maxAvailableChair = 0;
                if (friendIndex == targetFriend) {
                    smallestChairAlloted = 0;
                    break;
                }
            } else {
                if (arrivalTime < priorityQueue.peek().departureTime) {
                    Node node = null;
                    if (availableChairs.isEmpty()) {
                        maxAvailableChair += 1;
                        node = new Node(arrivalTimeWithIndex.departureTime, maxAvailableChair);
                    } else {
                        int availableChair = availableChairs.poll();
                        node = new Node(arrivalTimeWithIndex.departureTime, availableChair);
                    }
                    priorityQueue.add(node);
                    if (friendIndex == targetFriend) {
                        smallestChairAlloted = node.allotedChairIndex;
                        break;
                    }
                } else {

                    while (!priorityQueue.isEmpty() && arrivalTime >= priorityQueue.peek().departureTime) {
                        Node polledNode = priorityQueue.poll();
                        availableChairs.add(polledNode.allotedChairIndex);
                    }

                    Node node = new Node(arrivalTimeWithIndex.departureTime, availableChairs.poll());
                    priorityQueue.add(node);
                    if (friendIndex == targetFriend) {
                        smallestChairAlloted = node.allotedChairIndex;
                        break;
                    }
                }
            }
        }

        return smallestChairAlloted;
    }

    private void populateArrivalTimes(List<ArrivalTimeWithIndex> arrivalTimeWithIndexList, int[][] times) {
        for (int i = 0; i < times.length; i++) {
            arrivalTimeWithIndexList.add(new ArrivalTimeWithIndex(times[i][0], i, times[i][1]));
        }
        Collections.sort(arrivalTimeWithIndexList);
    }

    static class ArrivalTimeWithIndex implements Comparable<ArrivalTimeWithIndex> {
        private int arrivalTime;
        private int index;
        private int departureTime;

        public ArrivalTimeWithIndex(int arrivalTime, int index, int departureTime) {
            this.arrivalTime = arrivalTime;
            this.index = index;
            this.departureTime = departureTime;
        }

        public int compareTo(ArrivalTimeWithIndex obj) {
            return this.arrivalTime - obj.arrivalTime;
        }
    }

    static class Node {
        private int departureTime;

        private int allotedChairIndex;

        public Node(int departureTime, int allotedChairIndex) {
            this.departureTime = departureTime;
            this.allotedChairIndex = allotedChairIndex;
        }
    }

    static class DepartureTimeComparator implements Comparator<Node> {

        @Override
        public int compare(Node obj1, Node obj2) {
            if (obj1.departureTime == obj2.departureTime) {
                return obj2.allotedChairIndex - obj1.allotedChairIndex;
            }
            return obj1.departureTime - obj2.departureTime;
        }
    }
}
