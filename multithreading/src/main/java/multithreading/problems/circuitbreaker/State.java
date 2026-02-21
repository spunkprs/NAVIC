package multithreading.problems.circuitbreaker;

import org.apache.commons.lang3.RandomUtils;

public abstract class State {

    public abstract State makeTransition();

    public abstract Result makeCallToApi(String url);

    public Pair provideGenericApiBehaviour(String url) {
        return new Pair();
    }

    static class Pair {
        private boolean response;
        private long responseTimeStamp;

        public Pair() {
            this.response = RandomUtils.nextBoolean();
            this.responseTimeStamp = System.currentTimeMillis();
        }

        public boolean isResponse() {
            return response;
        }

        public long getResponseTimeStamp() {
            return responseTimeStamp;
        }
    }

    static class Result {
        private Pair responsePair;
        private State resultingState;

        public Result(Pair responsePair, State resultingState) {
            this.responsePair = responsePair;
            this.resultingState = resultingState;
        }
    }
}
