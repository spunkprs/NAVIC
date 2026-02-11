package multithreading.problems.parkinglot;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ParkingSlot {

    private int parkingNum;
    private AtomicBoolean isOccupied;

    public abstract ParkingType fetchParkingType();

    public ParkingSlot(int parkingNum, AtomicBoolean isOccupied) {
        this.parkingNum = parkingNum;
        this.isOccupied = isOccupied;
    }

    public int getParkingNum() {
        return parkingNum;
    }

    public AtomicBoolean getIsOccupied() {
        return isOccupied;
    }
}
