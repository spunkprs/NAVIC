package capitalOne.greedy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class DeliverySystemUsingDrones {

    public static void main(String ar[]) {
        DeliverySystemUsingDrones unit = new DeliverySystemUsingDrones();

        int target = 35;
        int stations[] = {7, 4, 12, 20, 21, 32};

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
