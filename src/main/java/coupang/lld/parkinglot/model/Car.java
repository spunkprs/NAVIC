package coupang.lld.parkinglot.model;

public class Car extends Vehicle {

    public Car(String vehicleNumber) {
        super(vehicleNumber);
    }

    public VehicleType getVehicleType() {
        return VehicleType.Car;
    }
}
