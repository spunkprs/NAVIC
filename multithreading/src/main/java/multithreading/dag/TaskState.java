package multithreading.dag;

public enum TaskState {
    RUNNING("Running"),
    WAITING("Waiting"),
    SUCCESS("Success"),
    FAILURE("Failure");

    TaskState(String state) {
        this.state = state;
    }

    private String state;
}
