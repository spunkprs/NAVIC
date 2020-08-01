package multithreading;

public class SharedNumber {
	
	private int sharedNumber;
	
	public SharedNumber(int sharedNumber) {
		this.sharedNumber = sharedNumber;
	}

	public int getSharedNumber() {
		return sharedNumber;
	}

	public void setSharedNumber(int sharedNumber) {
		this.sharedNumber = sharedNumber;
	}
	
}
