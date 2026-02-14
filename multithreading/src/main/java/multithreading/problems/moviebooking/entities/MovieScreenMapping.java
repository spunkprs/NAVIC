package multithreading.problems.moviebooking.entities;

public class MovieScreenMapping {
    private String movieScreenMappingId;
    private Movie movie;
    private Screen screen;
    private Theatre theatre;
    private TimeSlot timeSlot;

    public String getMovieScreenMappingId() {
        return movieScreenMappingId;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
