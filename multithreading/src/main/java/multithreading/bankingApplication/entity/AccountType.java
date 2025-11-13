package multithreading.bankingApplication.entity;

public enum AccountType {
    SAVING("Saving"),
    CURRENT("Current");

    private String accType;

    private AccountType(String accType) {
        this.accType = accType;
    }
}
