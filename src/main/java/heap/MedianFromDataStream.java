package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 Design a data structure that stores a dynamically changing list of integers and can find the median in
 constant time,O(1), as the list grows.
 To do that, implement a class named MedianOfStream with the following functionality :-

 1.) Constructor(): Initializes an instance of the class.

 2.) insertNum(int num): Adds a new integer num to the data structure.

 3.) findMedian(): Returns the median of all integers added so far.


 Note: The median is the middle value in a sorted list of integers.

 For an odd-sized list (e.g.,[4,5,6]), the median is the middle element: 5
 For an even-sized list (e.g.,[2,4,6,8]), the median is the average of the two middle elements: (4+6)/2=5

 Constraints:

 1.) −pow(10, 5) ≤ num ≤ pow(10,5), where num is an integer received from the data stream.
 2.) There will be at least one element in the data structure before the median is computed.
 3.) At most,500 calls will be made to the function that calculates the median.

 Source : Educative
 * */

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
