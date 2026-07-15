package coupang.lld.parkinglot.model;

public class Truck extends Vehicle {

    public Truck(String vehicleNumber) {
        super(vehicleNumber);
    }

    public VehicleType getVehicleType() {
        return VehicleType.Truck;
    }
}
