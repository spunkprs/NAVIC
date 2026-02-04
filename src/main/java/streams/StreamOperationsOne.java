package streams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StreamOperationsOne {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        Map<String, Integer> sizeMap = new HashMap<>();

        //Performing transformation then results are printed, here map is intermediate operation && forEach is terminal operation
        names.stream().map(word -> new Size(word, word.length())).forEach(obj -> System.out.println(obj.word + " " + obj.size));

        names.stream().forEach(word -> {
            sizeMap.put(word, word.length());
        });

        System.out.println("No transformation here, direct call to terminal operation !!");
        printMap(sizeMap);
    }

    private static void printMap(Map<String, Integer> sizeMap) {
        for (String word : sizeMap.keySet()) {
            System.out.println(word + " " + sizeMap.get(word));
        }
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
