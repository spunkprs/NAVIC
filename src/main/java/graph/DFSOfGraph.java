package graph;

import java.util.ArrayList;

public class DFSOfGraph {

    public static void dfs(int src, ArrayList<ArrayList<Integer>> list, boolean vis[])
    {
        traverseDFSInGraph(list, src, vis);
    }

    private static void traverseDFSInGraph(ArrayList<ArrayList<Integer>> list, int src, boolean[] vis) {
        if (!vis[src]) {
                    vis[src] = true;
                    System.out.print(src);
                    System.out.print(" ");
                    for (int j = 0; j < list.get(src).size(); j++) {
                        traverseDFSInGraph(list, list.get(src).get(j), vis);
                    }
        }

    }
}
