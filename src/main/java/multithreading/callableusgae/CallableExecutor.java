package multithreading.callableusgae;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExecutor {

    /**
     * Awesome explanation of Futures:-
     * When the call() method completes, answer must be stored in an object known to the main thread,
     * so that the main thread can know about the result that the thread returned. How will the program
     * store and obtain this result later? For this, a Future object can be used. Think of a Future as an
     * object that holds the result – it may not hold it right now, but it will do so in
     * the future (once the Callable returns)
     *https://www.geeksforgeeks.org/callable-future-java/
     */

    public static void main(String ar[]) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Callable<List<String>> callableOne = () -> {List<String> list = new ArrayList<String>();
        System.out.println("Adding FIRST via callableOne");
        list.add("FIRST");
        return list;
        };

        Callable<List<String>> callableTwo = () -> {List<String> list = new ArrayList<String>();
            Thread.sleep(50000);
            System.out.println("Adding SECOND via callableTwo");
            list.add("SECOND");
            return list;
        };

        Callable<List<String>> callableThree = () -> {List<String> list = new ArrayList<String>();
            System.out.println("Adding THREE via callableThree");
            //list.add("THREE");
            throw new Exception("throwing Exception explicitly while processing callableThree");
            //return list;
        };

        List<Callable<List<String>>> callableTasks = new ArrayList<>();
        callableTasks.add(callableOne);
        callableTasks.add(callableTwo);
        callableTasks.add(callableThree);

        try {
            System.out.println("Going to submit all callables");

            List<Callable<List<String>>> callables = new ArrayList<>();
            callables.add(callableOne);
            callables.add(callableTwo);
            callables.add(callableThree);

            List<Future<List<String>>> futures = new ArrayList<>();
            callables.forEach( c-> {
                futures.add(executor.submit(c));
            });

            /*
            invokeAll is also a blocking call that basically means that it will wait for all the underlying callable tasks
            to complete if any of the task is taking long to complete further execution will be hampered till
            the completion of all the underlying callable tasks, this behaviour is not the same
            with submit(Callable task) method of the executor
            * */

            //List<Future<List<String>>> futureList = executor.invokeAll(callableTasks);
            System.out.println("All callables submitted");
            //futureList.get(2).get();
            futures.get(1).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
