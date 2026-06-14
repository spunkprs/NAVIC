package jpmorgan.functionalprogrammingpractice;

import java.util.*;
import java.util.stream.Collectors;

/**
Requirements:-

a.) Ignore employees with null departments.
b.) Ignore employees with null names.
c.) employees itself may be null.
d.) Names within a department should be unique.
e.) Use mapping() as part of the solution.
f.) Avoid explicit loops.
 * */

public class MappingUsageExample {


    public static void main(String ar[]) {

    }

    public Map<String, Set<String>> departmentToEmployeeNames(
            List<Employee> employees) {

        /**
         Approach 1

         Map<String, List<Employee>> mapOne = Optional.ofNullable(employees).
         orElseGet(Collections:: emptyList).
         stream().
         filter(employee -> employee.getDepartment() != null).
         filter(employee -> employee.getName() != null)
         .collect(Collectors.groupingBy(Employee::getDepartment));

         Map<String, Set<String>> mapTwo = mapOne.entrySet().stream().collect(Collectors.toMap(a-> a.getKey(),
         a -> a.getValue().stream().map(c -> c.getName()).collect(Collectors.toSet())));

         * */

        //Approach 2
        return Optional.ofNullable(employees).
                orElseGet(Collections:: emptyList).
                stream().
                filter(employee -> employee.getDepartment() != null).
                filter(employee -> employee.getName() != null)
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.mapping(Employee::getName, Collectors.toSet())));
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

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
