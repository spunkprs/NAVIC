package multithreading;


public class Task implements Runnable {
	
	private int threadNumber;
	private int startingNumber;
	private int limit;
	private LockingObject lockingObject;
	
	public Task(int threadNumber, int startingNumber, int limit, LockingObject lockingObject) {
		this.startingNumber = startingNumber;
		this.threadNumber = threadNumber;
		this.limit = limit;
		this.lockingObject = lockingObject;		
	}

	@Override
	public void run() {
		
		int counter = 1;
		
		while (counter <= this.limit) {
			try {
				synchronized (this.lockingObject) {
					if (this.lockingObject.getSharingNumber() == this.threadNumber) {
						System.out.println("Task Number" + " " + this.threadNumber +" "+ "printing number" + " " + this.startingNumber);
						counter++;
						this.startingNumber += 3;
						int sharedNumber = this.lockingObject.getSharingNumber();
						sharedNumber++;
						if (sharedNumber > 3) {
							sharedNumber = 1;
						}
						this.lockingObject.setSharingNumber(sharedNumber);
						this.lockingObject.notifyAll();
					} else {
						this.lockingObject.wait();
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
