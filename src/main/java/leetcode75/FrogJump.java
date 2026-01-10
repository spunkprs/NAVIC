package leetcode75;

import java.util.*;

/**
Problem : 403
A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone.
The frog can jump on a stone, but it must not jump into the water.

Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone.
Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction

Constraints:-

a.) 2 <= stones.length <= 2000
b.) 0 <= stones[i] <= 2^31 - 1
c.) stones[0] == 0
d.) stones is sorted in a strictly increasing order

Time Complexity : O(N * N)
Space Complexity : O(N * N)

Beautiful question centered around multidimensional DP
 * */

public class FrogJump {

    public static void main(String ar[]) {
        FrogJump unit = new FrogJump();
        //int stones[] = {0,1,3,5,6,8,12,17};
        int stones[] = {0,1,2,3,4,8,9,11};
        System.out.print("Frog can reach last stone " + unit.canCross(stones));
    }

    public boolean canCross(int[] stones) {
        Map<Integer, Integer> stoneLocations = new TreeMap<>();
        populateStoneLocation(stones, stoneLocations);
        if (stones[1] != 1) {
            return false;
        } else {
            return processToCheckWhetherFrogCanReachLastStone(stones, stoneLocations);
        }
    }

    private void populateStoneLocation(int[] stones, Map<Integer, Integer> stoneLocations) {
        for (int i = 0; i < stones.length; i++) {
            stoneLocations.put(stones[i], i);
        }
    }

    private boolean processToCheckWhetherFrogCanReachLastStone(int[] stones, Map<Integer, Integer> stoneLocations) {
        Map<Pair, Boolean> map = new HashMap<>();
        return process(1, 1, map, stones, stoneLocations);
    }

    private boolean process(int index, int step, Map<Pair, Boolean> map, int stones[], Map<Integer, Integer> stoneLocations) {
        boolean finalResult = false;
        if (index == stones.length - 1) {
            map.put(new Pair(index, step), true);
        } else {
            List<Pair> possibleChildIndexes = fetchPossibleChildIndexes(index, step, stoneLocations, stones);
            for (Pair possibleChildIndex : possibleChildIndexes) {
                if (!map.containsKey(possibleChildIndex)) {
                    boolean intermittentResult = process(possibleChildIndex.index, possibleChildIndex.stepTaken, map, stones, stoneLocations);
                    if (intermittentResult) {
                        finalResult = true;
                        break;
                    }
                } else {
                    boolean result = map.get(possibleChildIndex);
                    if (result) {
                        finalResult = true;
                        break;
                    }
                }
            }
            map.put(new Pair(index, step), finalResult);
        }
        return map.get(new Pair(index, step));
    }

    private List<Pair> fetchPossibleChildIndexes(int childIndex, int step, Map<Integer, Integer> stoneLocations, int stones[]) {
        List<Pair> childIndexes = new ArrayList<>();
        int stepOne = step - 1;
        int stepTwo = step;
        int stepThree = step + 1;

        if (stepOne > 0 && stoneLocations.containsKey(stones[childIndex] + stepOne)) {
            childIndexes.add(new Pair(stoneLocations.get(stones[childIndex] + stepOne), stepOne));
        }

        if (stoneLocations.containsKey(stones[childIndex] + stepTwo)) {
            childIndexes.add(new Pair(stoneLocations.get(stones[childIndex] + stepTwo), stepTwo));
        }

        if (stoneLocations.containsKey(stones[childIndex] + stepThree)) {
            childIndexes.add(new Pair(stoneLocations.get(stones[childIndex] + stepThree), stepThree));
        }
        return childIndexes;
    }

    static class Pair {
        private int index;
        private int stepTaken;

        public Pair(int index, int stepTaken) {
            this.index = index;
            this.stepTaken = stepTaken;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return index == pair.index && stepTaken == pair.stepTaken;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, stepTaken);
        }
    }

}
