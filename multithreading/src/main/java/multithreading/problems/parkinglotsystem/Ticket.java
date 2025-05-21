package multithreading.problems.parkinglotsystem;

/*
This class is responsible for maintaining following things :-
a.) vehicleNumber
b.) parkingLotIndex --> slot index mapped to vehicle
c.) entryTimeInMillis --> time at which vehicle enters into the slot
d.) exitTimeInMillis --> time at which vehicle exits from the slot
e.) parkingCost --> this is derived using entryTime && exitTime

Keeping the scope of the problem simple have kept baseAmount per second as a constant but that can be dynamic too
*/

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
