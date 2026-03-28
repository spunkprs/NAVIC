package capitalOne.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 You are designing a delivery system using drones in a linear warehouse. The warehouse is represented as a number line starting at position 0 and
 ending at position target (target > 0). Along this line, there are charging stations placed at various positions, represented by an array stations,
 where stations[i] is the position of the ith charging station.
 Each drone has a limited battery that allows it to travel a maximum of 10 units after being fully charged. For example, if a drone is charged at position 12,
 it can travel to positions 12, 13, 14... up to position 22 (inclusive), but cannot reach position 23 or beyond without recharging.

 Your delivery protocol requires the following steps:

 From your current position, pick up the cargo and carry it on foot to the nearest charging station ahead of you.
 If there are no more stations ahead, carry the cargo on foot to the target position.
 Deploy a fully charged drone from this station and send it with the cargo as far as possible toward the target.
 If the target hasn't been reached, walk to the point where the drone landed to retrieve the cargo, then repeat from step 1.
 Your task is to calculate the total distance over which you must carry the cargo on foot, from position 0 to position target.

 Note: You are not expected to provide the most optimal solution, but a solution with time complexity not worse than O(stations.length x target) will fit within the execution time limit.

 Example:
 For target = 23 and stations = [7, 4, 14], the output should be solution(target, stations) = 4

 Starting at 0, you find the nearest station at position stations[1] = 4, so you carry the cargo on foot for 4 units to get there, and then deploy a drone that travels to position 14.
 There is another station at 14 (stations[2] = 14), which you use to deploy another drone that reaches the target position target = 23

 Space Complexity = O(N), where N being number of stations
 Time Complexity = O(NLogN), where N being number of stations
 * */

public class DeliverySystemUsingDrones {

    public static void main(String ar[]) {
        DeliverySystemUsingDrones unit = new DeliverySystemUsingDrones();

        //int target = 35;
        //int stations[] = {7, 4, 12, 20, 21, 32};

        int target = 23;
        int stations[] = {7, 4, 14};

        System.out.println("Min distance to cover on foot is " + unit.minDistanceToCoverOverFoot(target, stations));
    }

    public int minDistanceToCoverOverFoot(int target, int stations[]) {
        int distanceCoveredByFoot = 0;
        TreeSet<Integer> treeSet = populateTreeSet(stations);
        Set<Integer> visitedStations = new HashSet<>();
        int firstStation = treeSet.first();
        visitedStations.add(firstStation);

        distanceCoveredByFoot += firstStation;

        if (target > firstStation) {
            distanceCoveredByFoot = processToComputeMinDistance(distanceCoveredByFoot, firstStation, treeSet, visitedStations, target);
        }
        return distanceCoveredByFoot;
    }

    private int processToComputeMinDistance(int distanceCoveredByFoot, int currentStation, TreeSet<Integer> treeSet, Set<Integer> visitedStations, int target) {
        while (currentStation + 10 < target) {
            if (treeSet.contains(currentStation + 10)) {
                currentStation += 10;
                visitedStations.add(currentStation);
            } else {
                Integer nextStation = treeSet.higher(currentStation + 10);
                Integer prevStation = treeSet.lower(currentStation + 10);

                if (nextStation != null && !visitedStations.contains(prevStation)) {
                    currentStation = prevStation;
                    visitedStations.add(currentStation);
                } else if (nextStation != null && visitedStations.contains(prevStation)) {
                    distanceCoveredByFoot += nextStation - (currentStation + 10);
                    currentStation = nextStation;
                    visitedStations.add(currentStation);
                } else if (nextStation == null && !visitedStations.contains(prevStation)) {
                    currentStation = prevStation;
                    visitedStations.add(currentStation);
                } else if (nextStation == null && visitedStations.contains(prevStation)) {
                    currentStation += 10;
                }
            }
        }
        return distanceCoveredByFoot;
    }

    private TreeSet<Integer> populateTreeSet(int[] stations) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        Arrays.stream(stations).forEach(x -> treeSet.add(x));
        return treeSet;
    }
}
