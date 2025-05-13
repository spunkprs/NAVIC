package multithreading.basics.semaphoreusage;


/*
*
You are given an instance of the class PrintNumberSeries that has three functions:-
a.) printZero
b.) printOdd
c.) printEven

The same instance of PrintNumberSeries will be passed to three different threads:

Thread A: calls printZero() that should only output 0's.
Thread B: calls printOdd() that should only output odd numbers.
Thread C: calls printEven() that should only output even numbers.

Modify the given class to output the series "010203040506..." where the length of the series must be 2n

This solution makes use of monitor/intrinsic lock on the object that's shared among three threads to handle critical section

Credits --> Educative
* */

import java.util.concurrent.Semaphore;

public class PrintNumberSeriesImplementation {

    static class PrintNumberSeries {
        private int n;
        private Semaphore zeroSem, oddSem, evenSem;

        public PrintNumberSeries(int n) {
            this.n = n;
            zeroSem = new Semaphore(1);
            oddSem = new Semaphore(0);
            evenSem = new Semaphore(0);
        }

        public void printZero() {
            for (int i = 0; i < n; ++i) {
                try {
                    zeroSem.acquire();
                }
                catch (Exception e) {
                }
                System.out.print("0");
                // release oddSem if i is even or else release evenSem if i is odd
                (i % 2 == 0 ? oddSem : evenSem).release();
            }
        }

        public void printEven() {
            for (int i = 2; i <= n; i += 2) {
                try {
                    evenSem.acquire();
                }
                catch (Exception e) {
                }
                System.out.print(i);
                zeroSem.release();
            }
        }

        public void printOdd() {
            for (int i = 1; i <= n; i += 2) {
                try {
                    oddSem.acquire();
                }
                catch (Exception e) {
                }
                System.out.print(i);
                zeroSem.release();
            }
        }
    }

    static class PrintNumberSeriesThread extends Thread {

        PrintNumberSeries zeo;
        String method;

        public PrintNumberSeriesThread(PrintNumberSeries zeo, String method){
            this.zeo = zeo;
            this.method = method;
        }

        public void run() {
            if ("zero".equals(method)) {
                try {
                    zeo.printZero();
                }
                catch (Exception e) {
                }
            }
            else if ("even".equals(method)) {
                try {
                    zeo.printEven();
                }
                catch (Exception e) {
                }
            }
            else if ("odd".equals(method)) {
                try {
                    zeo.printOdd();
                }
                catch (Exception e) {
                }
            }
        }
    }


    public static void main(String[] args) {

        PrintNumberSeries zeo = new PrintNumberSeries(6);

        Thread t1 = new PrintNumberSeriesThread(zeo,"zero");
        Thread t2 = new PrintNumberSeriesThread(zeo,"even");
        Thread t3 = new PrintNumberSeriesThread(zeo,"odd");

        t2.start();
        t1.start();
        t3.start();
    }

}
