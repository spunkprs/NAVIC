package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestSourceToDestinationPath {

    public static void main(String ar[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numberOfTestCases; i++) {
                String input = br.readLine();
                String inputAr[] = input.split(" ");
                int numberOfRows = Integer.parseInt(inputAr[0]);
                int numberOfColumn = Integer.parseInt(inputAr[1]);
                input = br.readLine();
                Pair matrix[][] = new Pair[numberOfRows][numberOfColumn];
                prepareInputMatrix(matrix, input);
                input = br.readLine();
                inputAr = input.split(" ");
                int destinationX = Integer.parseInt(inputAr[0]);
                int destinationY = Integer.parseInt(inputAr[1]);
                PriorityQueue<Pair> priorityQueue = new PriorityQueue<Pair>(new CustomComparator());
                processToComputeShortestPathFromSourceToDestination(matrix, destinationX, destinationY, priorityQueue);
                if (!priorityQueue.isEmpty()) {
                    System.out.println(priorityQueue.peek().getSumOfPath());
                } else {
                    System.out.println(-1);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processToComputeShortestPathFromSourceToDestination(Pair matrix[][], int destinationX, int destinationY, PriorityQueue<Pair> priorityQueue) {
        Pair origin = matrix[0][0];
        if (origin.value == 1) {
            priorityQueue.add(origin);
        } else {
            return;
        }

        while (!priorityQueue.isEmpty()) {
            Pair source = priorityQueue.peek();
            if (source.x == destinationX && source.y == destinationY) {
                return;
            }
            updatePriorityQueue(priorityQueue, matrix);
        }
    }

    private static void updatePriorityQueue(PriorityQueue<Pair> priorityQueue, Pair[][] matrix) {
        Pair source = priorityQueue.peek();
        if (source.x - 1 >= 0 && matrix[source.x - 1][source.y].value == 1 && !matrix[source.x - 1][source.y].isExplored) {
            matrix[source.x - 1][source.y].sumOfPath = source.sumOfPath + 1;
            priorityQueue.add(matrix[source.x - 1][source.y]);
        }

        if (source.x + 1 < matrix.length && matrix[source.x + 1][source.y].value == 1 && !matrix[source.x + 1][source.y].isExplored) {
            matrix[source.x + 1][source.y].sumOfPath = source.sumOfPath + 1;
            priorityQueue.add(matrix[source.x + 1][source.y]);
        }

        if (source.y - 1 >= 0 && matrix[source.x][source.y - 1].value == 1 && !matrix[source.x][source.y - 1].isExplored) {
            matrix[source.x][source.y - 1].sumOfPath = source.sumOfPath + 1;
            priorityQueue.add(matrix[source.x][source.y - 1]);
        }

        if (source.y + 1 < matrix[0].length && matrix[source.x][source.y + 1].value == 1 && !matrix[source.x][source.y + 1].isExplored) {
            matrix[source.x][source.y + 1].sumOfPath = source.sumOfPath + 1;
            priorityQueue.add(matrix[source.x][source.y + 1]);
        }
        source.isExplored = true;
        priorityQueue.poll();
    }

    private static void prepareInputMatrix(Pair matrix[][], String input) {
        String inputArr[] = input.split(" ");
        int k = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = new Pair(i, j, Integer.parseInt(inputArr[k]));
                k++;
            }
        }
    }

    static class Pair {
        int x;
        int y;
        int sumOfPath;
        boolean isExplored;
        int value;

        public Pair(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        public int getSumOfPath() {
            return sumOfPath;
        }

        public void setSumOfPath(int sumOfPath) {
            this.sumOfPath = sumOfPath;
        }

        public boolean isExplored() {
            return isExplored;
        }

        public void setExplored(boolean explored) {
            isExplored = explored;
        }
    }

    static class CustomComparator implements Comparator<ShortestSourceToDestinationPath.Pair> {

        public int compare(ShortestSourceToDestinationPath.Pair o1, ShortestSourceToDestinationPath.Pair o2) {
            if (o1.sumOfPath == o2.sumOfPath) {
                return 0;
            } else if (o1.sumOfPath > o2.sumOfPath) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}
