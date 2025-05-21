package multithreading.problems.parkinglotsystem;

public class Ticket {
    private String vehicleNumber;
    private int parkingLotIndex;
    private long entryTimeInMillis;
    private long exitTimeInMillis;

    public Ticket(String vehicleNumber, int parkingLotIndex, long entryTime) {
        this.vehicleNumber = vehicleNumber;
        this.parkingLotIndex = parkingLotIndex;
        this.entryTimeInMillis = entryTime;
    }

    public int getParkingLotIndex() {
        return parkingLotIndex;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double computeParkingCost(long exitTime) {
        this.exitTimeInMillis = exitTime;
        double baseAmountPerSec = 2.5;
        return ((this.exitTimeInMillis - this.entryTimeInMillis)/1000) * baseAmountPerSec;
    }
}
