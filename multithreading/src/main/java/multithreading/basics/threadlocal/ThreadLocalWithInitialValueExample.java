package multithreading.basics.threadlocal;


/*
*
* All the basic information about ThreadLocal has already been documented in the example ThreadLocalBasicExample, go through it
* for any kind of revision/reference
*
* In this example we will be going through how initial values can be set inside ThreadLocal that will be same for each thread
* which is going to use it
*
* Following are the links that were referred to :-
    a.) https://www.baeldung.com/java-threadlocal
    b.) https://www.youtube.com/watch?v=a_BoqsnVR2U {Must watch}
* */

public class ThreadLocalWithInitialValueExample {

    public static void main(String ar[]) {

        ThreadLocal<String> threadLocal = ThreadLocal.withInitial( () -> {
           return "DEFAULT";
        });

        Thread threadOne = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Value fetched from ThreadLocal against Thread1 before modification :: " + threadLocal.get());
            threadLocal.set("Thread 1");
            System.out.println("Value fetched from ThreadLocal against Thread1 post modification :: " + threadLocal.get());
        });

        Thread threadTwo = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Value fetched from ThreadLocal against Thread2 before modification :: " + threadLocal.get());
            threadLocal.set("Thread 2");
            System.out.println("Value fetched from ThreadLocal against Thread2 post modification :: " + threadLocal.get());
        });

        threadOne.start();
        threadTwo.start();
    }

}
