package multithreading.basics;


/*
This class aims at printing foo && bar alternatively n number of times
Responsibility of printing foo && bar is given to individual threads

Constraints :-
1 <= n <= 1000

Made sure to make use of java monitor pattern to get a hold of the problem

* */

public class FooBarAlternatively {

    public static void main(String ar[]) {

        FooBar fooBar = new FooBar(5);

        Thread t1 = new Thread(() -> {
            try {
                fooBar.foo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                fooBar.bar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class FooBar {
        private int n;
        private int sharedNumber;

        public FooBar(int n) {
            this.n = n;
            this.sharedNumber = 1;
        }

        public void foo() throws InterruptedException {

            for (int i = 0; i < this.n; i++) {
                try {
                    synchronized (this) {
                        while(this.sharedNumber == 2) {
                            this.wait();
                        }
                        System.out.print("foo");
                        this.sharedNumber = 2;
                        this.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void bar() throws InterruptedException {

            for (int i = 0; i < this.n; i++) {
                try {
                    synchronized (this) {
                        while(this.sharedNumber == 1) {
                            this.wait();
                        }
                        System.out.print("bar");
                        this.sharedNumber = 1;
                        this.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
