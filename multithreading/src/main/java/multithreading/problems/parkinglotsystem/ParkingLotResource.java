package multithreading.problems.parkinglotsystem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
This class aims at simulating a multithreaded parking lot system that enables following operations to be done concurrently :-
a.) park vehicle into the parking lot system
b.) unpark already parked vehicle from the parking lot system

Following methods does the obvious for us :-
a.) parkVehicle(String vehicleNumber) --> to park the vehicle
b.) unParkVehicle(String vehicleNumber) --> to unpark the vehicle
* */

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

    /*
    This method will make sure that all the vehicles are granted a parking slot index if it's available, if the parking is full,
    all the threads who wish to get parking slot has to wait till it's available

    Entire method is not guarded by lock rather only critical sections are guarded by lock which guarantee atomicity.
    Intermittent notification && final notification against issuance of parking slot index doesn't come under critical
    section hence not needed to be guarded by lock
    */

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

        /*
        Have again called lock.wait() in the loop here to cater below scenario -->
        a.) One parking slot is available but there are multiple vehicles that wants to park there hence only one of them would be
        getting the slot and rest shall wait till we have vacant parking slot
        b.) Above mentioned scenario will only come into play when post having a non empty parking slot, some vehicle is unparked hence
        all the vehicles waiting for empty slot are free now to proceed further
        */

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
