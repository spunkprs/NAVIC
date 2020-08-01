package multithreading;

import java.util.LinkedList;

public class Producer implements Runnable {
	
	private LinkedList<Integer> list;
	private Object lock;
	private int listMaxSize;
	
	public Producer(LinkedList<Integer> list, Object lock, int listMaxSize) {
		this.lock = lock;
		this.list = list;
		this.listMaxSize = listMaxSize;
	}

	@Override
	public void run() {
		
		int value = 1;
		
		while (true) {
			synchronized (lock) {
				
				try {
					if (list.size() == listMaxSize) {
						lock.wait();
					} else {
						System.out.println("Going to push value" + " " + value);
						list.add(value);
						value++;
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
