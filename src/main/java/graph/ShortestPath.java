package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShortestPath {

    public static void main (String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String testCases = br.readLine();
            int num = Integer.parseInt(testCases);
            for (int i = 1; i <= num; i++) {
                String input = br.readLine();
                processToComputeShortestPath(Integer.parseInt(input));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processToComputeShortestPath(int number) {
        int num = 1;
        int result = number;
        int shortestPath = 0;

        while (result >= num) {
            if (result > 1) {
                if (result % 3 == 0) {
                    result /= 3;
                    shortestPath++;
                } else {
                    result--;
                    shortestPath++;
                }
            } else {
                break;
            }
        }
        System.out.println(shortestPath);
    }

}
