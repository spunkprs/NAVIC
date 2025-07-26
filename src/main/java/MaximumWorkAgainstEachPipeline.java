/**
This problem was asked in the assignment of Salesforce, I was able to code it but somehow all the test cases were failing

This problem aims at summation of max number in a window * size of window, if there exists multiple windows then we need to do this
exercise multiple times

Basically we need to fins maximum value present in each window * size of window

By intuition it's pretty clear that we need to make use of monotonic queue

This implementation is working as expected !!

Let's have a look into Dequeue and it's associated methods as here I made use of custom build doubly linked list and make it perform like
monotonically decreasing queue
 * */


public class MaximumWorkAgainstEachPipeline {

    public static void main(String ar[]) {

        MaximumWorkAgainstEachPipeline unit = new MaximumWorkAgainstEachPipeline();

        int worksArr[] = {4, 6, 1, 8};
        //int worksArr[] = {2, 3, 4};

        System.out.println("Maximum work against all the pipelines is : " + unit.fetchMaximumWorkAgainstAllThePipelines(worksArr));
    }


    public int fetchMaximumWorkAgainstAllThePipelines(int workArr[]) {
        int summedValue = 0;

        for (int i = 0; i < workArr.length; i++) {
            summedValue += workArr[i];
        }

        for (int j = 2; j <= workArr.length ; j++) {
            summedValue += processToFindMaxWorkAgainstEachSubArray(j, workArr);
        }

        return summedValue;
    }

    private int processToFindMaxWorkAgainstEachSubArray(int size, int[] workArr) {
        int summedValue = 0;

        Node head = null;
        Node tail = null;

        for (int i = 0; i < workArr.length; i++) {
            if (head == null) {
                head = new Node(workArr[i], i);
                tail = head;
            } else {
                Node node = new Node(workArr[i], i);
                if (node.value < tail.value) {
                    tail.next = node;
                    node.previous = tail;
                    tail = node;
                    if (tail.index - head.index  + 1 > size) {
                        head = head.next;
                    }
                } else {
                    while (tail != null && node.value >= tail.value) {
                        tail = tail.previous;
                    }
                    if (tail == null) {
                        tail = node;
                        head = tail;
                    } else {
                        tail.next = node;
                        node.previous = tail;
                        tail = node;
                    }
                }
            }

            if (tail.index + 1 >= size) {
                summedValue += head.value * size;
            }
        }
        return summedValue;
    }


    static class Node {
        private int value;
        private int index;
        private Node next;
        private Node previous;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}
