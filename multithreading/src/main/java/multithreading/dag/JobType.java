package multithreading.dag;

public enum JobType {

    RECURRING("Recurring"),
    NON_RECURRING("Non-Recurring");

    JobType(String type) {
        this.type = type;
    }

    private String type;
}
