package coupang.lld.parkinglot.model;

public class Spot {
    private int index;
    private Level level;
    private SpotType spotType;
    private SpotStatus status;
    private ParkingStructure associatedParkingStructure;

    public int getIndex() {
        return index;
    }

    public Level getLevel() {
        return level;
    }

    public SpotType getSpotType() {
        return spotType;
    }

    public SpotStatus getStatus() {
        return status;
    }

    public ParkingStructure getAssociatedParkingStructure() {
        return associatedParkingStructure;
    }

    public void setStatus(SpotStatus status) {
        this.status = status;
    }
}
