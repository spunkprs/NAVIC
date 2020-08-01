package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindWhetherPathExists {

    private static Set<Pair> path = new HashSet<Pair>();
    private static boolean haltProcess = false;

    public static void main (String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numberOfTestCases; i++) {
                int size = Integer.parseInt(br.readLine());
                int matrix[][] = new int[size][size];
                populateMatrix(size, matrix, br);
                processToFindIfPathExists(matrix);
                if (haltProcess) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
                haltProcess = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processToFindIfPathExists(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1) {
                    Pair parent = new Pair(i, j);
                    path.add(parent);
                    processToFindPath(matrix, parent);
                    path.remove(parent);
                    if (haltProcess) {
                        break;
                    }
                }
            }
            if (haltProcess) {
                break;
            }
        }
    }

    private static void processToFindPath(int[][] matrix, Pair parent) {
        List<Pair> children = findChildren(parent.getX() , parent.getY(), matrix);
        for (Pair child : children) {
            path.add(child);
            processToFindPath(matrix, child);
            path.remove(child);
        }
    }

    private static List<Pair> findChildren(int i, int j, int[][] matrix) {
        List<Pair> children = new ArrayList<Pair>();
        if (!haltProcess) {
            if (j + 1 < matrix[0].length) {
                Pair child = new Pair(i, j + 1);
                if (matrix[i][j + 1] == 3 && !path.contains(child)) {
                    children.add(child);
                }

                if (matrix[i][j + 1] == 2) {
                    haltProcess = true;
                }

            }

            if (j - 1 >= 0 && !haltProcess) {
                Pair child = new Pair(i, j - 1);
                if (matrix[i][j - 1] == 3 && !path.contains(child)) {
                    children.add(child);
                }

                if (matrix[i][j - 1] == 2) {
                    haltProcess = true;
                }

            }

            if (i + 1 < matrix.length && !haltProcess) {
                Pair child = new Pair(i + 1, j);
                if (matrix[i + 1][j] == 3 && !path.contains(child)) {
                    children.add(child);
                }

                if (matrix[i + 1][j] == 2) {
                    haltProcess = true;
                }
            }

            if (i - 1 >=0 && !haltProcess) {
                Pair child = new Pair(i -1 , j);
                if (matrix[i - 1][j] == 3 && !path.contains(child)) {
                    children.add(child);
                }

                if (matrix[i - 1][j] == 2) {
                    haltProcess = true;
                }
            }
        }
        return children;
    }

    private static void populateMatrix(int size, int[][] matrix, BufferedReader br) {
        try {
            String input = br.readLine();
            String inputArr[] = input.split(" ");
            int i = 0;
            while (i < inputArr.length) {
                int j = 0;
                while (j < matrix[0].length) {
                    matrix[i/size][j] = Integer.parseInt(inputArr[i]);
                    j++;
                    i++;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
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

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
