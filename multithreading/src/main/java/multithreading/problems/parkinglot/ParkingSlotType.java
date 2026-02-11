package multithreading.problems.parkinglot;

public enum ParkingSlotType {

    CAR("Car"),
    BIKE("Bike"),
    ELECTRIC_BIKE("Electric"),
    ELECTRIC_CAR("Electric");

    ParkingSlotType(String type) {
        this.type = type;
    }

    private String type;
}
