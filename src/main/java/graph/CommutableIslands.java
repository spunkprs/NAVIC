package graph;

import java.util.*;

public class CommutableIslands {

    Map<Integer, Pair> hashMap = new HashMap<Integer, Pair>();
    PriorityQueue<Pair> pairPriorityQueue;


    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        PairComparator pairComparator = new PairComparator();
        pairPriorityQueue = new PriorityQueue<Pair>(pairComparator);
        Map<Integer, List<Pair>> map = prepareInputMap(B);
        return processToComputeMinimumCost(map);
    }

    private int processToComputeMinimumCost(Map<Integer, List<Pair>> map) {
        int totalSum = 0;
        while (!pairPriorityQueue.isEmpty()) {
            Pair startNode = pairPriorityQueue.peek();
            List<Pair> children = map.get(startNode.vetex);
            if (children != null && !children.isEmpty()) {
                for (Pair p : children) {
                    Pair nodePresentInQueue = hashMap.get(p.vetex);
                    if (nodePresentInQueue.value > p.value) {
                        nodePresentInQueue.value = p.value;
                    }
                }
            }
            totalSum +=pairPriorityQueue.peek().value;
            pairPriorityQueue.poll();
            //hashMap.remove(startNode.vetex);
        }
        return totalSum;
    }

    private Map<Integer, List<Pair>> prepareInputMap(ArrayList<ArrayList<Integer>> b) {
        Map<Integer, List<Pair>> map = new LinkedHashMap<Integer, List<Pair>>();
        int k = 0;
        for (int i = 0; i < b.size(); i++) {
            if (!map.containsKey(b.get(i).get(0))) {
                Pair p = new Pair(b.get(i).get(1), b.get(i).get(2));
                List<Pair> pairs = new ArrayList<>();
                pairs.add(p);
                map.put(b.get(i).get(0), pairs);
            } else {
                Pair p = new Pair(b.get(i).get(1), b.get(i).get(2));
                List<Pair> pairs = map.get(b.get(i).get(0));
                pairs.add(p);
            }
            pushElementToPriorityQueueAndSet(b.get(i).get(0), b.get(i).get(1), k);

            if (!map.containsKey(b.get(i).get(1))) {
                Pair p = new Pair(b.get(i).get(0), b.get(i).get(2));
                List<Pair> pairs = new ArrayList<>();
                pairs.add(p);
                map.put(b.get(i).get(1), pairs);
            } else {
                Pair p = new Pair(b.get(i).get(0), b.get(i).get(2));
                List<Pair> pairs = map.get(b.get(i).get(1));
                pairs.add(p);
            }
            k++;
        }
        return map;
    }

    private void pushElementToPriorityQueueAndSet(int vertexOne, int vertexTwo, int k) {
        if (k == 0) {
            Pair p1 = new Pair(vertexOne, k);
            pairPriorityQueue.add(p1);
            Pair p2 = new Pair(vertexTwo, Integer.MAX_VALUE);
            pairPriorityQueue.add(p2);
            hashMap.put(vertexOne, p1);
            hashMap.put(vertexTwo, p2);
        } else {
            if (!hashMap.containsKey(vertexOne)) {
                Pair p = new Pair(vertexOne, Integer.MAX_VALUE);
                hashMap.put(vertexOne, p);
                pairPriorityQueue.add(p);
            }

            if (!hashMap.containsKey(vertexTwo)) {
                Pair p = new Pair(vertexTwo, Integer.MAX_VALUE);
                hashMap.put(vertexTwo, p);
                pairPriorityQueue.add(p);
            }
        }
    }

    class Pair {
        int vetex;
        int value;

        Pair(int vertex, int value) {
            this.vetex = vertex;
            this.value = value;
        }
    }

    class PairComparator implements Comparator<Pair> {

        @Override
        public int compare(Pair p1, Pair p2) {
            return p1.value - p2.value;
        }
    }
}
