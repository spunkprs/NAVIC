package coupang.lld.parkinglot.model;

public class Ticket {
    private String ticketId;
    private Spot allocatedSpot;
    private Long entryTime;
    private Long exitTime;
    private double calculatedAmount;
    private boolean amountPaid;
    private ParkingStructure parkingStructure;
}
