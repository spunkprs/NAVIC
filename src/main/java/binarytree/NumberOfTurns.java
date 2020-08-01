package binarytree;

import java.util.ArrayList;
import java.util.List;

public class NumberOfTurns {

    private static boolean flag = false;

    public static int numberOfTurn(Node root, int first, int second)
    {
        int numberOfTurns = 0;
        List<Pair> pairListOne = new ArrayList<Pair>();
        processToComputeTurns(root, first, pairListOne, null);

        List<Pair> pairListTwo = new ArrayList<Pair>();
        flag = false;
        processToComputeTurns(root, second, pairListTwo, null);
        flag = false;

        Pair lca = findLCA(pairListOne, pairListTwo);
        numberOfTurns += computeTurns(pairListOne, lca);
        numberOfTurns +=computeTurns(pairListTwo, lca);

        boolean decision = false;
        if (lca.node.data == first || lca.node.data == second) {
            decision = true;
        }

        if (decision) {
            return numberOfTurns;
        } else {
            return numberOfTurns + 1;
        }
    }

    private static int computeTurns(List<Pair> pairList, Pair lca) {
        int numberOfTurns = 0;
        int num = pairList.size() - 1;
        if (num > 0) {
          for (int i = num; i >= 1; i--) {
              if (!pairList.get(i).type.equals(pairList.get(i - 1).type) && !pairList.get(i).equals(lca) && !pairList.get(i-1).equals(lca)) {
                  numberOfTurns++;
              }
          }
        }
        return numberOfTurns;
    }

    private static Pair findLCA(List<Pair> pairListOne, List<Pair> pairListTwo) {
        Pair lca = null;
        int sizeOne = pairListOne.size();
        int sizeTwo = pairListTwo.size();
        int size = 0;
        if (sizeOne >= sizeTwo) {
            size = sizeTwo;
        } else {
            size = sizeOne;
        }

        int i = 0;
        while (i < size) {
            if (pairListOne.get(i).node.data != pairListTwo.get(i).node.data) {
                lca = pairListOne.get(i-1);
                break;
            } else {
                i++;
            }
        }
        if (lca == null) {
            lca = pairListOne.get(i-1);
        }
       return lca;
    }

    private static void processToComputeTurns(Node node, int first, List<Pair> pairList, String type) {
        if (node.data != first && !flag) {
            pairList.add(new Pair(node, type));
            Node leftNode = node.left;
            Node rightNode = node.right;

            if (leftNode != null) {
                processToComputeTurns(leftNode, first, pairList, "L");
                if (!flag) {
                    pairList.remove(pairList.size() - 1);
                }
            }

            if (rightNode != null) {
                processToComputeTurns(rightNode, first, pairList, "R");
                if (!flag) {
                    pairList.remove(pairList.size() - 1);
                }
            }
        } else if (node.data == first && !flag) {
            pairList.add(new Pair(node, type));
            flag = true;
        }
    }

    static class Pair {
        private Node node;
        private String type;

        public Pair(Node node, String type) {
            this.node = node;
            this.type = type;
        }

        public Node getNode() {
            return node;
        }

        public String getType() {
            return type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (node.data == pair.node.data) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = node != null ? node.hashCode() : 0;
            result = 31 * result + (type != null ? type.hashCode() : 0);
            return result;
        }
    }
}




