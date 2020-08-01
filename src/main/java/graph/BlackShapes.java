package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BlackShapes {

    public int black(ArrayList<String> A) {
        Pair[][] matrix = prepareMatrix(A);
        return processToComputeNumberOfBlackShapes(matrix);
    }

    private int processToComputeNumberOfBlackShapes(Pair[][] matrix) {
        int numberOfBlackShapes = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Character ch = new Character('X');
                if (!matrix[i][j].isVisited && matrix[i][j].element.equals(ch)) {
                    numberOfBlackShapes++;
                    Queue<Pair> queue = new LinkedList<Pair>();
                    queue.add(matrix[i][j]);
                    while (!queue.isEmpty()) {
                        updateStack(queue, matrix, queue.peek());
                    }
                }
            }
        }
        return numberOfBlackShapes;
    }

    private void updateStack(Queue<Pair> queue, Pair[][] matrix, Pair parent) {

        Character ch = new Character('X');

        if (parent.x - 1 >= 0 && !matrix[parent.x- 1][parent.y].isVisited && matrix[parent.x - 1][parent.y].element.equals(ch)) {
            queue.add(matrix[parent.x-1][parent.y]);
        }

        if (parent.x + 1 < matrix.length && !matrix[parent.x + 1][parent.y].isVisited && matrix[parent.x + 1][parent.y].element.equals(ch)) {
            queue.add(matrix[parent.x + 1][parent.y]);
        }

        if (parent.y - 1 >= 0 && !matrix[parent.x][parent.y - 1].isVisited && matrix[parent.x][parent.y - 1].element.equals(ch)) {
            queue.add(matrix[parent.x][parent.y - 1]);
        }

        if (parent.y + 1 < matrix[0].length && !matrix[parent.x][parent.y + 1].isVisited && matrix[parent.x][parent.y + 1].element.equals(ch)) {
            queue.add(matrix[parent.x][parent.y + 1]);
        }

        parent.isVisited = true;
        queue.poll();
    }

    private Pair[][] prepareMatrix(ArrayList<String> a) {
        Pair matrix[][] = null;
        for (int i = 0; i < a.size(); i++) {
            char[] characters = a.get(i).toCharArray();
            for (int j = 0; j < characters.length; j++) {
                if (i == 0 && j == 0) {
                    matrix = new Pair[a.size()][characters.length];
                }
                matrix[i][j] = new Pair(i, j, characters[j]);
            }
        }
        return matrix;
    }

    static class Pair {
        int x, y;
        boolean isVisited;
        Character element;

        public Pair(int x, int y, Character element) {
            this.x = x;
            this.y = y;
            this.element = element;
        }
    }
}
