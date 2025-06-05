package arrays.mergeInterval;

import java.util.ArrayList;
import java.util.List;

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

        int inputIntervalsOne [][] = {{1, 4}, {5, 6}, {7, 9}};
        int inputIntervalsTwo [][] = {{3, 5}, {6, 7}, {8, 9}};

        /*int inputIntervalsOne [][] = {{0, 4}, {5, 7}, {8, 12}, {13, 15}, {16, 18}};
        int inputIntervalsTwo [][] = {{0, 18}};*/

        int result[][] = unit.intervalsIntersection(inputIntervalsOne, inputIntervalsTwo);

        printResult(result);
    }

    private static void printResult(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i][0]);
            System.out.print(" ");
            System.out.print(result[i][1]);
            System.out.println();
        }
    }

    public int[][] intervalsIntersection(int[][] intervalLista, int[][] intervalListb) {

        if (intervalLista.length != 0 && intervalListb.length != 0) {
            List<Pair> resultList = logicToFindIntersections(intervalLista, intervalListb);
            return finalResultConversionProcess(resultList);
        }
        return new int[][]{};
    }

    private int[][] finalResultConversionProcess(List<Pair> resultList) {
        int result[][] = new int[resultList.size()][2];
        for (int i = 0; i < resultList.size(); i++) {
            result[i][0] = resultList.get(i).startTime;
            result[i][1] = resultList.get(i).endTime;
        }
        return result;
    }

    /**
    This method is the heart of the problem where entire logic of overlap checking is being done using 2 pointer approach
    * */

    private List<Pair> logicToFindIntersections(int[][] inputIntervalsOne, int[][] inputIntervalsTwo) {
        Pair pairsOne[] = computePairs(inputIntervalsOne);
        Pair pairsTwo[] = computePairs(inputIntervalsTwo);

        List<Pair> result = new ArrayList<>();

        int i = 0, j = 0;

        while (i < pairsOne.length && j < pairsTwo.length) {
            if (pairsTwo[j].startTime > pairsOne[i].endTime) {
                //In this case intervals won't be merged because there is no overlap
                i++;
            } else if (pairsTwo[j].startTime <= pairsOne[i].endTime && pairsTwo[j].startTime >= pairsOne[i].startTime) {
                if (pairsTwo[j].endTime > pairsOne[i].endTime) {
                    updateResult(pairsTwo[j], pairsOne[i], result);
                    i++;
                    //i pointer got progressed because pairsTwo[j].endTime > pairsOne[i].endTime
                } else if (pairsTwo[j].endTime == pairsOne[i].endTime) {
                    updateResult(pairsTwo[j], pairsOne[i], result);
                    i++;
                    j++;
                    //Because of the same end times both the pointers got progressed
                } else if (pairsTwo[j].endTime < pairsOne[i].endTime) {
                    updateResult(pairsTwo[j], pairsOne[i], result);
                    j++;
                    //j pointer got progressed because pairsTwo[j].endTime < pairsOne[i].endTime
                }
            } else if (pairsTwo[j].startTime == pairsOne[i].startTime && pairsTwo[j].endTime == pairsOne[i].endTime) {
                updateResult(pairsTwo[j], pairsOne[i], result);
                i++;
                j++;
                //Because of the same start && end times both the pointers got progressed
            }  else if (pairsOne[i].startTime > pairsTwo[j].endTime) {
                //In this case intervals won't be merged because there is no overlap
                j++;
            } else if (pairsTwo[j].startTime <= pairsOne[i].endTime && pairsTwo[j].startTime < pairsOne[i].startTime) {
                if (pairsTwo[j].endTime < pairsOne[i].endTime) {
                    updateResult(pairsTwo[j], pairsOne[i], result);
                    j++;
                    //j pointer got progressed because pairsTwo[j].endTime < pairsOne[i].endTime
                } else if (pairsTwo[j].endTime == pairsOne[i].endTime) {
                    updateResult(pairsTwo[j], pairsOne[i], result);
                    i++;
                    j++;
                    //Because of the same end times both the pointers got progressed
                } else if (pairsTwo[j].endTime > pairsOne[i].endTime) {
                    updateResult(pairsTwo[j], pairsOne[i], result);
                    i++;
                    //i pointer got progressed because pairsTwo[j].endTime > pairsOne[i].endTime
                }
            }
        }
        return result;
    }

    //This method is used to perform overlap operation as expected in the problem
    private void updateResult(Pair pTwo, Pair pOne, List<Pair> result) {
        result.add(new Pair(Math.max(pTwo.startTime, pOne.startTime),
                Math.min(pTwo.endTime, pOne.endTime)));
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
        private int startTime;
        private int endTime;

        public Pair(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

}
