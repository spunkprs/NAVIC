package multithreading.bankingApplication.entity;

public enum ExceptionMessages {

    WITHDRAW_EXCEPTION("Exception Occurred During Withdraw"),
    DEPOSIT_EXCEPTION("Exception Occurred During Deposit");

    private String exceptionMessage;

    private ExceptionMessages(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
