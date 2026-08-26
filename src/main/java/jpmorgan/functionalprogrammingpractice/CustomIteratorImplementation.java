package jpmorgan.functionalprogrammingpractice;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CustomIteratorImplementation {

    public static void main(String ar[]) {
        List<String> employeeNames = Arrays.asList("A", "B", "C", "D", "E", "F");

        Iterator<String> customIterator = new CustomIterator<>(employeeNames.iterator());

        while (customIterator.hasNext()) {
            System.out.println(customIterator.next());
        }
    }

    static class CustomIterator<T> implements Iterator<T> {

        private Iterator<T> existingIterator;

        public CustomIterator(Iterator<T> existingIterator) {
            this.existingIterator = existingIterator;
        }

        private boolean flag = false;

        @Override
        public boolean hasNext() {
            if (!existingIterator.hasNext()) {
                return false;
            } else {
                existingIterator.next();
                if (!existingIterator.hasNext()) {
                    return false;
                } else {
                    flag = true;
                }
            }
            return true;
        }

        @Override
        public T next() {
            if (flag && existingIterator.hasNext()) {
                flag = false;
                return existingIterator.next();
            } else if (!existingIterator.hasNext()) {
                throw new RuntimeException("No Element Found Exception !!");
            } else {
                throw new RuntimeException("Can't process records inside the iterator");
            }
        }
    }
}
