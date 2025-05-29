package multithreading.problems.movieticketbooking;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SeatStatusChecker implements Runnable {

    private Seat allSeats[];

    private int seatNumber;

    private String threadName;

    public SeatStatusChecker(Seat[] allSeats, int seatNumber, String threadName) {
        this.allSeats = allSeats;
        this.seatNumber = seatNumber;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println("Customer with name " + threadName + " want to check status of seat with number " + seatNumber);
        ReentrantReadWriteLock lockAssociatedWithSeat = null;
        try {
            Seat seat = allSeats[seatNumber];
            lockAssociatedWithSeat = seat.getReentrantReadWriteLock();
            lockAssociatedWithSeat.readLock().lock();
            System.out.println("Customer with name " + threadName + " finished with checking status of seat with number " +
                    seatNumber + " whose status is " + seat.getSeatStatus().name());
        } finally {
            lockAssociatedWithSeat.readLock().unlock();
        }
    }
}
