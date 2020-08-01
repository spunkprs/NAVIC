package multithreading;

import java.util.LinkedList;

public class ProducerConsumerProcessor {

	public static void main(String[] args) {
		
		LinkedList<Integer> list = new LinkedList<Integer>();
		Object lock = new Object();
		
		Producer producer = new Producer(list, lock, 6);
		ConsumerProcess consumerProcess = new ConsumerProcess(list, lock);
		
		System.out.print("Going to start producer && consumer");
		
		Thread t1 = new Thread(producer);
		
		Thread t2 = new Thread(consumerProcess);
		
		t1.start();
		
		t2.start();
	}

}
