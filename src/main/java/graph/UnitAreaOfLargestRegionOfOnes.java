package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class UnitAreaOfLargestRegionOfOnes {

    static int maxArea = 0;

    public static void main (String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int testCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= testCases; i++) {
                String input = br.readLine();
                String inputArr[] = input.split(" ");
                int rows = Integer.parseInt(inputArr[0]);
                int column = Integer.parseInt(inputArr[1]);
                input = br.readLine();
                Pair matrix[][] = prepareMatrix(input, rows, column);
                processToComputeUnitAreaOfLargestRegion(matrix);
                System.out.println(maxArea);
                maxArea = 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processToComputeUnitAreaOfLargestRegion(Pair[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (!matrix[i][j].isVisited && matrix[i][j].value == 1) {
                    Queue<Pair> queue = new LinkedList<Pair>();
                    queue.add(matrix[i][j]);
                    matrix[i][j].isVisited = true;
                    int area = 1;
                    while (!queue.isEmpty()) {
                        area = updateQueue(queue.peek(), matrix, queue, area);
                    }
                    updateMaxArea(area);
                }
            }
        }
    }

    private static void updateMaxArea(int area) {
        if (area > maxArea) {
            maxArea = area;
        }
    }

    private static int updateQueue(Pair parent, Pair[][] matrix, Queue<Pair> queue, int area) {

        int parentX = parent.x;
        int parentY = parent.y;

        if (parentX - 1 >= 0 && !matrix[parentX - 1][parentY].isVisited && matrix[parentX - 1][parentY].value == 1) {
            queue.add(matrix[parentX - 1][parentY]);
            matrix[parentX - 1][parentY].isVisited = true;
            area++;
        }

        if (parentX + 1 < matrix.length && !matrix[parentX + 1][parentY].isVisited && matrix[parentX + 1][parentY].value == 1) {
            queue.add(matrix[parentX + 1][parentY]);
            matrix[parentX + 1][parentY].isVisited = true;
            area++;
        }

        if (parentY - 1 >= 0 && !matrix[parentX][parentY - 1].isVisited && matrix[parentX][parentY - 1].value == 1) {
            queue.add(matrix[parentX][parentY - 1]);
            matrix[parentX][parentY - 1].isVisited = true;
            area++;
        }

        if (parentY + 1 < matrix[0].length && !matrix[parentX][parentY + 1].isVisited && matrix[parentX][parentY + 1].value == 1) {
            queue.add(matrix[parentX][parentY + 1]);
            matrix[parentX][parentY + 1].isVisited = true;
            area++;
        }

        if (parentX + 1 < matrix.length && parentY + 1 < matrix[0].length && !matrix[parentX + 1][parentY + 1].isVisited && matrix[parentX + 1][parentY + 1].value == 1) {
            queue.add(matrix[parentX + 1][parentY + 1]);
            matrix[parentX + 1][parentY + 1].isVisited = true;
            area++;
        }

        if (parentX + 1 < matrix.length && parentY - 1 >= 0 && !matrix[parentX + 1][parentY - 1].isVisited && matrix[parentX + 1][parentY - 1].value == 1) {
            queue.add(matrix[parentX + 1][parentY - 1]);
            matrix[parentX + 1][parentY - 1].isVisited = true;
            area++;
        }

        if (parentX - 1 >=0 && parentY + 1 < matrix[0].length && !matrix[parentX - 1][parentY + 1].isVisited && matrix[parentX - 1][parentY + 1].value == 1) {
            queue.add(matrix[parentX - 1][parentY + 1]);
            matrix[parentX - 1][parentY + 1].isVisited = true;
            area++;
        }

        if (parentX - 1 >=0 && parentY - 1 >= 0 && !matrix[parentX - 1][parentY - 1].isVisited && matrix[parentX - 1][parentY - 1].value == 1) {
            queue.add(matrix[parentX - 1][parentY - 1]);
            matrix[parentX - 1][parentY - 1].isVisited = true;
            area++;
        }

        //parent.isVisited = true;
        queue.poll();
        return area;
    }

    private static Pair[][] prepareMatrix(String input, int rows, int column) {
        Pair matrix[][] = new Pair[rows][column];
        String inputArr[] = input.split(" ");
        int k = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                matrix[i][j] = new Pair(i, j, Integer.parseInt(inputArr[k]));
                k++;
            }
        }
        return matrix;
    }

    static class Pair {
        int x, y, value;
        boolean isVisited;

        public Pair(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
}
