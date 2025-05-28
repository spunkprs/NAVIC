package multithreading.problems.customthreadpoolexecution;

public class CustomThreadPoolDemonstration {

    public static void main(String ar[]) {
        //Setting up custom thread pool
        CustomThreadPool customThreadPool = new CustomThreadPool(3, 8);

        //Setting up initial tasks
        Task taskOne = new Task("task1", 0);
        Task taskTwo = new Task("task2", 0);
        Task taskThree = new Task("task3", 100);
        Task taskFour = new Task("task4", 2000);
        Task taskFive = new Task("task5", 2000);
        Task taskSix = new Task("task6", 5000);
        Task taskSeven = new Task("task7", 200);
        Task taskEight = new Task("task8", 100);

        Task taskNine = new Task("task9", 2500);
        Task taskTen = new Task("task10", 5000);

        customThreadPool.submitTask(taskOne, taskOne.getTaskName());
        customThreadPool.submitTask(taskTwo, taskTwo.getTaskName());
        customThreadPool.submitTask(taskThree, taskThree.getTaskName());
        customThreadPool.submitTask(taskFour, taskFour.getTaskName());
        customThreadPool.submitTask(taskFive, taskFive.getTaskName());

        customThreadPool.submitTask(taskSix, taskSix.getTaskName());
        customThreadPool.submitTask(taskSeven, taskSeven.getTaskName());
        customThreadPool.submitTask(taskEight, taskEight.getTaskName());
        customThreadPool.submitTask(taskNine, taskNine.getTaskName());
        customThreadPool.submitTask(taskTen, taskTen.getTaskName());
    }
}
