package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MinimumSwapsForBracketBalancing {

    public static void main (String[] args)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 0; i < numberOfTestCases; i++) {
                int lengthOfString = Integer.parseInt(br.readLine());
                String stringToBalance = br.readLine();
                System.out.println(processToComputeMinimumSwaps(stringToBalance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int processToComputeMinimumSwaps(String stringToBalance) {
        Stack<Character> stack = new Stack<Character>();
        char arr[] = stringToBalance.toCharArray();
        int i = 0;
        int numberOfSwaps = 0;
        while (i < arr.length) {
            if (arr[i] == '[') {
                stack.push(arr[i]);
                i++;
            } else if (arr[i] == ']' && !stack.isEmpty()) {
                stack.pop();
                i++;
            } else if (arr[i] == ']' && stack.isEmpty() && arr[i + 1] == '[') {
                numberOfSwaps++;
                i+=2;
            }
        }
        return numberOfSwaps;
    }
}
