package leetcode75;

import java.util.PriorityQueue;

/**
You are given a 0-indexed integer array costs where costs[i] is the cost of hiring the ith worker.

You are also given two integers k and candidates. We want to hire exactly k workers according to the following rules:

You will run k sessions and hire exactly one worker in each session.
In each hiring session, choose the worker with the lowest cost from either the first candidates workers or the last candidates workers. Break the tie by the smallest index.
For example, if costs = [3,2,7,7,1,2] and candidates = 2, then in the first hiring session, we will choose the 4th worker because they have the lowest cost [3,2,7,7,1,2].
In the second hiring session, we will choose 1st worker because they have the same lowest cost as 4th worker but they have the smallest index [3,2,7,7,2]. Please note that the indexing may be changed in the process.
If there are fewer than candidates workers remaining, choose the worker with the lowest cost among them. Break the tie by the smallest index.
A worker can only be chosen once.
Return the total cost to hire exactly k workers


Constraints:-

a.) 1 <= costs.length <= 10^5
b.) 1 <= costs[i] <= 10^5
c.) 1 <= k, candidates <= costs.length
 * */

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
