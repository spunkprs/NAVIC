package multithreading.problems.parkinglot;

public class Ticket {
    private String ticketId;
    private long entryTime;
    private long exitTime;
    private ParkingType parkingType;
    private double amountToBePaid;
    private ParkingSlot assignedParking;
    private Vehicle vehicle;

    public Ticket(String ticketId, long entryTime, ParkingType parkingType, ParkingSlot assignedParking, Vehicle vehicle) {
        this.ticketId = ticketId;
        this.entryTime = entryTime;
        this.parkingType = parkingType;
        this.assignedParking = assignedParking;
        this.vehicle = vehicle;
    }

    public String getTicketId() {
        return ticketId;
    }

    public long getEntryTime() {
        return entryTime;
    }

    public long getExitTime() {
        return exitTime;
    }

    public ParkingType getParkingType() {
        return parkingType;
    }

    public double getAmountToBePaid() {
        return amountToBePaid;
    }

    public ParkingSlot getAssignedParking() {
        return assignedParking;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setExitTime(long exitTime) {
        this.exitTime = exitTime;
    }
}
