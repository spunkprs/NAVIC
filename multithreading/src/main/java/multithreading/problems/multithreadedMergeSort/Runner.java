package multithreading.problems.multithreadedMergeSort;

public class Runner {

    public static void main(String ar[]) throws InterruptedException {
        MergeSort multithreadedMergeSort = new MergeSort();
        int arr[] = {5, 9, 2, 1, 8};

        multithreadedMergeSort.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
