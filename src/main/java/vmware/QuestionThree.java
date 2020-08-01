package vmware;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class QuestionThree {

	public static void main(String[] args) {
		
		
		QuestionThree q3 = new QuestionThree();
		List<Integer> list = new ArrayList<Integer>();
		list.add(4);
		list.add(9);
		list.add(2);
		list.add(3);
		System.out.print(q3.fetchComputedPrice(list));
		
		
	}
	
	public long fetchComputedPrice(List<Integer> prices) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1.intValue() < o2.intValue()) {
					return -1;
				} else if (o1.intValue() > o2.intValue()) {
					return 1;
				}
				return 0;
			}
		});
		
		long computedPrice = 0;
		
		if (prices.size() > 1) {
			priorityQueue.add(prices.get(0));
			computedPrice += prices.get(0);
			for (int i = 1; i < prices.size(); i++) {
				computedPrice += Math.max(prices.get(i) - priorityQueue.peek(), 0);
				priorityQueue.add(prices.get(i));
			}
		} else if (prices.size() == 1) {
			computedPrice = prices.get(0);
		}
		return computedPrice;
	}

}
