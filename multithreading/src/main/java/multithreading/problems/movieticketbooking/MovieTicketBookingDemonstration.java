package multithreading.problems.movieticketbooking;

/**
This class demonstrates the ability to simulate the behaviour of a movie ticket booking scenario where following operations are possible :-

 1.) Booking of multiple seats by multiple users can be done at the same time
 2.) Status of multiple seats can be checked by multiple users can be done at the same time
 3.) Status of an individual seat can be checked by multiple users at the same time
 4.) Status of an individual seat can be altered by a single user at a time
 5.) No writes can happen during a single/group of users checking status of a seat and vice versa

 Limitation of the solution -->
 a.) Single user can reserve single seat not multiple seats at the same time

 Future Enhancements -->
 a.) Single user can reserve should be able to reserve multiple seats at the same time
 b.) In case multiple users wishes to reserve same group of seats only one should be able to reserve it effectively
 c.) In the case of contention during writes among multiple users i.e multiple users wising to reserve same set OR subset of seats
 only one of the user shall be able to make the transaction, partial allotment of seats shouldn't happen

 */


public class MovieTicketBookingDemonstration {

    public static void main(String ar[]) {

    }
}
