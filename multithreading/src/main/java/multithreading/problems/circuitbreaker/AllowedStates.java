package multithreading.problems.circuitbreaker;

public class AllowedStates {

    public static long CLOSED_STATE_TIME_BASED_SLIDING_WINDOW = 3000;
    public static double CLOSED_STATE_FAILURE_RATE_THRESHOLD = 100;

    public static long OPEN_STATE_TRANSITION_TIMEOUT = 3000;

}
