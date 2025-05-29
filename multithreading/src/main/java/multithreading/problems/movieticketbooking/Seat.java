package multithreading.problems.movieticketbooking;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Seat {

    private int seatNumber;
    private SeatStatus seatStatus;
    private ReentrantReadWriteLock reentrantReadWriteLock;

    public Seat(int seatNumber, SeatStatus seatStatus, ReentrantReadWriteLock reentrantReadWriteLock) {
        this.seatNumber = seatNumber;
        this.seatStatus = seatStatus;
        this.reentrantReadWriteLock = reentrantReadWriteLock;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public ReentrantReadWriteLock getReentrantReadWriteLock() {
        return reentrantReadWriteLock;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }
}
