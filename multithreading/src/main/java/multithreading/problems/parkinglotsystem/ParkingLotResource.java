package multithreading.problems.parkinglotsystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ParkingLotResource {

    private int parkingLotSize;
    private int availableParkingCount;

    private Queue<Integer> parkingLot;
    private Map<String, Ticket> vehicleToTicketMap;

    private Object lock = new Object();

    public ParkingLotResource(int parkingLotSize) {
        this.parkingLotSize = parkingLotSize;
        this.availableParkingCount = parkingLotSize;
        this.parkingLot = new LinkedList<>();
        this.vehicleToTicketMap = new HashMap<>();
        pushParkingLotIndexesToQueue(parkingLotSize);
    }

    private void pushParkingLotIndexesToQueue(int parkingLotSize) {
        for (int index = 1; index <= parkingLotSize; index++) {
            this.parkingLot.add(index);
        }
    }

    public void parkVehicle(String vehicleNumber) {
        System.out.println("Vehicle with number " + vehicleNumber + " stepped into parking lot !!");
        synchronized (lock) {
            while (availableParkingCount == 0) {
                try {
                    System.out.println("Vehicle with number " + vehicleNumber + " needs to wait in the absence of empty parking lot !!");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Got empty parking lot " + vehicleNumber + " proceeding with parking !!");
        int parkingLotIndex = -1;

        synchronized (lock) {
            while (availableParkingCount == 0) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            availableParkingCount--;
            parkingLotIndex = parkingLot.poll();
            Ticket ticket = new Ticket(vehicleNumber, parkingLotIndex, System.currentTimeMillis());
            vehicleToTicketMap.put(vehicleNumber, ticket);
            lock.notifyAll();
        }

        if (parkingLotIndex != -1) {
            System.out.println(vehicleNumber + " parked at parking lot index " + parkingLotIndex);
        } else {
            System.out.println("Some issue reported in the parking lot system granted reserved parking lot" + " to " + vehicleNumber);
        }
    }

    public void unParkVehicle(String vehicleNumber) {
        synchronized (lock) {
            while (!vehicleToTicketMap.containsKey(vehicleNumber)) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(vehicleNumber + " proceeding with unparking !!");
        Ticket ticket = null;

        synchronized (lock) {
            ticket = vehicleToTicketMap.get(vehicleNumber);
            vehicleToTicketMap.remove(vehicleNumber);
            parkingLot.add(ticket.getParkingLotIndex());
            availableParkingCount++;
            lock.notifyAll();
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long exitTime = System.currentTimeMillis();
        double parkingCost = ticket.computeParkingCost(exitTime);

        System.out.println(vehicleNumber + " successfully unparked from index " + ticket.getParkingLotIndex() + " post paying parking amount " + parkingCost);
    }
}
