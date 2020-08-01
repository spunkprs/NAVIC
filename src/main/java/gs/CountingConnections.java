package gs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountingConnections {

    Set<Pair> set = new HashSet<Pair>();

    static int countConnections(List<List<Integer>> matrix) {
        List<List<Pair>> matrixPair = prepareMatrixPair(matrix);
        parseMatrix(matrixPair);
        return 0;
    }

    private static void parseMatrix(List<List<Pair>> matrixPair) {
        for (int i = 0; i < matrixPair.size(); i++) {
            for (int j = 0; j < matrixPair.get(i).size(); j++) {
                if (!matrixPair.get(i).get(j).visited) {
                    traverse(matrixPair, i, j);
                }
            }
        }
    }

    private static void traverse(List<List<Pair>> matrixPair, int x, int y) {

    }



    private static List<List<Pair>> prepareMatrixPair(List<List<Integer>> matrix) {
        List<List<Pair>> matrixPair = new ArrayList<List<Pair>>();
        for (int i = 0; i < matrix.size(); i++) {
            List<Pair> p = new ArrayList<Pair>();
            for (int j = 0; j < matrix.get(i).size(); j++) {
                p.add(new Pair(i, j, matrix.get(i).get(j)));
            }
            matrixPair.add(p);
        }
        return matrixPair;
    }

    static class Pair {
        int x, y, val;
        boolean visited;

        public Pair(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.val = value;
            if (val == 0) {
                visited = true;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (x != pair.x) return false;
            return y == pair.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }
}
