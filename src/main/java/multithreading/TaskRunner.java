package multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class TaskRunner implements Runnable {
	
	private int threadNumber;
	private int startingNumber;
	private int limit;
	private AtomicInteger sharedNumber;
	
	public TaskRunner(int threadNumber, int startingNumber, int limit, AtomicInteger sharedNumber) {
		this.startingNumber = startingNumber;
		this.threadNumber = threadNumber;
		this.limit = limit;
		this.sharedNumber = sharedNumber;
	}
	
	@Override
	public void run() {
		
		int counter = 1;
		
		while (counter <= this.limit) {
			try {
					if (this.sharedNumber.get() == this.threadNumber) {
						System.out.println("Task Runner Number" + " " + this.threadNumber +" "+ "printing number" + " " + this.startingNumber);
						counter++;
						this.startingNumber += 3;
						this.sharedNumber.set(this.sharedNumber.get() + 1);
						if (this.sharedNumber.get() > 3) {
							this.sharedNumber.set(1);
						}
					} 
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
