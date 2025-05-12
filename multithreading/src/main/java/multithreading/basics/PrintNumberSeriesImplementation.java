package multithreading.basics;


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

Modify the given class to output the series "010203040506..." where the length of the series must be 2n.
* */

public class PrintNumberSeriesImplementation {

    static class PrintNumberSeries {
        private int n;
        private int number;
        private boolean printZero;

        public PrintNumberSeries(int n) {
            this.n = n;
        }

        public void printZero() {
            System.out.print(0);
        }

        public void printOdd() {
            System.out.print(number);
        }

        public void printEven() {
            System.out.print(number);
        }
    }

    public static void main(String ar[]) {

        PrintNumberSeries printNumberSeries = new PrintNumberSeries(4);
        printNumberSeries.printZero = true;
        printNumberSeries.number = 1;

        Thread printZeroThread = new Thread(new Runnable() {

            @Override
            public void run() {
                boolean flag = true;
                while (flag) {
                    synchronized (printNumberSeries) {
                        if (printNumberSeries.number <= printNumberSeries.n) {
                            if (printNumberSeries.printZero) {
                                printNumberSeries.printZero();
                                printNumberSeries.printZero = false;
                                try {
                                    if (printNumberSeries.number < printNumberSeries.n) {
                                        printNumberSeries.wait();
                                    } else {
                                        flag = false;
                                        printNumberSeries.notifyAll();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                printNumberSeries.notifyAll();
                            }
                        }
                    }
                }
            }
        });

        Thread printOddNumThread = new Thread(new Runnable() {

            @Override
            public void run() {
                boolean flag = true;
                while (flag) {
                    synchronized (printNumberSeries) {
                        if (!printNumberSeries.printZero) {
                            if (printNumberSeries.number <= printNumberSeries.n && printNumberSeries.number % 2 != 0) {
                                printNumberSeries.printOdd();
                                try {
                                    if (printNumberSeries.number < printNumberSeries.n) {
                                        printNumberSeries.number = printNumberSeries.number + 1;
                                        printNumberSeries.printZero = true;
                                        printNumberSeries.wait();
                                    } else {
                                        flag = false;
                                        printNumberSeries.number = printNumberSeries.number + 1;
                                        printNumberSeries.notifyAll();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else if (printNumberSeries.number > printNumberSeries.n) {
                                flag = false;
                            }
                        } else {
                            printNumberSeries.notifyAll();
                        }
                    }
                }
            }
        });

        Thread printEvenNumThread = new Thread(new Runnable() {

            @Override
            public void run() {

                boolean flag = true;
                while (flag) {
                    synchronized (printNumberSeries) {
                        if (!printNumberSeries.printZero) {
                            if (printNumberSeries.number <= printNumberSeries.n && printNumberSeries.number % 2 == 0) {
                                printNumberSeries.printEven();
                                try {
                                    if (printNumberSeries.number < printNumberSeries.n) {
                                        printNumberSeries.number = printNumberSeries.number + 1;
                                        printNumberSeries.printZero = true;
                                        printNumberSeries.wait();
                                    } else {
                                        flag = false;
                                        printNumberSeries.number = printNumberSeries.number + 1;
                                        printNumberSeries.notifyAll();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else if (printNumberSeries.number > printNumberSeries.n) {
                                flag = false;
                            }
                        } else {
                            printNumberSeries.notifyAll();
                        }
                    }
                }
            }
        });


        //Start the individual threads
        printZeroThread.start();
        printOddNumThread.start();
        printEvenNumThread.start();

        try {
            printZeroThread.join();
            printOddNumThread.join();
            printEvenNumThread.join();
        } catch (InterruptedException e) {
            System.out.print("Exception thrown inside main thread while for individual threads to complete !!");
            e.printStackTrace();
        }
    }
}
