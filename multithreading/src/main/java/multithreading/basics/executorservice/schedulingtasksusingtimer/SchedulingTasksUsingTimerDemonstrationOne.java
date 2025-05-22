package multithreading.basics.executorservice.schedulingtasksusingtimer;

import java.util.Timer;
import java.util.TimerTask;

/*
This class aims at demonstrating that multiple tasks submitted to Timer has following potential issues :-

a.) If a task misbehaves and never terminates, all other tasks would not be executed

b.) If a task takes too long to execute, it can block timely execution of other tasks.
Say two tasks are submitted and the first is scheduled to execute after 100ms and the second is scheduled to execute after 500ms.
Now if the first task takes 5 minutes to execute then the second task would get delayed by 5 minutes rather than the intended 500ms.
{We are demonstrating this use case as part of this example}

c.) In the above example, if the second task is scheduled to run periodically after every 500ms, then when it finally gets a chance
to run after 5 minutes, it'll run for all the times it missed its turns, one after the other, without any delay between consecutive runs.

The achilles' heel of the Timer class is its use of a single thread to execute submitted tasks. Timer has a single worker thread that
attempts to execute all user submitted tasks

* */

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
