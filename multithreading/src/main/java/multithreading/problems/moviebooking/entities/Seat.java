package multithreading.problems.moviebooking.entities;

public abstract class Seat {
    private String seatNum;
    private boolean isOccupied;

    public Seat(String seatNum, boolean isOccupied) {
        this.seatNum = seatNum;
        this.isOccupied = isOccupied;
    }
}
