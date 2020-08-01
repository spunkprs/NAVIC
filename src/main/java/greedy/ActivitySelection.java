package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ActivitySelection {

    public static void main(String[] ar) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            int numberOfTestCases = Integer.parseInt(br.readLine());
            for (int i = 1; i <= numberOfTestCases; i++) {
                int numberOfActivities = Integer.parseInt(br.readLine());
                int startTime[] = new int[numberOfActivities];
                int endTime[] = new int[numberOfActivities];
                String startTimes[] = br.readLine().split(" ");
                String endTimes[] = br.readLine().split(" ");
                Map<Integer, Queue<Integer>> map = new HashMap<Integer, Queue<Integer>>();
                for (int k = 0; k < startTime.length; k++) {
                    startTime[k] = Integer.parseInt(startTimes[k]);
                    endTime[k] = Integer.parseInt(endTimes[k]);
                    if (!map.containsKey(endTime[k])) {
                        Queue<Integer> queue = new LinkedList<Integer>();
                        queue.add(k);
                        map.put(endTime[k], queue);
                    } else {
                        Queue<Integer> queue = map.get(endTime[k]);
                        queue.add(k);
                    }
                }
                System.out.println(processToParseInputAndComputeNumberOfActivities(startTime, endTime, map));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int processToParseInputAndComputeNumberOfActivities(int[] startTime, int[] endTime, Map<Integer, Queue<Integer>> map) {
        int startTimeCopy[] = new int[startTime.length];
        Arrays.sort(endTime);
        for (int i = 0; i < endTime.length; i++) {
            if (!map.get(endTime[i]).isEmpty()) {
                startTimeCopy[i] = startTime[map.get(endTime[i]).poll()];
            }
        }

        int numberOfActivities = 1;

        int j = 0;
        for (int i = 1; i < endTime.length; i++) {
            if (startTimeCopy[i]>= endTime[j]) {
                numberOfActivities++;
                j = i;
            }
        }
        return numberOfActivities;
    }
}
