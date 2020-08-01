package multithreading;

import java.util.LinkedList;

public class ConsumerProcess implements Runnable {

	private LinkedList<Integer> list;
	private Object lock;
	
	public ConsumerProcess(LinkedList<Integer> list, Object lock) {
		this.lock = lock;
		this.list = list;
	}
	
	@Override
	public void run() {
		
		while (true) {
			synchronized (lock) {
				
				try {
					if (list.size() == 0) {
						lock.wait();
					} else {
						int value = list.removeFirst();
						System.out.println("Value consumed by consumer" + " " + value);
						lock.notify();
						Thread.sleep(1000);
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
