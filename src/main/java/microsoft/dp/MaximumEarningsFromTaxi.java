package microsoft.dp;

import java.util.*;

/**
Problem : 2008
Link : https://leetcode.com/problems/maximum-earnings-from-taxi/description/
There are n points on a road you are driving your taxi on. The n points on the road are labeled from 1 to n in the direction
you are going, and you want to drive from point 1 to point n to make money by picking up passengers. You cannot change the direction of the taxi.

The passengers are represented by a 0-indexed 2D integer array rides, where rides[i] = [starti, endi, tipi] denotes the ith passenger requesting a ride from point starti to point endi who is willing to give a tipi dollar tip.

For each passenger i you pick up, you earn endi - starti + tipi dollars. You may only drive at most one passenger at a time.

Given n and rides, return the maximum number of dollars you can earn by picking up the passengers optimally.

Note: You may drop off a passenger and pick up a different passenger at the same point.

Constraints:-

a.) 1 <= n <= 10^5
b.) 1 <= rides.length <= 3 * 10^4
c.) rides[i].length == 3
d.) 1 <= starti < endi <= n
e.) 1 <= tipi <= 10^5

Level : Medium

For now 13/84 test cases are getting passed, will definitely coming back on it
Approach : Top Down Dynamic Programming
 * */

public class MaximumEarningsFromTaxi {

    public static void main(String ar[]) {
        int n = 10;
        //int rides [][] = {{2, 5, 4}, {1, 5, 1}};

        //int rides[][] = {{1,6,1},{3,10,2},{10,12,3},{11,12,2},{12,15,2},{13,18,1}};

        int rides[][] = {{2,3,6},{8,9,8},{5,9,7},{8,9,1},{2,9,2},{9,10,6},{7,10,10},{6,7,9},{4,9,7},{2,3,1}};

        MaximumEarningsFromTaxi unit = new MaximumEarningsFromTaxi();
        System.out.print("Maximum earning from taxi is " + unit.maxTaxiEarnings(n, rides));
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        TreeMap<Integer, List<RideNode>> rideNodesMap = prepareRideNodes(rides);
        return processToComputeMaxTaxiEarnings(rideNodesMap);
    }

    private long processToComputeMaxTaxiEarnings(TreeMap<Integer, List<RideNode>> rideNodesMap) {
        Map<RideNode, Long> mapping = new HashMap<>();
        for (Integer startPoint : rideNodesMap.keySet()) {
            for (RideNode rn : rideNodesMap.get(startPoint)) {
                process(rn, mapping, rideNodesMap);
            }
        }

        Optional<Long> finalResult = mapping.values().stream().max((a, b) -> {
            return a < b ? -1 : a > b ? 1 : 0;
        });
        return finalResult.get();
    }

    private long process(RideNode parentRideNode, Map<RideNode, Long> mapping, TreeMap<Integer, List<RideNode>> rideNodesMap) {
        long result = 0;
        if (!mapping.containsKey(parentRideNode)) {
            List<RideNode> subsequentNodes = fetchSubsequentNodes(parentRideNode, mapping, rideNodesMap);

            if (subsequentNodes.isEmpty()) {
                long val = parentRideNode.endPoint - parentRideNode.startPoint + parentRideNode.tip;
                mapping.put(parentRideNode, val);
                result = val;
            } else {
                for (RideNode sn : subsequentNodes) {
                    long intermittentResult = process(sn, mapping, rideNodesMap);
                    result = intermittentResult > result ? intermittentResult : result;
                }
                long val = parentRideNode.endPoint - parentRideNode.startPoint + parentRideNode.tip;
                mapping.put(parentRideNode, result + val);
            }
        } else {
        result = mapping.get(parentRideNode);
        }
        return result;
    }

    private List<RideNode> fetchSubsequentNodes(RideNode parentRideNode, Map<RideNode, Long> mapping, TreeMap<Integer, List<RideNode>> rideNodesMap) {
        List<RideNode> subsequentNodes = new ArrayList<>();
        NavigableMap<Integer, List<RideNode>> rideNodesMapIntermittent = rideNodesMap.tailMap(parentRideNode.endPoint, true);
        for (Integer num : rideNodesMapIntermittent.keySet()) {
            subsequentNodes.addAll(rideNodesMapIntermittent.get(num));
        }
        return subsequentNodes;
    }

    private TreeMap<Integer, List<RideNode>> prepareRideNodes(int[][] rides) {
        List<RideNode> intermittentList = new ArrayList<>();
        TreeMap<Integer, List<RideNode>> rideNodesMap = new TreeMap<>(new RideNodeComparator());
        for (int i = 0; i < rides.length; i++) {
            intermittentList.add(new RideNode(rides[i][0], rides[i][1], rides[i][2]));
        }
        for (RideNode rn : intermittentList) {
            if (rideNodesMap.isEmpty()) {
                List<RideNode> nodesList = new ArrayList<>();
                nodesList.add(rn);
                rideNodesMap.put(rn.startPoint, nodesList);
            } else {
                if (rideNodesMap.containsKey(rn.startPoint)) {
                    List<RideNode> existingRideNodes = rideNodesMap.get(rn.startPoint);
                    existingRideNodes.add(rn);
                } else {
                    List<RideNode> nodesList = new ArrayList<>();
                    nodesList.add(rn);
                    rideNodesMap.put(rn.startPoint, nodesList);
                }
            }
        }
        return rideNodesMap;
    }

    static class RideNode {
        private int startPoint;
        private int endPoint;
        private int tip;

        public RideNode(int startPoint, int endPoint, int tip) {
            this.startPoint = startPoint;
            this.endPoint = endPoint;
            this.tip = tip;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RideNode rideNode = (RideNode) o;
            return startPoint == rideNode.startPoint && endPoint == rideNode.endPoint && tip == rideNode.tip;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startPoint, endPoint, tip);
        }
    }

    static class RideNodeComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 < o2 ? -1 : o1 > o2 ? 1 : 0;
        }
    }
}
