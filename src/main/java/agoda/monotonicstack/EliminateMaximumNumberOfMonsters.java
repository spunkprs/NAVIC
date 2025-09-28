package agoda.monotonicstack;

import java.util.PriorityQueue;

/**
 You are playing a video game where you are defending your city from a group of n monsters.
 You are given a 0-indexed integer array dist of size n, where dist[i] is the initial distance
 in kilometers of the ith monster from the city.

 The monsters walk toward the city at a constant speed. The speed of each monster is given to you in an integer array
 speed of size n, where speed[i] is the speed of the ith monster in kilometers per minute.

 You have a weapon that, once fully charged, can eliminate a single monster. However, the weapon takes
 one minute to charge. The weapon is fully charged at the very start.

 You lose when any monster reaches your city. If a monster reaches the city at the exact moment
 the weapon is fully charged, it counts as a loss, and the game ends before you can use your weapon.

 Return the maximum number of monsters that you can eliminate before you lose,
 or n if you can eliminate all the monsters before they reach the city.

 Source : LeetCode


 * */

public class EliminateMaximumNumberOfMonsters {

    public static void main(String ar[]) {
        EliminateMaximumNumberOfMonsters unit = new EliminateMaximumNumberOfMonsters();

        int distanceArr[] = {3,2,4};
        int speedArr[] = {5, 3, 2};

        System.out.println("Number of monsters that can be killed is " + unit.eliminateMaximum(distanceArr, speedArr));
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        if (dist.length == 1) {
            return 1;
        }

        PriorityQueue<Double> minHeap = processTimeToReachForAMonster(dist, speed);
        boolean shallContinue = true;

        int count = 0;
        double initialTime = 0;

        while (!minHeap.isEmpty() && shallContinue) {
            if (initialTime < minHeap.peek()) {
                initialTime += 1;
                count++;
                minHeap.poll();
            } else {
                shallContinue = false;
            }
        }
        return count;
    }

    private PriorityQueue<Double> processTimeToReachForAMonster(int[] dist, int[] speed) {
        PriorityQueue<Double> minHeap = new PriorityQueue<>();
        for (int i = 0; i < dist.length ; i++) {
            double distanceD = dist[i];
            double speedD = speed[i];
            double timeTaken = distanceD/speedD;
            minHeap.add(timeTaken);
        }
        return minHeap;
    }
}
