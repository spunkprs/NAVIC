package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SumOfDependencies {

    public static void main (String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String testCases = br.readLine();
            int num = Integer.parseInt(testCases);
            for (int i = 1; i <= num; i++) {
                String input = br.readLine();
                input = br.readLine();
                System.out.println(input.split(" ").length/2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
