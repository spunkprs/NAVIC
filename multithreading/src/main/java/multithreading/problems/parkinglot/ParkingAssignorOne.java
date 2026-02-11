package multithreading.problems.parkinglot;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingAssignorOne {

    private Map<ParkingSlotType, AtomicInteger> availableParkingSlots;
    private Map<ParkingSlotType, List<ParkingSlot>> parkingSlots;

    public ParkingAssignorOne(Map<ParkingSlotType, AtomicInteger> availableParkingSlots, Map<ParkingSlotType, List<ParkingSlot>> parkingSlots) {
        this.availableParkingSlots = availableParkingSlots;
        this.parkingSlots = parkingSlots;
    }


    /*
    Getting rid of this method as this will decrease throughput of the system
    public boolean isParkingAvailable(ParkingType parkingType) {
        return false;
    }*/

    // This method is pretty quick in response but the results are eventually consistent
    public int availableParkingCount(ParkingSlotType parkingSlotType) {
        return availableParkingSlots.get(parkingSlotType).get();
    }

    //This method follows optimistic locking instead of pessimistic and guarantee higher throughput of the system
    public Ticket assignParkingSlot(Vehicle vehicle, ParkingSlotType parkingSlotType) {
        Ticket assignedTicket = null;
        for (ParkingSlot parkingSlot : parkingSlots.get(parkingSlotType)) {
            if (parkingSlot.fetchParkingType() == parkingSlotType) {
                if (parkingSlot.getIsOccupied().compareAndSet(false, true)) {
                    availableParkingSlots.get(parkingSlotType).decrementAndGet();
                    long currentTimeInMillis = System.currentTimeMillis();
                    assignedTicket = new Ticket(vehicle.getUniqueNum() + parkingSlot.getParkingNum(), currentTimeInMillis, parkingSlotType, parkingSlot, vehicle);
                    break;
                }
            }
        }
        return assignedTicket;
    }


    /***
     Above method can be written using ConcurrentLinkedQueue where multiple threads can try to poll parking slots and get the parking slot assigned
     During freeing the parking slot it can be pushed back to CLQ
     During both the processes counter against the type needs to be altered as I have done above
     Rather making use of CLQ is better as we don't need to iterate the entire structure in case free slots aren't there or pretty less because in case of CLQ it's size will
     be shrinking with allocation
     */

    public double calculateParkingAmount(Ticket ticket) {
        double parkingAmount = 0.0;
        if (ticket != null) {
            ParkingSlot assignedSlot = ticket.getAssignedParking();
            availableParkingSlots.get(ticket.getParkingType()).incrementAndGet();
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
