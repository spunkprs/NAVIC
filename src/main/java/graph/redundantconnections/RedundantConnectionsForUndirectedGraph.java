package graph.redundantconnections;

/*
This problem aims at finding that edge in the graph which is causing loop in the graph and it's removal will make this graph a tree
i.e Only single loop is bound to be present in the graph
Vertices are 1 based
Time Complexity = O(E)
Space Complexity = 2 * O(V)
* */
public class RedundantConnectionsForUndirectedGraph {

    private int resultArray [] = new int[2];

    public int[] findRedundantConnection(int[][] edges) {
        int parentArray[] = new int[edges.length];
        int degreeArray[] = new int[edges.length];

        for (int i = 0; i < edges.length; i++) {
            parentArray[i] = i;
            degreeArray[i] = 1;
        }

        for (int i = 0; i < edges.length; i++) {
            performFindAndUnionOperation(i, edges, parentArray, degreeArray);
        }

        return resultArray;
    }

    private void performFindAndUnionOperation(int index, int edges[][], int parentArray[], int degreeArray[]) {
        int vertexOne = edges[index][0] - 1;
        int vertexTwo = edges[index][1] - 1;

        int parentVertexOne = fetchParentIndex(vertexOne, parentArray);
        int parentVertexTwo = fetchParentIndex(vertexTwo, parentArray);

        if (parentVertexOne != parentVertexTwo) {
            if (degreeArray[parentVertexOne] >= degreeArray[parentVertexTwo]) {
                degreeArray[parentVertexOne] += degreeArray[parentVertexTwo];
                parentArray[parentVertexTwo] = parentVertexOne;
            } else {
                degreeArray[parentVertexTwo] += degreeArray[parentVertexOne];
                parentArray[parentVertexOne] = parentVertexTwo;
            }
        } else {
            resultArray[0] = vertexOne + 1;
            resultArray[1] = vertexTwo + 1;
        }
    }

    private int fetchParentIndex(int index, int parentArray[]) {
        while (parentArray[index] != index) {
            index = parentArray[index];
        }
        return index;
    }

}
