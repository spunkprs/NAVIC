package multithreading.bankingApplication.entity;

public enum AccountStatus {
    ACTIVE("Active"),
    FROZEN("Frozen"),
    CLOSED("Closed");

    private String accStatus;

    private AccountStatus(String accStatus) {
        this.accStatus = accStatus;
    }
}
