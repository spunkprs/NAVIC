package multithreading.basics.threadlocal;

/*
*
* The TheadLocal construct allows us to store data that will be accessible only by a specific thread
* Using it all the individual threads would be working on the values without any overlap, hence no possibilities of
* unexpected behaviour that arises in the multithreaded environment
*
* For the sake of simplicity we can think of ThreadLocal as a map where key is the individual thread && value is the data i.e
* value of the data on which that particular thread is working
*
* In this example we will be getting our hands dirty with some basic example of how ThreadLocal shall be used
* in the context of multithreaded environment
*
* Following are the links that were referred to :-
    a.) https://www.baeldung.com/java-threadlocal
    b.) https://www.youtube.com/watch?v=a_BoqsnVR2U {Must watch}
* */

public class ThreadLocalBasicExample {

    public static void main(String ar[]) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        Thread threadOne = new Thread(() -> {
            threadLocal.set("Thread One");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Value fetched from ThreadLocal against Thread1 :: " + threadLocal.get());
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
