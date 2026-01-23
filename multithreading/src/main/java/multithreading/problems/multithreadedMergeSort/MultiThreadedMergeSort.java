package multithreading.problems.multithreadedMergeSort;

/**
As we all know merge sort is a typical divide && conquer problem, but in a conventional merge sort algorithm division of array && finally merge part both of
them done in the single thread.
With this solution part post array is divided into halves, process post that is being taken by individual threads instead, definitely this approach is a time saver
but that also depends on the number of available cores assigned to JVM of the machine on which this algorithm is running[as we all know caveats around concurrency]

Another important learning here is the usage of join() method which is used to dictate order in which we want to perform execution of threads

Reference --> https://www.educative.io/courses/java-multithreading-for-senior-engineering-interviews/multithreaded-merge-sort
 * */

public class MultiThreadedMergeSort {

    private int tempArr[];

    public void sort(int arr[]) throws InterruptedException {
        tempArr = new int[arr.length];
        int startIndex = 0;
        int endIndex = arr.length - 1;
        int midIndex = (startIndex + endIndex) / 2;

        Thread tOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sortProcess(arr, startIndex, midIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread tTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sortProcess(arr, midIndex + 1, endIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        tOne.start();
        tTwo.start();

        tOne.join();
        tTwo.join();

        mergePart(arr, startIndex, endIndex);
    }

    private void sortProcess(int[] arr, int startIndex, int endIndex) throws InterruptedException {

        if (startIndex == endIndex) {
            return;
        }

        int midIndex = (startIndex + endIndex) / 2;

        Thread tOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sortProcess(arr, startIndex, midIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread tTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sortProcess(arr, midIndex + 1, endIndex);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        tOne.start();
        tTwo.start();

        tOne.join();
        tTwo.join();

        mergePart(arr, startIndex, endIndex);
    }

    private void mergePart(int[] arr, int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;
        int k = startIndex;

        while (k <= endIndex) {
            tempArr[k] = arr[k];
            k++;
        }

        int i = startIndex;
        int j = midIndex + 1;

        k = startIndex;

        while (k <= endIndex) {
            if (i <= midIndex && j <= endIndex) {
                if (tempArr[i] < tempArr[j]) {
                    arr[k] = tempArr[i];
                    i++;
                } else if (tempArr[j] < tempArr[i]) {
                    arr[k] = tempArr[j];
                    j++;
                } else {
                    arr[k] = tempArr[i];
                    i++;
                    j++;
                }
            } else if (i <= midIndex && j > endIndex) {
                arr[k] = tempArr[i];
                i++;
            } else if (i > midIndex && j <= endIndex) {
                arr[k] = tempArr[j];
                j++;
            }
            k++;
        }
    }
}
