package multithreading.callableusgae;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExecutor {

    public static void main(String ar[]) {

        ExecutorService executor = Executors.newFixedThreadPool(4);

        Callable<List<String>> callableOne = () -> {List<String> list = new ArrayList<String>();
        System.out.println("Adding FIRST via callableOne");
        list.add("FIRST");
        return list;
        };

        Callable<List<String>> callableTwo = () -> {List<String> list = new ArrayList<String>();
            System.out.println("Adding SECOND via callableTwo");
            list.add("SECOND");
            Thread.sleep(5000);
            return list;
        };

        Callable<List<String>> callableThree = () -> {List<String> list = new ArrayList<String>();
            System.out.println("Adding THREE via callableThree");
            //list.add("THREE");
            throw new Exception("exception");
            //return list;
        };

        List<Callable<List<String>>> callableTasks = new ArrayList<>();
        callableTasks.add(callableOne);
        callableTasks.add(callableTwo);
        callableTasks.add(callableThree);

        try {
            List<Future<List<String>>> futureList = executor.invokeAll(callableTasks);
            futureList.get(2).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
