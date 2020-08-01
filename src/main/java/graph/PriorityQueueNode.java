package graph;

public class PriorityQueueNode implements Comparable<PriorityQueueNode> {
	
	private int num;
	private int amount;
	private boolean isValid;
	
	public PriorityQueueNode(int num, int amount, boolean isValid) {
		this.num = num;
		this.amount = amount;
		this.isValid = isValid;
	}

	
	@Override
	public int compareTo(PriorityQueueNode node) {
		return this.amount < node.amount ? -1 : this.amount > node.amount ? 1 : 0;
	}

	public int getNum() {
		return num;
	}

	public int getAmount() {
		return amount;
	}

	public boolean isValid() {
		return isValid;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	
	
	
}
