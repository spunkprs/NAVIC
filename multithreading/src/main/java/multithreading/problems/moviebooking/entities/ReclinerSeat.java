package multithreading.problems.moviebooking.entities;

//Assuming cost of seat is constant across theatre && screens for now

public class ReclinerSeat extends Seat {

    private double cost;

    public ReclinerSeat(String seatNum, boolean isOccupied) {
        super(seatNum, isOccupied);
    }
}
