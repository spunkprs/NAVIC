package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFromDataStream {

    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public static void main(String ar[]) {
        MedianFromDataStream unit = new MedianFromDataStream();
    }

    public MedianFromDataStream() {
        this.maxHeap = new PriorityQueue<>(new MaxHeapComparator());
        this.minHeap = new PriorityQueue<>();
    }

    public void insertNum(int num) {
        // Write your code here
        if (this.maxHeap.isEmpty() && this.minHeap.isEmpty()) {
            maxHeap.add(num);
        } else {
            if (num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                maxHeap.add(num);
            }
            checkForImbalance();
        }
    }

    private void checkForImbalance() {
        if (Math.abs(this.minHeap.size() - this.maxHeap.size()) > 1) {
            if (this.minHeap.size() > this.maxHeap.size()) {
                int element = this.minHeap.poll();
                this.maxHeap.add(element);
            } else {
                int element = this.maxHeap.poll();
                this.minHeap.add(element);
            }
        }
    }

    public double findMedian() {
        int size = this.maxHeap.size() + this.minHeap.size();
        if (size % 2 == 0) {
            double peekOne = this.maxHeap.peek();
            double peekTwo = this.minHeap.peek();
            return (peekOne + peekTwo) / 2;
        } else {
            return this.maxHeap.size() > this.minHeap.size() ? this.maxHeap.peek() : this.minHeap.peek();
        }
    }

    static class MaxHeapComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.intValue() - o1.intValue();
        }
    }
}
