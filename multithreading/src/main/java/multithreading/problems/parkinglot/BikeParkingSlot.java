package multithreading.problems.parkinglot;

import java.util.concurrent.atomic.AtomicBoolean;

public class BikeParkingSlot extends ParkingSlot {

    @Override
    public ParkingSlotType fetchParkingType() {
        return ParkingSlotType.BIKE;
    }

    public BikeParkingSlot(int parkingNum, AtomicBoolean isOccupied) {
        super(parkingNum, isOccupied);
    }
}
