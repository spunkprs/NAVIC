package leetcode75;

import java.util.PriorityQueue;

public class TotalCostToHireKWorkers {

    public static void main(String ar[]) {
        TotalCostToHireKWorkers unit = new TotalCostToHireKWorkers();
        //int costs[] = {17,12,10,2,7,2,11,20,8};
        int costs[] = {31,25,72,79,74,65,84,91,18,59,27,9,81,33,17,58};
        int k = 11, candidates = 2;
        System.out.print("Cost to hire k workers is " + unit.totalCost(costs, k, candidates));
    }

    public long totalCost(int[] costs, int k, int candidates) {
        return processToComputeTotalCost(costs, k, candidates);
    }

    private long processToComputeTotalCost(int costs[], int k, int candidates) {
        int visitedIndexes[] = new int[costs.length];
        PriorityQueue<Node> heapOne = new PriorityQueue((objOne, objTwo) -> {
            Node obj1 = (Node)objOne;
            Node obj2 = (Node)objTwo;
            return obj1.cost < obj2.cost ? -1 : obj1.cost > obj2.cost ? 1 : 0;
        });

        PriorityQueue<Node> heapTwo = new PriorityQueue((objOne, objTwo) -> {
            Node obj1 = (Node)objOne;
            Node obj2 = (Node)objTwo;
            return obj1.cost < obj2.cost ? -1 : obj1.cost > obj2.cost ? 1 : 0;
        });

        int startIndexOne = 0;
        int endIndexOne = startIndexOne + candidates - 1;

        int startIndexTwo = costs.length - 1;
        int endIndexTwo = startIndexTwo - candidates + 1;

        int indexOne = 0;
        while (indexOne <= endIndexOne) {
            heapOne.add(new Node(costs[indexOne], indexOne));
            indexOne++;
        }

        int indexTwo = costs.length - 1;
        while (indexTwo >= endIndexTwo) {
            heapTwo.add(new Node(costs[indexTwo], indexTwo));
            indexTwo--;
        }

        int count = 1;
        long cost = 0;
        while (count <= k) {
            Node peekedNodeOne = heapOne.peek();
            Node peekedNodeTwo = heapTwo.peek();

            if (peekedNodeOne.cost < peekedNodeTwo.cost) {
                cost += peekedNodeOne.cost;
                visitedIndexes[peekedNodeOne.index] = 1;
                if (endIndexOne < costs.length) {
                    endIndexOne = heapMaintenanceOne(heapOne, endIndexOne, costs, visitedIndexes);
                }
            } else if (peekedNodeOne.cost > peekedNodeTwo.cost) {
                cost += peekedNodeTwo.cost;
                visitedIndexes[peekedNodeTwo.index] = 1;
                if (endIndexTwo >= 0) {
                    endIndexTwo = heapMaintenanceTwo(heapTwo, endIndexTwo, costs, visitedIndexes);
                }
            } else {
                int indexFirst = peekedNodeOne.index;
                int indexSecond = peekedNodeTwo.index;

                if (indexFirst < indexSecond) {
                    cost += peekedNodeOne.cost;
                    visitedIndexes[peekedNodeOne.index] = 1;
                    if (endIndexOne < costs.length) {
                        endIndexOne = heapMaintenanceOne(heapOne, endIndexOne, costs, visitedIndexes);
                    }
                } else if (indexFirst > indexSecond) {
                    cost += peekedNodeTwo.cost;
                    visitedIndexes[peekedNodeTwo.index] = 1;
                    if (endIndexTwo >= 0) {
                        endIndexTwo = heapMaintenanceTwo(heapTwo, endIndexTwo, costs, visitedIndexes);
                    }
                } else {
                    cost += peekedNodeOne.cost;
                    visitedIndexes[peekedNodeOne.index] = 1;
                    if (endIndexOne < costs.length) {
                        endIndexOne = heapMaintenanceOne(heapOne, endIndexOne, costs, visitedIndexes);
                    }
                    if (endIndexTwo >= 0) {
                        endIndexTwo = heapMaintenanceTwo(heapTwo, endIndexTwo, costs, visitedIndexes);
                    }
                }
            }
            count++;
        }
        return cost;
    }

    private int heapMaintenanceOne(PriorityQueue<Node> heapOne, int endIndexOne, int costs[], int visitedIndexes[]) {
        heapOne.poll();
        int index = endIndexOne + 1;
        while (index < costs.length) {
            if (visitedIndexes[index] == 0) {
                break;
            }
            index++;
        }
        if (index < costs.length) {
            heapOne.add(new Node(costs[index], index));
            return index;
        }
        return Integer.MAX_VALUE;
    }

    private int heapMaintenanceTwo(PriorityQueue<Node> heapTwo, int endIndexTwo, int costs[], int visitedIndexes[]) {
        heapTwo.poll();
        int index = endIndexTwo - 1;
        while (index >= 0) {
            if (visitedIndexes[index] == 0) {
                break;
            }
            index--;
        }
        if (index >= 0) {
            heapTwo.add(new Node(costs[index], index));
            return index;
        }
        return Integer.MIN_VALUE;
    }

    static class Node {
        private int cost;
        private int index;

        Node(int cost, int index) {
            this.cost = cost;
            this.index = index;
        }
    }
}
