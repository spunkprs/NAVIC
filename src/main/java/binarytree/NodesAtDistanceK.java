package binarytree;

import java.util.ArrayList;
import java.util.List;

public class NodesAtDistanceK {

    private static boolean flag = false;

    public static int printkdistanceNode(Node root, Node target , int k) {
        List<Node> ancestorList = new ArrayList<Node>();
        processToPrepareAncestorList(root, target, ancestorList);
        if (!flag) {
            ancestorList = null;
        }
        if (ancestorList != null) {
            System.out.print(" ");
            processToPrintNodesAtDistanceK(target, k, 0);
            int j = 1;
            for (int i = ancestorList.size() - 1; i >= 0; i--) {
                if (i == ancestorList.size() - 1) {
                if (ancestorList.get(i).left != null && ancestorList.get(i).left.data == target.data) {
                    if (k == j) {
                        System.out.print(ancestorList.get(i).data);
                    } else if (k > j && ancestorList.get(i).right != null) {
                        processToPrintNodesAtDistanceK(ancestorList.get(i).right, k - j - 1, 0);
                    }
                } else if (ancestorList.get(i).right != null && ancestorList.get(i).right.data == target.data) {
                    if (k == j) {
                        System.out.print(ancestorList.get(i).data);
                    } else if (k > j && ancestorList.get(i).left != null) {
                        processToPrintNodesAtDistanceK(ancestorList.get(i).left, k - j - 1, 0);
                    }
                }
                } else {
                    if (ancestorList.get(i).left != null && ancestorList.get(i).left.data == ancestorList.get(i + 1).data) {
                        if (k == j) {
                            System.out.print(ancestorList.get(i).data);
                        } else if (k > j && ancestorList.get(i).right != null) {
                            processToPrintNodesAtDistanceK(ancestorList.get(i).right, k - j - 1, 0);
                        }
                    } else if (ancestorList.get(i).right != null && ancestorList.get(i).right.data == ancestorList.get(i + 1).data) {
                        if (k == j) {
                            System.out.print(ancestorList.get(i).data);
                        } else if (k > j && ancestorList.get(i).left != null) {
                            processToPrintNodesAtDistanceK(ancestorList.get(i).left, k - j - 1, 0);
                        }
                    }
                }
                j++;
            }
        } else {
            return -1;
        }

        return ancestorList.size();
    }

    private static void processToPrintNodesAtDistanceK(Node node, int distance, int computedDistance) {
        if (computedDistance != distance) {
            Node leftNode = node.left;
            Node rightNode = node.right;

            if (leftNode != null) {
                processToPrintNodesAtDistanceK(leftNode, distance, computedDistance + 1);
            }

            if (rightNode != null) {
                processToPrintNodesAtDistanceK(rightNode, distance, computedDistance + 1);
            }
        } else {
            System.out.print(node.data);
            System.out.print(" ");
        }
    }

    private static void processToPrepareAncestorList(Node node, Node target, List<Node> ancestorList) {
        if (node.data != target.data) {
            ancestorList.add(node);
            Node leftNode = node.left;
            Node rightNode = node.right;

            if (leftNode != null && !flag) {
                processToPrepareAncestorList(leftNode, target, ancestorList);
                if (!flag) {
                    ancestorList.remove(ancestorList.size() - 1);
                }
            }

            if (rightNode != null && !flag) {
                processToPrepareAncestorList(rightNode, target, ancestorList);
                if (!flag) {
                    ancestorList.remove(ancestorList.size() - 1);
                }
            }
        } else {
            flag = true;
        }
    }
}
