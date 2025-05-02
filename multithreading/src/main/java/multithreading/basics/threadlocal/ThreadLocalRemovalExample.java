package multithreading.basics.threadlocal;

/*
*
* All the basic information about ThreadLocal has already been documented in the example ThreadLocalBasicExample, go through it
* for any kind of revision/reference
*
* In this example we will be going through how values set inside ThreadLocal can be removed against each thread
*
* Following are the links that were referred to :-
    a.) https://www.baeldung.com/java-threadlocal
    b.) https://www.youtube.com/watch?v=a_BoqsnVR2U {Must watch}
* */

public class ThreadLocalRemovalExample {

    public static void main(String ar[]) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread threadOne = new Thread(() -> {
            threadLocal.set("Thread One");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Value fetched from ThreadLocal against Thread1 before removal :: " + threadLocal.get());
            threadLocal.remove();
            System.out.println("Value fetched from ThreadLocal against Thread1 post removal :: " + threadLocal.get());
        });

        Thread threadTwo = new Thread(() -> {
            threadLocal.set("Thread two");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Value fetched from ThreadLocal against Thread2 :: " + threadLocal.get());
        });

        threadOne.start();
        threadTwo.start();
    }
}
