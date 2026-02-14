package multithreading.problems.moviebooking.entities;

import java.util.List;

public class Ticket {
    private String ticketId;
    private MovieScreenMapping movieScreenMapping;
    private List<Seat> seatsAssigned;
    private User user;
    private double amountPaid;
    private String ticketStatus;
}
