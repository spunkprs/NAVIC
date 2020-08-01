package binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LevelOrder {

    public ArrayList<ArrayList<Integer>> levelOrder(TreeNode A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int currentLevel = 1;
        Queue<Pair> queue = new LinkedList<Pair>();
        Pair root = new Pair(A, 1);
        queue.add(root);
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (list.isEmpty()) {
                list.add(p.node.val);
                result.add(list);
            } else {
                if (currentLevel != p.depth) {
                    list = new ArrayList<Integer>();
                    list.add(p.node.val);
                    currentLevel++;
                    result.add(list);
                } else {
                    list.add(p.node.val);
                }
            }
            if (p.node.left != null) {
                queue.add(new Pair(p.node.left, p.depth + 1));
            }

            if (p.node.right != null) {
                queue.add(new Pair(p.node.right, p.depth + 1));
            }
        }
        return result;
    }


    class Pair {
        TreeNode node;
        int depth;

        Pair(TreeNode treeNode, int depth) {
            this.node = treeNode;
            this.depth = depth;
        }
    }

}


