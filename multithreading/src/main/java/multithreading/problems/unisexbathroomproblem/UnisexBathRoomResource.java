package multithreading.problems.unisexbathroomproblem;

public class UnisexBathRoomResource {

    private int maxPeopleAllowed;
    private int maleCountUsingBathroom;
    private int femaleCountUsingBathroom;

    public UnisexBathRoomResource(int maxPeopleAllowed) {
        this.maxPeopleAllowed = maxPeopleAllowed;
    }

    public void allowMaleToUseBathroom(String personName) {
        synchronized (this) {
            while (femaleCountUsingBathroom != 0 || maleCountUsingBathroom == maxPeopleAllowed) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            maleCountUsingBathroom++;
        }

        useBathroom(personName);

        synchronized (this) {
            maleCountUsingBathroom--;
            System.out.println(personName + " is done with using the bathroom");
            if (maleCountUsingBathroom == 0) {
                this.notifyAll();
            }
        }
    }

    public void allowFemaleToUseBathroom(String personName) {
        synchronized (this) {
            while (maleCountUsingBathroom != 0 || femaleCountUsingBathroom == maxPeopleAllowed) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            femaleCountUsingBathroom++;
        }

        useBathroom(personName);

        synchronized (this) {
            femaleCountUsingBathroom--;
            System.out.println(personName + " is done with using the bathroom");
            if (femaleCountUsingBathroom == 0) {
                this.notifyAll();
            }
        }
    }

    private void useBathroom(String personName) {
        System.out.println(personName + " is currently using bathroom");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
