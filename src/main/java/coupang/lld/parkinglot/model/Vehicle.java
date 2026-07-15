package coupang.lld.parkinglot.model;

public abstract class Vehicle {
    private String vehicleNumber; //Unique number will be there for each vehicle
    public abstract VehicleType getVehicleType();

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }
}
