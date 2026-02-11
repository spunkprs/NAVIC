package multithreading.problems.parkinglot;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingAssignor {

    private AtomicInteger availableParkingSlots;
    private List<ParkingSlot> parkingSlots;

    public ParkingAssignor(AtomicInteger availableParkingSlots, List<ParkingSlot> parkingSlots) {
        this.availableParkingSlots = availableParkingSlots;
        this.parkingSlots = parkingSlots;
    }


    /*
    Getting rid of this method as this will decrease throughput of the system
    public boolean isParkingAvailable(ParkingType parkingType) {
        return false;
    }*/


    // This method is pretty quick in response but the results are eventually consistent
    public int availableParkingCount(ParkingType parkingType) {
        return availableParkingSlots.get();
    }

    //This method follows optimistic locking instead of pessimistic and guarantee higher throughput of the system
    public Ticket assignParkingSlot(Vehicle vehicle, ParkingType parkingType) {
        Ticket assignedTicket = null;
        for (ParkingSlot parkingSlot : parkingSlots) {
            if (parkingSlot.fetchParkingType().name().equals(parkingType.name())) {
                if (parkingSlot.getIsOccupied().compareAndSet(false, true)) {
                    availableParkingSlots.decrementAndGet();
                    long currentTimeInMillis = System.currentTimeMillis();
                    assignedTicket = new Ticket(vehicle.getUniqueNum() + parkingSlot.getParkingNum(), currentTimeInMillis, parkingType, parkingSlot, vehicle);
                    break;
                }
            }
        }
        return assignedTicket;
    }

    public double calculateParkingAmount(Ticket ticket) {
        double parkingAmount = 0.0;
        if (ticket != null) {
            ParkingSlot assignedSlot = ticket.getAssignedParking();
            availableParkingSlots.incrementAndGet();
            assignedSlot.getIsOccupied().set(true);
            ticket.setExitTime(System.currentTimeMillis());
            parkingAmount = processToComputeParkingAmount(ticket.getEntryTime(), ticket.getExitTime());
        }
        return parkingAmount;
    }

    private double processToComputeParkingAmount(long entryTime, long exitTime) {
        long entryTimeInMinutes = entryTime/1000 * 60;
        long exitTimeInMinutes = exitTime/1000 * 60;
        return (exitTimeInMinutes - entryTimeInMinutes) * 0.005;
    }
}
