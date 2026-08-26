package jpmorgan.functionalprogrammingpractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingExample {

    public static void main(String args[]) {
        List<Employee> employees = Arrays.asList(
                new Employee("Alice", 60000),
                new Employee("Charlie", 50000),
                new Employee("Bob", 60000),
                new Employee("David", 45000),
                new Employee("Eva", 60000)
        );

        //Approach 1 --> when we can sort the existing list in place [no additional space required]

        employees.sort(Comparator.comparingDouble(Employee::getSalary).thenComparing(Employee::getName, Comparator.reverseOrder()));

        employees.forEach(e -> System.out.print(e));

        //Approach 2 --> using Streams && additional space required will be required

        List<Employee> sortedEmployees = employees.stream().sorted(Comparator.comparing(Employee::getSalary).
                thenComparing(Employee::getName, Comparator.reverseOrder())).collect(Collectors.toList());

        sortedEmployees.forEach(e -> System.out.print(e));

    }

    static class Employee {
        private final String name;
        private final double salary;

        public Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return "Employee{name='" + name + "', salary=" + salary + "}";
        }
    }
}
