package graph.numberofconnectedcomponents;

/*
Time complexity of this Algorithm is O(E) i.e number of edges, because this makes use of find && union approach
&& Space complexity is O(V) i.e number of vertices in the graph
Out of all the solutions submitted for this problem, this one does best both in the context of time && space complexity
* */
public class NumberOfConnectedComponentsUsingUnionAndFindApproach {

    private int connectedComponents = 0;

    public int countComponents(int n, int[][] edges) {
        connectedComponents = n;
        int parentArray[] = new int[n];
        int degreeArray[] = new int[n];
        for (int i = 0; i < n; i++) {
            parentArray[i] = i;
            degreeArray[i] = 1;
        }

        for (int i = 0; i < edges.length; i++) {
            performFindAndUnionOperation(i, edges, parentArray, degreeArray);
        }
        return connectedComponents;
    }

    private void performFindAndUnionOperation(int index, int edges[][], int parentArray[], int degreeArray[]) {
        int vertexOne = edges[index][0];
        int vertexTwo = edges[index][1];

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
            connectedComponents--;
        }
    }

    private int fetchParentIndex(int index, int parentArray[]) {
        while (parentArray[index] != index) {
            index = parentArray[index];
        }
        return index;
    }
}
