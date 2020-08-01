package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumCostPath {


    private static boolean shouldContinue = true;
    public static void main (String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numberOfTestCases; i++) {
                int numberOfVertex = Integer.parseInt(br.readLine());
                String input = br.readLine();
                prepareGraphAndProceed(numberOfVertex, input);
                shouldContinue = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void prepareGraphAndProceed(int numberOfVertex, String input) {
        int p = 0;
        String inputArr[] = input.split(" ");
        Pair adjacencyMatrix[][] = new Pair[numberOfVertex][numberOfVertex];
        for (int i = 0; i < numberOfVertex; i++) {
            for (int j = 0; j < numberOfVertex; j++) {
                adjacencyMatrix[i][j] = new Pair(i, j, Integer.parseInt(inputArr[p]));
                p++;
            }
        }

        PriorityQueue<Pair> pairPriorityQueue = new PriorityQueue<Pair>(new CustomComparator());
        Set<Pair> hashSet = new HashSet<Pair>();

        pairPriorityQueue.add(adjacencyMatrix[0][0]);
        processToComputeMinimumCostPath(adjacencyMatrix, numberOfVertex, pairPriorityQueue, hashSet);
        System.out.println(adjacencyMatrix[numberOfVertex - 1][numberOfVertex - 1].sumValue);
    }

    private static void processToComputeMinimumCostPath(Pair[][] adjacencyMatrix, int range, PriorityQueue<Pair> pairPriorityQueue, Set<Pair> hashSet) {
        while (!pairPriorityQueue.isEmpty() && shouldContinue) {
            Pair coordinate = pairPriorityQueue.remove();
            coordinate.setVisited(true);
            if (coordinate.x == range - 1 && coordinate.y == range - 1) {
                shouldContinue = false;
                return;
            } else {
                findChildren(coordinate, adjacencyMatrix.length, adjacencyMatrix, pairPriorityQueue, hashSet);
            }
        }
    }

    private static void findChildren(Pair parent, int range, Pair[][] adjacencyMatrix, PriorityQueue<Pair> pairPriorityQueue, Set<Pair> hashSet) {
        int parentX = parent.x;
        int parentY = parent.y;

            if (parentX - 1 >= 0 && !adjacencyMatrix[parentX - 1][parentY].isVisited()) {
                updateSum(adjacencyMatrix, parentX - 1, parentY, parent);
                addToPriorityQueue(pairPriorityQueue, adjacencyMatrix[parentX - 1][parentY], hashSet);
            }

            if (parentX + 1 < range && !adjacencyMatrix[parentX + 1][parentY].isVisited()) {
                updateSum(adjacencyMatrix, parentX + 1, parentY, parent);
                addToPriorityQueue(pairPriorityQueue, adjacencyMatrix[parentX + 1][parentY], hashSet);
            }

            if (parentY - 1 >= 0 && !adjacencyMatrix[parentX][parentY - 1].isVisited()) {
                updateSum(adjacencyMatrix, parentX, parentY - 1, parent);
                addToPriorityQueue(pairPriorityQueue, adjacencyMatrix[parentX][parentY - 1], hashSet);
            }

            if (parentY + 1 < range && !adjacencyMatrix[parentX][parentY + 1].isVisited()) {
                updateSum(adjacencyMatrix, parentX , parentY + 1, parent);
                addToPriorityQueue(pairPriorityQueue, adjacencyMatrix[parentX][parentY + 1], hashSet);
            }
    }

    private static void addToPriorityQueue(PriorityQueue<Pair> pairPriorityQueue, Pair element, Set<Pair> hashSet) {
            if (!hashSet.contains(element)) {
                pairPriorityQueue.add(element);
            }
    }

    private static void updateSum(Pair[][] adjacencyMatrix, int parentX, int parentY, Pair parent) {
        if (adjacencyMatrix[parentX][parentY].sumValue == adjacencyMatrix[parentX][parentY].value) {
            adjacencyMatrix[parentX][parentY].sumValue = parent.sumValue + adjacencyMatrix[parentX][parentY].value;
        } else {
            if (adjacencyMatrix[parentX][parentY].sumValue > parent.sumValue + adjacencyMatrix[parentX][parentY].value) {
                adjacencyMatrix[parentX][parentY].sumValue = parent.sumValue + adjacencyMatrix[parentX][parentY].value;
            }
        }

    }

    static class Pair {
        int x;
        int y;
        int value;
        int sumValue;
        boolean visited;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x &&
                    y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        public Pair(int x, int y, int value) {
           this.x = x;
           this.y = y;
           this.value = value;
           this.sumValue = value;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }
    }

    static class CustomComparator implements Comparator<Pair> {

        public int compare(Pair o1, Pair o2) {
            if (o1.sumValue == o2.sumValue) {
                return 0;
            } else if (o1.sumValue > o2.sumValue) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
