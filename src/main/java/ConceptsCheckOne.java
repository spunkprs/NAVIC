import java.util.Comparator;
import java.util.PriorityQueue;

public class ConceptsCheckOne {
	
	public static void main(String ar[]) {
		System.out.println("Hello World");
		
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<ConceptsCheckOne.Pair>(new Comparator<Pair>() {
        	
        	@Override
			public int compare(Pair o1, Pair o2) {
				if (o1.cost < o2.cost) {
					return -1;
				} else if (o1.cost > o2.cost) {
					return 1;
				}
				return 0;
			}
		});
        
        ConceptsCheckOne c1 = new ConceptsCheckOne();
		
        ConceptsCheckOne.Pair pOne = c1.new Pair(10);
        ConceptsCheckOne.Pair pTwo = c1.new Pair(20);
        ConceptsCheckOne.Pair pThree = c1.new Pair(30);
        ConceptsCheckOne.Pair pFour = c1.new Pair(40);
		priorityQueue.add(pOne);
		priorityQueue.add(pTwo);
		priorityQueue.add(pThree);
		priorityQueue.add(pFour);
		
		int counter = 1;
		
		while (!priorityQueue.isEmpty()) {
		    if (counter == 1) {
		        pTwo.cost = 100;
		    }
		    counter++;
		    System.out.println(priorityQueue.remove().cost);
		}
	}
	
		class Pair {
        private int cost;
        
        public Pair(int cost) {
            this.cost = cost;
        }
        
        @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + cost;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (cost != other.cost)
				return false;
			return true;
		}

		private ConceptsCheckOne getEnclosingInstance() {
			return ConceptsCheckOne.this;
		}
    }

}
