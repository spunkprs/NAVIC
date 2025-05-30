package multithreading.problems.movieticketbooking;

import java.util.Random;

/**
This class demonstrates the ability to simulate the behaviour of a movie ticket booking scenario where following operations are possible :-

 1.) Booking of multiple seats by multiple users can be done at the same time
 2.) Status of multiple seats can be checked by multiple users can be done at the same time
 3.) Status of an individual seat can be checked by multiple users at the same time
 4.) Status of an individual seat can be altered by a single user at a time
 5.) No writes can happen during a single/group of users checking status of a seat and vice versa
 6.) Have taken the liberty of making the seat number start from index 0

 Limitation of the solution -->
 a.) Single user can reserve single seat not multiple seats at the same time

 Future Enhancements -->
 a.) Single user should be able to reserve multiple seats at the same time
 b.) In case multiple users wishes to reserve same group of seats only one should be able to reserve it effectively
 c.) In the case of contention during writes among multiple users i.e multiple users wising to reserve same set OR subset of seats
 only one of the user shall be able to make the transaction, partial allotment of seats shouldn't happen

 */


public class MovieTicketBookingDemonstration {

    public static void main(String ar[]) {

        int maxSeatNumber = 5;
        Seat[] availableSeats = prepareSeats(maxSeatNumber);

        Runnable[] readers = prepareReaderThreads(20, maxSeatNumber, availableSeats);
        Runnable[] writers = prepareWriterThreads(20, maxSeatNumber, availableSeats);

        startReaders(readers);
        startWriters(writers);
    }

    private static void startReaders(Runnable[] readers) {
        for (int i = 0; i < readers.length; i++) {
            Thread reader = new Thread(readers[i]);
            reader.start();
        }
    }

    private static void startWriters(Runnable[] writers) {
        for (int i = 0; i < writers.length; i++) {
            Thread writer = new Thread(writers[i]);
            writer.start();
        }
    }

    private static Runnable[] prepareWriterThreads(int numberOfWriterThreads, int maxSeatNumber, Seat[] availableSeats) {
        Runnable writerThreads[] = new Runnable[numberOfWriterThreads];
        for (int i = 1; i <= numberOfWriterThreads; i++) {
            writerThreads[i - 1] = new SeatStatusAlterer(availableSeats, new Random().nextInt(maxSeatNumber), SeatStatus.RESERVE,
                    "Writer- " + String.valueOf(i - 1));
        }
        return writerThreads;
    }

    private static Runnable[] prepareReaderThreads(int numberOfReaderThreads, int maxSeatNumber, Seat[] availableSeats) {
        Runnable readerThreads[] = new Runnable[numberOfReaderThreads];
        for (int i = 1; i <= numberOfReaderThreads; i++) {
            readerThreads[i - 1] = new SeatStatusChecker(availableSeats, new Random().nextInt(maxSeatNumber),
                    "Reader- " + String.valueOf(i - 1));
        }
        return readerThreads;
    }

    private static Seat[] prepareSeats(int maxSeatNumber) {
        Seat seats[] = new Seat[maxSeatNumber];
        for (int i = 0; i < maxSeatNumber; i++) {
            seats[i] = new Seat(i);
        }
        return seats;
    }
}
