package coupang.lld.parkinglot.model;

public class Bike extends Vehicle {

    public Bike(String vehicleNumber) {
        super(vehicleNumber);
    }

    public VehicleType getVehicleType() {
        return VehicleType.Bike;
    }
}
