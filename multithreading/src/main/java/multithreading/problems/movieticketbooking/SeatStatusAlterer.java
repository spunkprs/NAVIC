package multithreading.problems.movieticketbooking;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
This class mimics the behaviour of any thread which attempts to alter the status of a given seat at a time
*/

public class SeatStatusAlterer implements Runnable {

    private Seat allSeats[];
    private int seatNumber;
    private SeatStatus seatStatus;
    private String threadName;

    public SeatStatusAlterer(Seat[] allSeats, int seatNumber, SeatStatus seatStatus, String threadName) {
        this.allSeats = allSeats;
        this.seatNumber = seatNumber;
        this.seatStatus = seatStatus;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Customer with name " + threadName + " want to update status of seat to " + seatStatus.name()
                + " having seat number " + seatNumber);
        ReentrantReadWriteLock lockAssociatedWithSeat = null;
        try {
            Seat seat = allSeats[seatNumber];
            lockAssociatedWithSeat = seat.getReentrantReadWriteLock();
            lockAssociatedWithSeat.writeLock().lock();
            if (seat.getSeatStatus().name().equals(seatStatus.name())) {
                if (seatStatus.name().equals(SeatStatus.RESERVE)) {
                    System.out.println("Reserved seat with number " + seatNumber + " cannot be reserved again !!");
                } else if (seatStatus.name().equals(SeatStatus.FREE)) {
                    System.out.println("Free seat with number " + seatNumber + " cannot be made free again !!");
                }
            } else {
                seat.setSeatStatus(seatStatus);
                System.out.println("Customer with name " + threadName + " finished with altering status of seat with number " + seatNumber
                        + ", updated status of seat is " + seatStatus.name());
            }
        } finally {
            lockAssociatedWithSeat.writeLock().unlock();
        }
    }
}
