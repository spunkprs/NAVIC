package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FloydWarshall {

    public static void main (String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numberOfTestCases; i++) {
                int numberOfVertices = Integer.parseInt(br.readLine());
                String adjacencyMatrix[][] = new String[numberOfVertices][numberOfVertices];
                for (int j = 1; j <= numberOfVertices; j++) {
                    populateAdjacencyMatrix(adjacencyMatrix, br, j);
                }

                adjacencyMatrix = processFloydWarshallAlgorithm(adjacencyMatrix);
                printResult(adjacencyMatrix);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printResult(String[][] adjacencyMatrix) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix.length; j++) {
                System.out.print(adjacencyMatrix[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    private static String[][] processFloydWarshallAlgorithm(String adjacencyMatrix[][]) {
        String adjacencyMatrixTemp [][] = new String[adjacencyMatrix.length][adjacencyMatrix.length];
        for (int i = 0; i < adjacencyMatrix.length; i++) {
                for (int j = 0; j < adjacencyMatrixTemp.length; j++) {
                    for (int k = 0; k < adjacencyMatrixTemp.length; k++) {
                        copyContents(adjacencyMatrix, adjacencyMatrixTemp, i);
                        if (k != i && j != i) {
                            if (j == k) {
                                adjacencyMatrixTemp[j][k] = "0";
                            } else {
                                String val = adjacencyMatrix[j][k];
                                String valOne = adjacencyMatrix[j][i];
                                String valTwo = adjacencyMatrix[i][k];

                                if (val.equals("INF")) {
                                    if (valOne.equals("INF") || valTwo.equals("INF")) {
                                        adjacencyMatrixTemp[j][k] = "INF";
                                    } else if (!valOne.equals("INF") && !valTwo.equals("INF")) {
                                        adjacencyMatrixTemp[j][k] = String.valueOf(Integer.parseInt(valOne) + Integer.parseInt(valTwo));
                                    }
                                } else {
                                    if (valOne.equals("INF") || valTwo.equals("INF")) {
                                        adjacencyMatrixTemp[j][k] = String.valueOf(Integer.parseInt(val));
                                    } else if (!valOne.equals("INF") && !valTwo.equals("INF")) {
                                        int numOne = Integer.parseInt(val);
                                        int numTwo = Integer.parseInt(valOne) + Integer.parseInt(valTwo);
                                        adjacencyMatrixTemp[j][k] = numOne >= numTwo ? String.valueOf(numTwo) : String.valueOf(numOne);
                                    }
                                }
                            }
                        }
                    }
                }
            adjacencyMatrix = adjacencyMatrixTemp;
            adjacencyMatrixTemp = new String[adjacencyMatrix.length][adjacencyMatrix.length];
         }
         return adjacencyMatrix;
    }

    private static void copyContents(String[][] adjacencyMatrix, String[][] adjacencyMatrixTemp, int i) {
        for (int p = 0; p < adjacencyMatrix.length; p++) {
            adjacencyMatrixTemp[i][p] = adjacencyMatrix[i][p];
        }

        for (int p = 0; p < adjacencyMatrix.length; p++) {
            adjacencyMatrixTemp[p][i] = adjacencyMatrix[p][i];
        }
    }

    private static void populateAdjacencyMatrix(String[][] adjacencyMatrix, BufferedReader br, int row) {
        try {
            String line[] = br.readLine().split(" ");
            for (int i = 0; i < adjacencyMatrix[0].length; i++) {
                adjacencyMatrix[row - 1][i] = line[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
