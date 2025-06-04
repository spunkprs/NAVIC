package arrays.mergeInterval;

/**
Given two lists of closed intervals, intervalLista and intervalListb, return the intersection of the two interval lists.
Each interval in the lists has its own start and end time and is represented as [start, end].
Specifically:-

intervalLista[i] = [starti, endi]
intervalListb[j] = [startj, endj]

 The intersection of two closed intervals i and j is either:
 An empty set, if they do not overlap, or
 A closed interval [max(starti, startj), min(endi, endj)] if they do overlap

 Also, each list of intervals is pairwise disjoint and in sorted order.

 Constraints:-

 a.) 0 ≤ intervalLista.length, intervalListb.length ≤ 1000

 b.) intervalLista.length + intervalListb.length >= 1

 c.) 0 ≤ starti < endi ≤ pow(10,9)

 d.) endi < starti + 1

 e.) 0 ≤ startj < endj ≤ pow(10,9)

 f.) endj < startj + 1


Credits --> Educative
References --> https://www.educative.io/interview-prep/coding/interval-list-intersections
* */

public class IntervalListIntersections {

    public static void main(String ar[]) {
        IntervalListIntersections unit = new IntervalListIntersections();

        int inputIntervalsOne [][] = {{1, 4}, {5, 6}, {7, 8}};
        int inputIntervalsTwo [][] = {{3, 5}, {6, 7}, {8, 9}};

        unit.intervalsIntersection(inputIntervalsOne, inputIntervalsTwo);
    }

    public int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {

        if (intervalLista.length != 0 && intervalListb.length != 0) {
            logicToFindIntersections(intervalLista, intervalListb);
        }
        return new int[][]{};
    }

    private void logicToFindIntersections(int[][] inputIntervalsOne, int[][] inputIntervalsTwo) {
        Pair pairsOne[] = computePairs(inputIntervalsOne);
        Pair pairsTwo[] = computePairs(inputIntervalsTwo);


    }

    private Pair[] computePairs(int [][] inputInterval) {
        Pair pairs[] = new Pair[inputInterval.length];
        for (int i = 0; i < inputInterval.length; i++) {
             Pair pair = new Pair(inputInterval[i][0], inputInterval[i][1]);
             pairs[i] = pair;
        }
        return pairs;
    }

    static class Pair {
        private int x;
        private int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
