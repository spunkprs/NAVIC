package multithreading.problems.moviebooking.entities;

//Assuming cost of seat is constant across theatre && screens for now

public class NormalSeat extends Seat {

    private double cost;

    public NormalSeat(String seatNum, boolean isOccupied) {
        super(seatNum, isOccupied);
    }


}
