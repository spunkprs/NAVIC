import java.util.LinkedList;
import java.util.Queue;

public class ConceptsCheck {

	public static void main(String[] args) {
		
		for (int i = 1; i <=1; i++) {
			Queue<Integer> queue = new LinkedList<Integer>();
			queue.add(10);
			queue.add(20);
			queue.add(30);
			
			while (!queue.isEmpty() && queue.size() < 10) {
				queue.add(40);
				//queue.remove();
			}
			
			/*
			 * for (int j = 0; j < queue.size(); j++) { queue.add(55); }
			 * This leads to never ending process and the code states everything
			 */
			
			/*
			 * for (Integer k : queue) { queue.add(k); }
			 * 
			 * for (Integer k : queue) { queue.remove(k); }
			 * 
			 * It leads to java.util.ConcurrentModificationException being in the single thread, if you try to modify the collection while 
			 * iterating over it using for each loop
			 */
			
			/*
			 * while (!queue.isEmpty()) { queue.remove(); }
			 * Above exception doesn't come if we go via this approach
			 */
			
			/*
			 * for (int k = 0; k < queue.size(); k++) { queue.remove(); }
			 */
			
			/*
			 * Iterator<Integer> iterator = queue.iterator(); while (iterator.hasNext()) {
			 * queue.remove(); //queue.remove(iterator.next()); }
			 * Pretty interesting if we make use of remove() method it doesn't lead to ConcurrentModificationException but if we make use 
			 * of remove() method with some parameter it leads to ConcurrentModificationException, so I guess remove() is the only safe 
			 * way to modify a collection during iteration while making use of Iterator over Collection
			 */
			
			//https://stackoverflow.com/questions/223918/iterating-through-a-collection-avoiding-concurrentmodificationexception-when-re
			
			System.out.println("Hell yeah !!" + queue.size());
			
			
			
		}
	}

}
