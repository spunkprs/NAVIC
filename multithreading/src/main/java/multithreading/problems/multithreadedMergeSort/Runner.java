package multithreading.problems.multithreadedMergeSort;

public class Runner {

    public static void main(String ar[]) throws InterruptedException {
        MultiThreadedMergeSort multithreadedMergeSort = new MultiThreadedMergeSort();
        int arr[] = {5, 9, 2, 1, 8};

        multithreadedMergeSort.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
