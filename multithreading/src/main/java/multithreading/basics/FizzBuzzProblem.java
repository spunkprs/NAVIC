package multithreading.basics;


/*
Problem in which a program prints a number series from 1 to n such that for
every number that is a multiple of 3 it prints “fizz”,
for every number that is a multiple of 5 it prints “buzz”
and for every number that is a multiple of both 3 and 5 it prints “fizzbuzz”
Suppose we have four threads t1, t2, t3 and t4.
Thread t1 checks if the number is divisible by 3 and prints fizz
Thread t2 checks if the number is divisible by 5 and prints buzz
Thread t3 checks if the number is divisible by both 3 and 5 and prints fizzbuzz
Thread t4 prints numbers that are not divisible by 3 or 5

Constraints :-
1 <= maxNum <= 1000

For example, for maxNum = 15, the output should be: 12fizz4buzzfizz78fizzbuzz11fizz1314fizzbuzz.

* */



public class FizzBuzzProblem {

    public static void main(String ar[]) {

        FizzBuzz lockObject = new FizzBuzz();
        lockObject.counter = 1;
        int maxNum = 15;

        Thread t1 = new Thread(new FizzBuzzThreadOne(maxNum, lockObject));
        Thread t2 = new Thread(new FizzBuzzThreadTwo(maxNum, lockObject));
        Thread t3 = new Thread(new FizzBuzzThreadThree(maxNum, lockObject));
        Thread t4 = new Thread(new FizzBuzzThreadFour(maxNum, lockObject));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class FizzBuzz {

        private int counter;

        public void fizz() {
            System.out.print("fizz");
        }

        public void buzz() {
            System.out.print("buzz");
        }

        public void fizzbuzz() {
            System.out.print("fizzbuzz");
        }

        public void number(int num) {
            System.out.print(num);
        }
    }

    static class FizzBuzzThreadOne implements Runnable {

        private int maxNum;
        private FizzBuzz monitor;

        public FizzBuzzThreadOne(int maxNum, FizzBuzz monitor) {
            this.maxNum = maxNum;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            boolean flag = true;

            while (flag) {
                synchronized (this.monitor) {
                    if (this.monitor.counter <= maxNum) {
                        if (this.monitor.counter % 3 != 0) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (this.monitor.counter % 3 == 0 && this.monitor.counter % 5 != 0) {
                            this.monitor.fizz();
                            this.monitor.counter = this.monitor.counter + 1;
                            this.monitor.notifyAll();
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
    }

    static class FizzBuzzThreadTwo implements Runnable {

        private int maxNum;
        private FizzBuzz monitor;

        public FizzBuzzThreadTwo(int maxNum, FizzBuzz monitor) {
            this.maxNum = maxNum;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            boolean flag = true;

            while (flag) {
                synchronized (this.monitor) {
                    if (this.monitor.counter <= maxNum) {
                        if (this.monitor.counter % 5 != 0) {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else if (this.monitor.counter % 5 == 0 && this.monitor.counter % 3 != 0) {
                            this.monitor.buzz();
                            this.monitor.counter = this.monitor.counter + 1;
                            this.monitor.notifyAll();
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
    }

    static class FizzBuzzThreadThree implements Runnable {

        private int maxNum;
        private FizzBuzz monitor;

        public FizzBuzzThreadThree(int maxNum, FizzBuzz monitor) {
            this.maxNum = maxNum;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            boolean flag = true;

            while (flag) {
                synchronized (this.monitor) {
                    if (this.monitor.counter <= maxNum) {
                        if (this.monitor.counter % 3 == 0 && this.monitor.counter % 5 == 0) {
                            this.monitor.fizzbuzz();
                            this.monitor.counter = this.monitor.counter + 1;
                            this.monitor.notifyAll();
                        } else {
                            try {
                                monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
    }

    static class FizzBuzzThreadFour implements Runnable {

        private int maxNum;
        private FizzBuzz monitor;

        public FizzBuzzThreadFour(int maxNum, FizzBuzz monitor) {
            this.maxNum = maxNum;
            this.monitor = monitor;
        }

        @Override
        public void run() {
            boolean flag = true;

            while (flag) {
                synchronized (this.monitor) {
                    if (this.monitor.counter <= maxNum) {
                        if (this.monitor.counter % 5 != 0 && this.monitor.counter % 3 != 0) {
                            this.monitor.number(this.monitor.counter);
                            this.monitor.counter = this.monitor.counter + 1;
                            this.monitor.notifyAll();
                        } else {
                            try {
                                this.monitor.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        flag = false;
                    }
                }
            }
        }
    }
}
