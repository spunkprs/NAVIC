package multithreading;

import java.util.concurrent.locks.Lock;

public class TaskHavingLock implements Runnable {
	
	private int threadNumber;
	private int startingNumber;
	private int limit;
	private Lock lockingObject;
	private SharedNumber sharedNumber;
	
	public TaskHavingLock(int threadNumber, int startingNumber, int limit, Lock lockingObject, SharedNumber sharedNumber) {
		this.threadNumber = threadNumber;
		this.startingNumber = startingNumber;
		this.limit = limit;
		this.lockingObject = lockingObject;
		this.sharedNumber = sharedNumber;
	}

	@Override
	public void run() {
		
		int counter = 1;
		
		while (counter <= limit) {
			
			try {
				lockingObject.lock();
				if (threadNumber == sharedNumber.getSharedNumber()) {
					System.out.println("Printing number " + this.startingNumber + " by thread " + this.threadNumber);
					this.startingNumber += 3;
					counter++;
					if (sharedNumber.getSharedNumber() == 3) {
						sharedNumber.setSharedNumber(1);
					} else {
						sharedNumber.setSharedNumber(sharedNumber.getSharedNumber() + 1);
					}
				} 
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				lockingObject.unlock();
			}
		}
	}

}
