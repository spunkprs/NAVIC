package multithreading.bankingApplication.exception;

public class DepositException extends RuntimeException {

    private String message;

    public DepositException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
