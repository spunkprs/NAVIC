package multithreading.basics.executorservice.schedulingtasksusingtimer;

import java.util.Timer;
import java.util.TimerTask;

public class SchedulingTasksUsingTimerDemonstrationOne {

        public static void main( String args[] ) throws Exception {
            Timer timer = new Timer();
            TimerTask badTask = new TimerTask() {

                @Override
                public void run() {

                    // it will run forever
                    while (true)
                        ;

                }
            };

            TimerTask goodTask = new TimerTask() {

                @Override
                public void run() {
                    System.out.println("Hello I am a well-behaved task");
                }
            };

            //Task has been submitted to the single worker thread of Timer, it's picked by lone worker thread of Timer too for execution
            timer.schedule(badTask, 500);

            /*
            Task has been submitted to the single worker thread of Timer but it will never get executed because lone worker thread is
            stuck with the never ending bad task that's running in infinite loop
            */
            timer.schedule(goodTask, 1000);

            //This statement will be printed
            System.out.println("Both the tasks have been submitted to the timer !!");

            // By three seconds, both tasks are expected to have launched
            Thread.sleep(3000);
        }
}
