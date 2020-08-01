import graph.BFSOfGraph;
import org.junit.Test;

import java.util.ArrayList;

public class BFSOfGraphTest {

    @Test
    public void shouldDoBFSOFGraphCaseOne() {
        ArrayList<Integer> listOne = new ArrayList<Integer>();
        listOne.add(1);
        listOne.add(2);
        listOne.add(3);

        ArrayList<Integer> listTwo = new ArrayList<Integer>();

        ArrayList<Integer> listThree = new ArrayList<Integer>();
        listThree.add(4);

        ArrayList<Integer> listFour = new ArrayList<Integer>();

        ArrayList<Integer> listFive = new ArrayList<Integer>();

        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<ArrayList<Integer>>();

        adjacencyMatrix.add(listOne);
        adjacencyMatrix.add(listTwo);
        adjacencyMatrix.add(listThree);
        adjacencyMatrix.add(listFour);
        adjacencyMatrix.add(listFive);

        boolean visitedArray[] = {false, false, false, false, false};

        BFSOfGraph.bfs(0, adjacencyMatrix, visitedArray, 5);
    }
}
