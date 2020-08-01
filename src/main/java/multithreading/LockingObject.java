package multithreading;

public class LockingObject {
	
	private int sharingNumber;
	
	public LockingObject(int sharingNumber) {
		this.sharingNumber = sharingNumber;
	}

	public int getSharingNumber() {
		return sharingNumber;
	}

	public void setSharingNumber(int sharingNumber) {
		this.sharingNumber = sharingNumber;
	}
}
