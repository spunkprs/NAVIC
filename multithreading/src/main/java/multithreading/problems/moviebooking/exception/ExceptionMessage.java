package multithreading.problems.moviebooking.exception;

public enum ExceptionMessage {

    THEATRE_ID_NULL("Theatre Id Cannot Be Empty Or Null"),
    MOVIE_TITLE_NULL("Movie Title Cannot Be Empty Or Null"),
    INVALID_MOVIE_TITLE("Invalid Movie Title"),
    INVALID_THEATRE_ID("Invalid Theatre ID");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
