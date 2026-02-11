package multithreading.problems.parkinglot;

import java.util.concurrent.atomic.AtomicBoolean;

public class BikeParkingSlot extends ParkingSlot {

    @Override
    public ParkingType fetchParkingType() {
        return ParkingType.BIKE;
    }

    public BikeParkingSlot(int parkingNum, AtomicBoolean isOccupied) {
        super(parkingNum, isOccupied);
    }
}
