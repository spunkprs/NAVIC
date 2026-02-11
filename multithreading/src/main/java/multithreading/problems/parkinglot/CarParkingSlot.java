package multithreading.problems.parkinglot;

import java.util.concurrent.atomic.AtomicBoolean;

public class CarParkingSlot extends ParkingSlot {

    public CarParkingSlot(int parkingNum, AtomicBoolean isOccupied) {
        super(parkingNum, isOccupied);
    }


    @Override
    public ParkingType fetchParkingType() {
        return ParkingType.CAR;
    }
}
