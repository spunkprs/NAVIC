package multithreading.dag;

public enum TaskState {
    RUNNING("Running"),
    SUCCESS("Success"),
    RETRY("Retry"),
    FAILURE("Failure");

    TaskState(String state) {
        this.state = state;
    }

    private String state;
}
