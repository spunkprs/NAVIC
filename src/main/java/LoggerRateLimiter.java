import java.util.HashMap;
import java.util.Map;

/**
 Design a logger system that receives a stream of messages along with their timestamps.
 Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t
 will prevent other identical messages from being printed until timestamp t + 10).

 All messages will come in chronological order. Several messages may arrive at the same timestamp.

 Implement the Logger class:

 Logger() Initializes the logger object.
 bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the
 given timestamp, otherwise returns false.

Constraints:

1.) 0 <= timestamp <= pow(10,9)
2.) Every timestamp will be passed in non-decreasing order (chronological order).
3.) 1 <= message.length <= 30
4.) At most pow(10,4) calls will be made to shouldPrintMessage.

Level : Easy
 This question was asked in Uber !!

 ---> Design Kind Of Question <-----
 * */



public class LoggerRateLimiter {


    public static void main(String ar[]) {
        LoggerRateLimiter unit = new LoggerRateLimiter();
    }


    static class Logger {

        Map<String, Integer> map = null;

        public Logger() {
            map = new HashMap<>();
        }

        public boolean shouldPrintMessage(int timestamp, String message) {

            if (!map.containsKey(message)) {
                map.put(message, timestamp + 10);
                return true;
            } else {
                int allowedTimestamp = map.get(message);
                boolean result = timestamp < allowedTimestamp ? false: true;
                if (result) {
                    map.put(message, timestamp + 10);
                }
                return result;
            }
        }
    }
}
