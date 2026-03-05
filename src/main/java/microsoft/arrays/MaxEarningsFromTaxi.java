package microsoft.arrays;

import java.util.*;

public class MaxEarningsFromTaxi {

    public static void main(String ar[]) {
        MaxEarningsFromTaxi unit = new MaxEarningsFromTaxi();
        int n = 5;
        int rides[][] = {{2,5,4},{1,5,1}};

        //int rides[][] = {{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}};
        //int n = 20;
        System.out.print("Max money that can be collected is " + unit.maxTaxiEarnings(n, rides));
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        List<Ride> rideList = fetchRides(rides);
        if (rideList.size() > 1) {
            TreeMap<Integer, List<Ride>> treeMap = prepareMap(rideList);
            Set<Integer> startTimeSet = treeMap.keySet();
            return processToComputeMaxEarning(startTimeSet, treeMap);
        } else {
            return rideList.get(0).endPoint - rideList.get(0).startPoint + rideList.get(0).tip;
        }
    }

    private long processToComputeMaxEarning(Set<Integer> startTimeSet, TreeMap<Integer, List<Ride>> treeMap) {
        Map<Integer, Long> internalMap = new HashMap<>();
        long finalResult = 0;
        for (Integer startTime : startTimeSet) {
            long intermittentResult = 0;
            if (!internalMap.containsKey(startTime)) {
                List<Ride> associatedRides = treeMap.get(startTime);
                for (Ride ride : associatedRides) {
                    long amount = ride.endPoint - ride.startPoint + ride.tip;
                    intermittentResult = Math.max(intermittentResult, amount + process(ride, internalMap, treeMap));
                }
                internalMap.put(startTime, intermittentResult);
                finalResult = Math.max(finalResult, intermittentResult);
            } else {
                finalResult = Math.max(finalResult, internalMap.get(startTime));
            }
        }
        return finalResult;
    }

    private long process(Ride parentRide, Map<Integer, Long> internalMap, TreeMap<Integer, List<Ride>> treeMap) {
        long finalResult = 0;
        Set<Integer> intermittentSet = treeMap.tailMap(parentRide.endPoint, true).keySet();
        if (!intermittentSet.isEmpty()) {
            for (Integer startTime : treeMap.tailMap(parentRide.endPoint, true).keySet()) {
                long intermittentResult = 0;
                if (!internalMap.containsKey(startTime)) {
                    List<Ride> associatedRides = treeMap.get(startTime);
                    for (Ride ride : associatedRides) {
                        long amount = ride.endPoint - ride.startPoint + ride.tip;
                        intermittentResult = Math.max(intermittentResult, amount + process(ride, internalMap, treeMap));
                    }
                    internalMap.put(startTime, intermittentResult);
                } else {
                    intermittentResult = internalMap.get(startTime);
                }
                finalResult = Math.max(finalResult, intermittentResult);
            }
        } else {
            long earning = parentRide.endPoint - parentRide.startPoint + parentRide.tip;
            if (!internalMap.containsKey(parentRide.startPoint)) {
                internalMap.put(parentRide.startPoint, earning);
                finalResult = Math.max(finalResult, earning);
            } else {
                internalMap.put(parentRide.startPoint, Math.max(earning, internalMap.get(parentRide.startPoint)));
                finalResult = Math.max(finalResult, internalMap.get(parentRide.startPoint));
            }
        }
        return finalResult;
    }

    private TreeMap<Integer, List<Ride>> prepareMap(List<Ride> rideList) {
        TreeMap<Integer, List<Ride>> treeMap = new TreeMap<>();
        for (Ride ride : rideList) {
            if (!treeMap.containsKey(ride.startPoint)) {
                List<Ride> rides = new ArrayList<>();
                rides.add(ride);
                treeMap.put(ride.startPoint, rides);
            } else {
                List<Ride> rides = treeMap.get(ride.startPoint);
                rides.add(ride);
            }
        }
        return treeMap;
    }

    private List<Ride> fetchRides(int[][] rides) {
        List<Ride> rideList = new ArrayList<>();
        for (int i = 0; i < rides.length; i++) {
            int startTime = rides[i][0];
            int endTime = rides[i][1];
            int tip = rides[i][2];
            rideList.add(new Ride(startTime, endTime, tip));
        }
        return rideList;
    }

    static class Ride {
        private int startPoint;
        private int endPoint;
        private int tip;

        public Ride(int startPoint, int endPoint, int tip) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.tip = tip;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Ride ride = (Ride) o;
            return startPoint == ride.startPoint;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startPoint);
        }
    }
}
