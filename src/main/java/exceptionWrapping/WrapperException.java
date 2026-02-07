package exceptionWrapping;

public class WrapperException extends RuntimeException {

    private String exceptionDetails;

    public WrapperException(String message, Throwable cause) {
        super(message, cause);
        this.exceptionDetails = message;
    }
}
