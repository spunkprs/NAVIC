package multithreading.problems.multithreadedMergeSort;

public class MergeSort  {

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
