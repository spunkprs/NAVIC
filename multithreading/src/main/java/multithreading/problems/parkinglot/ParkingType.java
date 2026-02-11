package multithreading.problems.parkinglot;

public enum ParkingType {

    CAR("Car"),
    BIKE("Bike");

    ParkingType(String type) {
        this.type = type;
    }

    private String type;
}
