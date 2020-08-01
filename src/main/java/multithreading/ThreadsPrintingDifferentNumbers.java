package multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadsPrintingDifferentNumbers {

	public static void main(String ar[]) {
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		AtomicInteger sharedNumber = new AtomicInteger(1);

		
		/*
		 * LockingObject lockingObject = new LockingObject(1);
		 * 
		 * Task taskOne = new Task(1, 1, 5, lockingObject); Task taskTwo = new Task(2,
		 * 2, 5, lockingObject); Task taskThree = new Task(3, 3, 5, lockingObject);
		 * 
		 * executorService.submit(taskOne); executorService.submit(taskTwo);
		 * executorService.submit(taskThree);
		 */
		 
		/*
		 * TaskRunner taskRunnerOne = new TaskRunner(1, 1, 5, sharedNumber); TaskRunner
		 * taskRunnerTwo = new TaskRunner(2, 2, 5, sharedNumber); TaskRunner
		 * taskRunnerThree = new TaskRunner(3, 3, 5, sharedNumber);
		 * 
		 * executorService.submit(taskRunnerOne); executorService.submit(taskRunnerTwo);
		 * executorService.submit(taskRunnerThree);
		 * 
		 * executorService.shutdown();
		 * 
		 * Lock lock = new ReentrantLock();
		 * 
		 * SharedNumber sharedNumberTwo = new SharedNumber(1);
		 */
		
		SharedNumber sharedNumberTwo = new SharedNumber(1);
		Lock lock = new ReentrantLock();
		
		TaskHavingLock taskHavingLockOne = new TaskHavingLock(1, 1, 5, lock, sharedNumberTwo);
		TaskHavingLock taskHavingLockTwo = new TaskHavingLock(2, 2, 5, lock, sharedNumberTwo);
		TaskHavingLock taskHavingLockThree = new TaskHavingLock(3, 3, 5, lock, sharedNumberTwo);
		
		executorService.submit(taskHavingLockOne);
		executorService.submit(taskHavingLockTwo);
		executorService.submit(taskHavingLockThree);
		
		executorService.shutdown();
		
	}

}
