package multithreading.problems.movieticketbooking;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
Seat is the resource here upon which following actions can be performed :-
1.) Reserve
2.) Free i.e cancel the booking against that seat

All the seats are independent from each other hence independent bookings can be done simultaneously, 1:1 mapping is there between seat
and it's fellow ReadWriteLock hence following things are guaranteed :-
1.) Multiple threads can check the status of the seat at the same time
2.) No writes can happen during the time read/group of reads are happening && vice-versa
*/

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
