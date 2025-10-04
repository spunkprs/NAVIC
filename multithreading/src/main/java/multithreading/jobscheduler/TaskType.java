package multithreading.jobscheduler;

public enum TaskType {
    RECURRING("Recurring"),
    NON_RECURRING("Non-Recurring");

    TaskType(String type) {
        this.type = type;
    }

    private String type;

}
