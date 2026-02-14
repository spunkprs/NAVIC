package multithreading.problems.moviebooking.controller;

import multithreading.problems.moviebooking.entities.MovieScreenMapping;
import multithreading.problems.moviebooking.entities.TimeSlot;

import java.util.List;

public class BookingSystem {

    //Fetching details by movie title
    public List<MovieScreenMapping> fetchDetailsByMovieTitle(String movieTitle) {
        //Assuming every movie name is unique
        return null;
    }


    //Fetching details by theatreId
    public List<MovieScreenMapping> fetchDetailsByTheatreId(String theatreId) {
        return null;
    }


    /**
     Booking the ticket, primarily consists of three parts :-
     a.) Holding the seats
     b.) Making the payment
     c.) Depending on the payment status either allocate of deallocate the seat
     * */
    public boolean bookTickets(String movieId, String theatreId, String screenId, TimeSlot timeSlot, List<String> seatNumbers) {
        return false;
    }

    /**
     Cancellation od ticket, primarily consists of two parts :-
     a.) Seats de allocation
     b.) Starting the refundProcess
     * */
    public boolean cancelTicket(String ticketId) {
        return false;
    }


}
