package multithreading.basics;

public class PrintNumberSeriesImplementation {

    static class PrintNumberSeries {
        private int n;
        private int number;
        private boolean printZero = true;

        public PrintNumberSeries(int n) {
            this.n = n;
        }

        public void printZero() {
            System.out.print("0");
        }

        public void printOdd() {
            System.out.print(number);
        }

        public void printEven() {
            System.out.print(number);
        }
    }

    public static void main(String ar[]) {

        PrintNumberSeries printNumberSeries = new PrintNumberSeries(5);

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
                                        printNumberSeries.wait();
                                    } else {
                                        flag = false;
                                        printNumberSeries.notifyAll();
                                    }
                                    printNumberSeries.number = printNumberSeries.number + 1;
                                    printNumberSeries.printZero = true;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
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
                                printNumberSeries.number = printNumberSeries.number + 1;
                                printNumberSeries.printZero = true;
                                try {
                                    printNumberSeries.wait();
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
    }
}
