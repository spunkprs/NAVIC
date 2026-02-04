package streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 Reference -->
 a.) https://www.baeldung.com/java-8-streams-introduction
 b.) https://www.baeldung.com/java-8-streams
 * */

public class StreamOperationsTwo {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        Map<String, Integer> sizeMap = new HashMap<>();

        //Performing intermediate operations of map then filter is applied then terminal operation of anyMatch is applied just to return the result
        boolean result = names
                .stream()
                .map(word -> new Size(word, word.length()))
                .filter(obj -> obj.size >= 4)
                //.anyMatch(obj -> obj.word.startsWith("A"))
                .allMatch(obj -> obj.word.startsWith("A"));

        System.out.println("boolean result after performing multiple intermediate operations && single terminal operation is " + result);

        int summationOfSize = names
                .stream()
                .map(word -> new Size(word, word.length()))
                .filter(obj -> obj.size >= 4)
                .filter(obj -> obj.word.startsWith("A"))
                .map(obj -> obj.size)
                .reduce(0, (obj1, obj2) -> obj1 + obj2);

        System.out.println("summationOfSize result after performing multiple intermediate operations && single terminal operation is " + summationOfSize);

        long finalElementsCount = names
                .stream()
                .map(word -> new Size(word, word.length()))
                .filter(obj -> obj.size >= 4)
                .filter(obj -> obj.word.startsWith("A"))
                .map(obj -> obj.size)
                .count();

        System.out.println("finalElementsCount result after performing multiple intermediate operations && single terminal operation is " + finalElementsCount);
    }

    static class Size {
        private String word;
        private int size;

        public Size(String word, int size) {
            this.word = word;
            this.size = size;
        }
    }
}
