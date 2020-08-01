package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class JobSecheduling {

    public static void main(String ar[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int s = 1; s <= numberOfTestCases; s++) {
                int numberOfJobs = Integer.parseInt(br.readLine());
                int i = 0;
                int deadLines[] = new int[numberOfJobs];
                int profits[] = new int[numberOfJobs];
                String inputArr[] = br.readLine().split(" ");
                int j = 1;
                int k = 2;
                int p = 0, m = 0;
                int maxDeadLine = -1;
                Map<Integer, Queue<Integer>> map = new HashMap<Integer, Queue<Integer>>();
                while (i < numberOfJobs * 3) {
                    if (i == j && j < (numberOfJobs * 3) - 1) {
                        deadLines[p] = Integer.parseInt(inputArr[i]);
                        p++;
                        j+=3;
                        if (maxDeadLine < Integer.parseInt(inputArr[i])) {
                            maxDeadLine = Integer.parseInt(inputArr[i]);
                        }
                    }

                    if (i == k && k < (numberOfJobs * 3)) {
                        profits[m] = Integer.parseInt(inputArr[i]);
                        if (!map.containsKey(Integer.parseInt(inputArr[i]))) {
                            Queue<Integer> queue = new LinkedList<Integer>();
                            queue.add(m);
                            map.put(Integer.parseInt(inputArr[i]), queue);
                        } else {
                            Queue<Integer> queue = map.get(Integer.parseInt(inputArr[i]));
                            queue.add(m);
                        }
                        m++;
                        k+=3;
                    }
                    i++;
                }
                processToComputeMaximumProfitAndNumberOfJobs(maxDeadLine ,deadLines, profits, map);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processToComputeMaximumProfitAndNumberOfJobs(int maxDeadLine, int[] deadLines, int[] profits, Map<Integer, Queue<Integer>> map) {
        int updatedDeadlines[] = new int[deadLines.length];
        Arrays.sort(profits);
        for (int i = 0; i < updatedDeadlines.length; i++) {
            if (map.containsKey(profits[i])) {
                Queue<Integer> queue = map.get(profits[i]);
                if (!queue.isEmpty()) {
                    updatedDeadlines[i] = deadLines[queue.poll()];
                }
            }
        }

        Content arr[] = new Content[maxDeadLine];
        int sum = 0;
        int numberOfJobs = 0;
        for (int i = profits.length - 1; i >= 0; i--) {
            int deadline = updatedDeadlines[i];
            int k = deadline - 1;
            while (k >= 0) {
                if (arr[k] == null) {
                    arr[k] = new Content(profits[i]);
                    sum += arr[k].value;
                    numberOfJobs++;
                    break;
                }
                k--;
            }
        }

        System.out.println(numberOfJobs + " " + sum);
    }

    static class Content {
        int value;
        Content(int value) {
            this.value = value;
        }
    }
}
