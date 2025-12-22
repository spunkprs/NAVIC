package leetcode75;

import java.util.*;

public class FindKPairsWithSmallestSum {

    public static void main(String ar[]) {
        FindKPairsWithSmallestSum unit = new FindKPairsWithSmallestSum();
        int numsOne[] = {1,7,11};
        int numsTwo[] = {2,4,6};

        //int numsOne[] = {1,1,2};
        //int numsTwo[] = {1,2,3};
        int k = 3;
        //int k = 2;
        List<List<Integer>> result = unit.kSmallestPairs(numsOne, numsTwo, k);
        for (List<Integer> pair : result) {
            System.out.println(pair);
        }
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        return processToFindKSmallestPairs(nums1, nums2, k);
    }

    private List<List<Integer>> processToFindKSmallestPairs(int[] nums1, int[] nums2, int k) {
        int count = 1;
        List<Pair> result = new ArrayList<>();
        Pair firstPair = new Pair(0, 0, nums1, nums2);

        Set<Pair> visitedIndexes = new HashSet<>();
        PriorityQueue<Pair> minHeap = new PriorityQueue<>(new SumComparator<>());

        visitedIndexes.add(firstPair);
        minHeap.add(firstPair);

        while (count <= k && !minHeap.isEmpty()) {
            Pair poppedPair = minHeap.poll();
            addToResult(poppedPair, result);
            pushAdjacentPairs(poppedPair, minHeap, nums1, nums2, visitedIndexes);
            count++;
        }
        return transform(result);
    }

    private List<List<Integer>> transform(List<Pair> result) {
        List<List<Integer>> finalResult = new ArrayList<>();
        for (Pair pair : result) {
            List<Integer> intermittentResult = new ArrayList<>();
            intermittentResult.add(pair.numOne[pair.indexOne]);
            intermittentResult.add(pair.numTwo[pair.indexTwo]);
            finalResult.add(intermittentResult);
        }
        return finalResult;
    }

    private void addToResult(Pair poppedPair, List<Pair> result) {
        result.add(poppedPair);
    }

    private void pushAdjacentPairs(Pair poppedPair, PriorityQueue<Pair> minHeap, int[] nums1, int[] nums2,
                                   Set<Pair> visitedIndexes) {
        Pair one = new Pair(poppedPair.indexOne, poppedPair.indexTwo + 1);
        Pair two = new Pair(poppedPair.indexOne + 1, poppedPair.indexTwo);

        if (one.indexTwo < nums2.length && !visitedIndexes.contains(one)) {
            one.setSum(nums1, nums2);
            minHeap.add(one);
            visitedIndexes.add(one);
        }

        if (two.indexOne < nums1.length && !visitedIndexes.contains(two)) {
            two.setSum(nums1, nums2);
            minHeap.add(two);
            visitedIndexes.add(two);
        }
    }

    static class SumComparator<Pair> implements Comparator<FindKPairsWithSmallestSum.Pair> {

        @Override
        public int compare(FindKPairsWithSmallestSum.Pair objOne, FindKPairsWithSmallestSum.Pair objTwo) {
            return objOne.getSum() < objTwo.getSum() ? -1 : objOne.getSum() > objTwo.getSum() ? 1 : 0;
        }
    }

    static class Pair {
        private int indexOne;
        private int indexTwo;
        private int sum;
        private int numOne[];
        private int numTwo[];

        public Pair(int indexOne, int indexTwo) {
            this.indexOne = indexOne;
            this.indexTwo = indexTwo;
        }

        public Pair(int indexOne, int indexTwo, int numOne[], int numTwo[]) {
            this.indexOne = indexOne;
            this.indexTwo = indexTwo;
            this.numOne = numOne;
            this.numTwo = numTwo;
            this.sum = numOne[indexOne] + numTwo[indexTwo];
        }


        public int getSum() {
            return sum;
        }

        public void setSum(int numOne[], int numTwo[]) {
            this.numOne = numOne;
            this.numTwo = numTwo;
            this.sum = numOne[indexOne] + numTwo[indexTwo];
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return indexOne == pair.indexOne && indexTwo == pair.indexTwo;
        }

        @Override
        public int hashCode() {
            return Objects.hash(indexOne, indexTwo);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "indexOne=" + numOne[indexOne] +
                    ", indexTwo=" + numTwo[indexTwo] +
                    '}';
        }
    }
}
