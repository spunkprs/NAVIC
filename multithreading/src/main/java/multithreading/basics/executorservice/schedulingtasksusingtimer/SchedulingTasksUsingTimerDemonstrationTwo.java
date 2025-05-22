package multithreading.basics.executorservice.schedulingtasksusingtimer;

import java.util.Timer;
import java.util.TimerTask;

/*
This class aims at demonstrating that multiple tasks submitted to Timer has following potential issues :-

a.) If a task misbehaves and never terminates, all other tasks would not be executed

b.) If a task takes too long to execute, it can block timely execution of other tasks.
Say two tasks are submitted and the first is scheduled to execute after 100ms and the second is scheduled to execute after 500ms.
Now if the first task takes 5 minutes to execute then the second task would get delayed by 5 minutes rather than the intended 500ms.

c.) In the above example, if the second task is scheduled to run periodically after every 500ms, then when it finally gets a chance
to run after 5 minutes, it'll run for all the times it missed its turns, one after the other, without any delay between consecutive runs.

The achilles' heel of the Timer class is its use of a single thread to execute submitted tasks. Timer has a single worker thread that
attempts to execute all user submitted tasks

Demonstrating the case when exception is being raised during one of the task execution it will halt worker thread processing hence
remaining tasks will never gets executed

* */

public class SchedulingTasksUsingTimerDemonstrationTwo {

        public static void main( String args[] ) throws Exception{

            Timer timer = new Timer();
            TimerTask badTask = new TimerTask() {

                @Override
                public void run() {
                    throw new RuntimeException("Something Bad Happened");
                }
            };

            TimerTask goodTask = new TimerTask() {

                @Override
                public void run() {
                    System.out.println("Hello I am a well-behaved task");
                }
            };

            /*
            Important thing to notice here -->
            Exception raised by badTask impacted worker thread of Timer but that exception won't propagate to main thread, hence
            print statement inside catch block will never gets executed
            */

            try {
                timer.schedule(badTask, 10);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Exception raised by badTask caught here !! ");
            }


            /*
            This task will never gets executed by the lone worker thread of Timer, because bad task throws exception
            because of which worker thread halt it's processing && eventually goodTask won't be executed by it
            */
            timer.schedule(goodTask, 1000);

            //This statement will be printed
            System.out.println("Both the tasks have been submitted to the timer !!");
        }

}
