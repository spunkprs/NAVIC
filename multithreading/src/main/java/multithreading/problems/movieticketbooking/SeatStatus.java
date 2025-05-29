package multithreading.problems.movieticketbooking;

public enum SeatStatus {

    RESERVE("Reserved"),
    FREE("Free");

    private String seatStatus;

    SeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }
}
