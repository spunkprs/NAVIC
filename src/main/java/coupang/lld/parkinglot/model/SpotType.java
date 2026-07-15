package coupang.lld.parkinglot.model;

public enum SpotType {
    Bike(VehicleType.Bike),
    Car(VehicleType.Bike, VehicleType.Car),
    Truck(VehicleType.Bike, VehicleType.Car, VehicleType.Truck);

    private VehicleType[] allowedVehicleTypes;

     SpotType(VehicleType ... allowedVehicleTypes) {
        this.allowedVehicleTypes = allowedVehicleTypes;
    }

    public VehicleType[] getAllowedVehicleTypes() {
        return allowedVehicleTypes;
    }
}
