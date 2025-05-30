package multithreading.problems.movieticketbooking;

/**
ENUM for handling status of the seat
*/

public enum SeatStatus {

    RESERVE("Reserved"),
    FREE("Free");

    private String seatStatus;

    SeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String getSeatStatus() {
        return seatStatus;
    }
}
