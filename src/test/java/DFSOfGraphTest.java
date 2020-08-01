import graph.DFSOfGraph;
import org.junit.Test;

import java.util.ArrayList;

public class DFSOfGraphTest {

    @Test
    public void shouldPrintDFSOfGraphCaseOne() {
        ArrayList<Integer> listOne = new ArrayList<Integer>();
        listOne.add(1);
        listOne.add(2);
        listOne.add(3);

        ArrayList<Integer> listTwo = new ArrayList<Integer>();
        listTwo.add(0);

        ArrayList<Integer> listThree = new ArrayList<Integer>();
        listThree.add(0);
        listThree.add(4);

        ArrayList<Integer> listFour = new ArrayList<Integer>();
        listFour.add(0);

        ArrayList<Integer> listFive = new ArrayList<Integer>();
        listFive.add(2);

        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<ArrayList<Integer>>();

        adjacencyMatrix.add(listOne);
        adjacencyMatrix.add(listTwo);
        adjacencyMatrix.add(listThree);
        adjacencyMatrix.add(listFour);
        adjacencyMatrix.add(listFive);

        boolean visitedArray[] = {false, false, false, false, false};

        DFSOfGraph.dfs(0, adjacencyMatrix, visitedArray);

    }

    @Test
    public void shouldPrintDFSOfGraphCaseTwo() {
        ArrayList<Integer> listOne = new ArrayList<Integer>();
        listOne.add(1);
        listOne.add(2);
        listOne.add(3);

        ArrayList<Integer> listTwo = new ArrayList<Integer>();
        listTwo.add(0);
        listTwo.add(3);

        ArrayList<Integer> listThree = new ArrayList<Integer>();
        listThree.add(0);
        listThree.add(4);

        ArrayList<Integer> listFour = new ArrayList<Integer>();
        listFour.add(0);
        listFour.add(1);

        ArrayList<Integer> listFive = new ArrayList<Integer>();
        listFive.add(2);

        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<ArrayList<Integer>>();

        adjacencyMatrix.add(listOne);
        adjacencyMatrix.add(listTwo);
        adjacencyMatrix.add(listThree);
        adjacencyMatrix.add(listFour);
        adjacencyMatrix.add(listFive);

        boolean visitedArray[] = {false, false, false, false, false};

        DFSOfGraph.dfs(0, adjacencyMatrix, visitedArray);

    }
}
