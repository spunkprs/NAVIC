package binarytree;


import java.util.*;

/**
 You are given a weighted tree consisting of n nodes numbered from 0 to n - 1.

 The tree is rooted at node 0 and represented with a 2D array edges of size n
 where edges[i] = [pari, weighti] indicates that node pari is the parent of node i, and the edge between
 them has a weight equal to weighti. Since the root does not have a parent, you have edges[0] = [-1, -1].

 Choose some edges from the tree such that no two chosen edges are adjacent and the sum of the weights of the
 chosen edges is maximized.

 Return the maximum sum of the chosen edges.

 Note:

 1.) You are allowed to not choose any edges in the tree, the sum of weights in this case will be 0.
 2.) Two edges Edge1 and Edge2 in the tree are adjacent if they have a common node.
 In other words, they are adjacent if Edge1 connects nodes a and b and Edge2 connects nodes b and c.

 Constraints:

 1.) n == edges.length
 2.) 1 <= n <= pow(10,5)
 3.) edges[i].length == 2
 4.) par0 == weight0 == -1
 5.) 0 <= pari <= n - 1 for all i >= 1.
 6.) pari != i
 7.) -pow(10,6) <= weighti <= pow(10,6) for all i >= 1.
 8.) edges represents a valid tree.

 * */

public class ChooseEdgesToMaximiseScore {

    public static void main(String ar[]) {
        ChooseEdgesToMaximiseScore unit = new ChooseEdgesToMaximiseScore();
        //int edges[][] = {{-1,-1},{0,5},{0,10},{2,6},{2,4}};
        //int edges[][] = {{-1,-1},{0,5},{0,-6},{0,7}};
        int edges[][] = {{-1,-1},{5,551218},{4,866844},{0,828151},{6,17412},{7,-298822},{3,700735},{6,-884559}};
        System.out.println("Maximum sum of chosen non adjacent edges is " + unit.maxScore(edges));
    }

    public long maxScore(int[][] edges) {
        if(edges.length > 1) {
            TreeNode root = prepareTreeOne(edges);
            return traverseTree(root);
        }
        return 0;
    }

    private int traverseTree(TreeNode root) {
        Pair result = processTreeTraversal(root);
        return Math.max(result.valueConsidered, result.valueNotConsidered);
    }

    private Pair processTreeTraversal(TreeNode node) {
        List<TreeNode> children = node.children;
        List<Pair> pairList = new ArrayList<>();
        if (children == null) {
            return new Pair(node.edgeWeight > 0 ? node.edgeWeight : 0, 0);
        } else {
            for (TreeNode child : children) {
                pairList.add(processTreeTraversal(child));
            }
        }
        return generateResultantPair(node, pairList);
    }

    private Pair generateResultantPair(TreeNode node, List<Pair> pairList) {
            int valueConsidered = node.edgeWeight > 0 ? node.edgeWeight : 0;
            for (Pair p : pairList) {
                valueConsidered += p.valueNotConsidered;
            }
            int valueNotConsidered = 0;
            for (int i = 0; i < pairList.size(); i++) {
                int value = pairList.get(i).valueConsidered;
                for (int j = 0; j < pairList.size(); j++) {
                    if (i != j) {
                        value += pairList.get(j).valueNotConsidered;
                    }
                }
                valueNotConsidered = value > valueNotConsidered ? value : valueNotConsidered;
            }
            return new Pair(valueConsidered, valueNotConsidered);
    }

    private TreeNode prepareTreeOne(int[][] edges) {
        Map<Integer, TreeNode> map = new HashMap<>();
        List<Coordinates> coordinatesList = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            if (i == 0) {
                coordinatesList.add(new Coordinates(-1, i, -1));
            } else {
                int parent = edges[i][0];
                int edgeWeight = edges[i][1];
                coordinatesList.add(new Coordinates(parent, i, edgeWeight));
            }
        }
        Collections.sort(coordinatesList, new CoordinatesComparator());
        for (Coordinates coordinates : coordinatesList) {
            int child = coordinates.child;
            int parent = coordinates.parent;
            TreeNode node = null;
            if (!map.containsKey(child)) {
                node = new TreeNode(child, coordinates.edgeWeight);
            } else {
                node = map.get(child);
                node.edgeWeight = coordinates.edgeWeight;
            }

            if (parent == -1) {
                map.put(child, node);
            } else {
                TreeNode parentNode = map.get(parent);
                if (parentNode == null) {
                    parentNode = new TreeNode(parent, -1);
                    map.put(parent, parentNode);
                }
                node.parent = parentNode;
                List<TreeNode> childNodes = parentNode.children;
                if (childNodes == null) {
                    childNodes = new ArrayList<>();
                    parentNode.children = childNodes;
                }
                childNodes.add(node);
            }
            map.put(child, node);
        }
        return map.get(0);
    }

    static class TreeNode {
        TreeNode parent;
        List<TreeNode> children;
        int val;
        int edgeWeight;

        public TreeNode(int val, int edgeWeight) {
            this.val = val;
            this.edgeWeight = edgeWeight;
        }
    }

    static class Pair {
        int valueConsidered;
        int valueNotConsidered;

        public Pair(int valueConsidered, int valueNotConsidered) {
            this.valueConsidered = valueConsidered;
            this.valueNotConsidered = valueNotConsidered;
        }
    }

    static class Coordinates {
        int parent;
        int child;
        int edgeWeight;

        public Coordinates(int parent, int child, int edgeWeight) {
            this.parent = parent;
            this.child = child;
            this.edgeWeight = edgeWeight;
        }
    }

    static class CoordinatesComparator implements Comparator<Coordinates> {

        @Override
        public int compare(Coordinates o1, Coordinates o2) {
            if (o1.parent != o2.parent) {
                return o1.parent < o2.parent ? -1 : 1;
            } else {
                return o1.child < o2.child ? -1 : 1;
            }
        }
    }

}
