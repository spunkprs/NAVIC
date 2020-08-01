package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSOfGraph {

    public static void bfs(int s, ArrayList<ArrayList<Integer>> list, boolean vis[], int nov)
    {
        Queue<Integer> queue = new LinkedList<Integer>();
        System.out.print(s);
        System.out.print(" ");
        vis[s] = true;

        ArrayList<Integer> children = list.get(s);
        for (int i = 0; i < children.size(); i++) {
            if (!vis[children.get(i)]) {
                if (!vis[children.get(i)]) {
                    vis[children.get(i)] = true;
                }
                queue.add(children.get(i));
            }
        }

        while (!queue.isEmpty()) {
            children = list.get(queue.peek());
            for (int i = 0; i < children.size(); i++) {
                if (!vis[children.get(i)]) {
                    queue.add(children.get(i));
                    vis[children.get(i)] = true;
                }
            }
            int top = queue.poll();
            vis[top] = true;
            System.out.print(top);
            System.out.print(" ");
        }
    }
}
