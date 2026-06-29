package microsoft.dfs;

import java.util.*;

public class DetonateMaximumBombs {

    public static void main(String ar[]) {
        DetonateMaximumBombs unit = new DetonateMaximumBombs();
        //int[][] bombs = {{1,2,3},{2,3,1},{3,4,2},{4,5,3},{5,6,4}};

        int [][] bombs = {{56,80,2},{55,9,10},{32,75,2},{87,89,1},{61,94,3},{43,82,9},{17,100,6},{50,6,7},{9,66,7},{98,3,6},{67,50,2},{79,39,5},
                {92,60,10},{49,9,9},{42,32,10}};

        System.out.print("Maximum bombs that can de detonated is " + unit.maximumDetonation(bombs));
    }

    public int maximumDetonation(int[][] bombs) {

        Node bombsArr[] = new Node[bombs.length];
        prepareArr(bombs, bombsArr);
        Map<Node, Integer> map = prepareMap(bombsArr);
        return processToFindMaximumBombsForDetonation(bombsArr, map);
    }

    private Map<Node, Integer> prepareMap(Node[] bombsArr) {
        Map<Node, Integer> map = new HashMap<>();

        for (Node bomb : bombsArr) {
            if (map.containsKey(bomb)) {
                map.put(bomb, map.get(bomb) + 1);
            } else {
                map.put(bomb, 1);
            }
        }
        return map;
    }

    private int processToFindMaximumBombsForDetonation(Node[] bombsArr, Map<Node, Integer> map) {
        Set<Node> visitedNodes = new HashSet<>();
        int result = 0;
        for (Node bomb : bombsArr) {
            visitedNodes.add(bomb);
            int computedResult = process(bomb, bombsArr, visitedNodes, map);
                result = computedResult > result ? computedResult : result;
                visitedNodes = new HashSet<>();
        }
        return result;
    }

    private int process(Node bomb, Node[] bombsArr, Set<Node> visitedNodes, Map<Node, Integer> map) {
        int result = 0;
        for (Node reachableBomb : fetchNeighbours(bomb, bombsArr, visitedNodes)) {
            result += process(reachableBomb, bombsArr, visitedNodes, map);
        }
        return result + map.get(bomb);
    }

    private List<Node> fetchNeighbours(Node bomb, Node[] bombsArr, Set<Node> visitedNodes) {
        List<Node> reachableBombs = new ArrayList<>();
        for (int i = 0; i < bombsArr.length; i++) {
            if (bombsArr[i] != bomb && !visitedNodes.contains(bombsArr[i])) {
                long valOne = bomb.xVal - bombsArr[i].xVal;
                long valTwo = bomb.yVal - bombsArr[i].yVal;

                if (1l * bomb.radius * bomb.radius >= ((valOne * valOne) + (valTwo * valTwo))) {
                    reachableBombs.add(bombsArr[i]);
                    visitedNodes.add(bombsArr[i]);
                }
            }
        }
        return reachableBombs;
    }

    private void prepareArr(int[][] bombs, Node[] bombsArr) {
        for (int i = 0; i < bombs.length; i++) {
            bombsArr[i] = new Node(bombs[i][0], bombs[i][1], bombs[i][2]);
        }
    }

    static class Node {
        private int xVal;
        private int yVal;
        private int radius;

        public Node(int x, int y, int radius) {
            this.xVal = x;
            this.yVal = y;
            this.radius = radius;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return xVal == node.xVal && yVal == node.yVal && radius == node.radius;
        }

        @Override
        public int hashCode() {
            return Objects.hash(xVal, yVal, radius);
        }
    }
}
