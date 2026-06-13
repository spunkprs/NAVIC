package jpmorgan.functionalprogrammingpractice;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**

Requirements:-

a.) Return the highest-paid employee in each department.
b.) Ignore employees with null departments.
c.) Assume employees itself may be null.
d.) Use Streams and Collectors.
e.) Avoid explicit loops.

 * */

public class ExerciseTwo {

    public static void main(String ar[]) {

    }

    Map<String, Employee> highestPaidEmployeeByDepartment(
            List<Employee> employees) {

        /*
        Approach 1

        Map<String, List<Employee>> groupedMap = employees.stream().filter(employee -> employee.getDepartment() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment));

        groupedMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream().max(Comparator.comparingInt(Employee::getSalary))));

                        */

        /*
        Approach 2

        return Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(employee -> employee.getDepartment() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))
                ));

                */

        return Optional.ofNullable(employees)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(employee -> employee.getDepartment() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)), a -> a.get())
                ));
    }

    static class Employee {
        private String department;
        private String name;
        private int salary;

        public Employee(String department, String name, int salary) {
            this.department = department;
            this.name = name;
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public String getName() {
            return name;
        }

        public int getSalary() {
            return salary;
        }
    }
}
