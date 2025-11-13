package multithreading.bankingApplication.exception;

public class WithdrawException extends RuntimeException {

    private String message;

    public WithdrawException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
