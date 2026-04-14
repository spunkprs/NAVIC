package microsoft.bfs;

import java.util.LinkedList;
import java.util.Queue;


/**
A jumping number is defined as a number where all adjacent digits have an absolute difference of 1 (e.g., 7, 43, 898).
All single-digit numbers (0–9) are considered jumping numbers

Time Complexity = O(K) where K is the total number of jumping numbers less than or equal to provided number

Space Complexity = O(K) where K is the total number of jumping numbers less than or equal to provided number

Approach Used : BFS

Not a leet code question rather it was asked during Amazon interview which I was not able to solve convincingly !!

 * */

public class LargestJumpingNumber {

    public static void main(String ar[]) {
        LargestJumpingNumber unit = new LargestJumpingNumber();
        int number = 50;
        System.out.println("Largest jumping number less than " + number + " is " + unit.fetchLargestJumpingNumber(number));
    }

    public int fetchLargestJumpingNumber(int number) {

        if (number <= 10) {
            return number - 1;
        } else {
            return processToFetchLargestJumpingNumber(number);
        }
    }

    private int processToFetchLargestJumpingNumber(int number) {
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            queue.add(i);
        }

        while (!queue.isEmpty()) {
            int peekedNum = queue.peek();
            String sb = String.valueOf(peekedNum);

            int lastDigit = peekedNum % 10;

            if (lastDigit != 0) {
                int numOne = Integer.parseInt(sb + (lastDigit + 1));
                int numTwo = Integer.parseInt(sb + (lastDigit - 1));
                if (numOne < number) {
                    queue.add(numOne);
                }
                if (numTwo < number) {
                    queue.add(numTwo);
                }
            } else {
                int numOne = Integer.parseInt(sb + (lastDigit + 1));
                if (numOne < number) {
                    queue.add(numOne);
                }
            }
            result = peekedNum > result ? peekedNum : result;
            queue.poll();
        }
        return result;
    }

}
