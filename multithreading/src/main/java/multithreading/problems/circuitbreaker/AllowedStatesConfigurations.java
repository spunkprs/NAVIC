package multithreading.problems.circuitbreaker;

public class AllowedStatesConfigurations {

    public static long CLOSED_STATE_TIME_BASED_SLIDING_WINDOW = 3000;
    public static double CLOSED_STATE_FAILURE_RATE_THRESHOLD = 100;

    public static long OPEN_STATE_TRANSITION_TIMEOUT = 3000;

    public static long HALF_OPEN_STATE_TIME_BASED_SLIDING_WINDOW = 2000;
    public static double HALF_OPEN_STATE_FAILURE_RATE_THRESHOLD = 20;
    public static int HALF_OPEN_REQUESTS_ALLOWED = 100;
    public static double HALF_OPEN_TOKEN_REFILL_RATE = 100;
}
