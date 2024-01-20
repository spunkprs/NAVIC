package lamdaexpressionandfunctionalinterface.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FilteringEmployeesUsingPredicate {

    public static void main(String[] args) {
        Employee eOne = new Employee(123, "XYZ",
                "Bangalore", 200000, "Developer");
        Employee eTwo = new Employee(124, "XYY",
                "Delhi", 250000, "Developer");
        Employee eThree = new Employee(125, "ABC",
                "Bangalore", 250000, "Manager");
        Employee eFour = new Employee(126, "PQR",
                "Hyderabad", 150000, "Developer");
        Employee eFive = new Employee(127, "MNP",
                "Bangalore", 190000, "Manager");

        List<Employee> employeeList = Arrays.asList(eOne, eTwo, eThree, eFour, eFive);

        //Print those employees who are based out of Bangalore && have salary >=200000
        List<Employee> result = new ArrayList<>();

        Predicate<Employee> predicateOne = employee -> employee.getEmployeeCity().equals("Bangalore");
        Predicate<Employee> predicateTwo = employee -> employee.getSalary() >= 200000;

        for (Employee employee : employeeList) {
            if (predicateOne.and(predicateTwo).test(employee)) {
                result.add(employee);
            }
        }
        System.out.println("Printing those employees who are based out of Bangalore && have salary >=200000 " + result);

        //Print those employees who are based out of Bangalore && are not Managers

        result = new ArrayList<>();

        Predicate<Employee> predicateThree = employee -> employee.getDesignation().equals("Manager");

        for (Employee employee : employeeList) {
            if (predicateOne.and(predicateThree.negate()).test(employee)) {
                result.add(employee);
            }
        }
        System.out.println("Printing those employees who are based out of Bangalore && are not Managers " + result);

        result = new ArrayList<>();

        //Print those employees who are not based out of Bangalore OR are not Managers

        for (Employee employee : employeeList) {
            if (predicateOne.negate().or(predicateThree.negate()).test(employee)) {
                result.add(employee);
            }
        }
        System.out.println("Printing those employees who are not based out of Bangalore OR are not Managers " + result);

    }

    static class Employee {
        private int employeeId;
        private String employeeName;
        private String employeeCity;
        private double salary;
        private String designation;

        public Employee(int employeeId, String employeeName, String employeeCity, double salary, String designation) {
            this.employeeId = employeeId;
            this.employeeName = employeeName;
            this.employeeCity = employeeCity;
            this.salary = salary;
            this.designation = designation;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "employeeId=" + employeeId +
                    ", employeeName='" + employeeName + '\'' +
                    ", employeeCity='" + employeeCity + '\'' +
                    ", salary=" + salary +
                    ", designation='" + designation + '\'' +
                    '}';
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public String getEmployeeCity() {
            return employeeCity;
        }

        public double getSalary() {
            return salary;
        }

        public String getDesignation() {
            return designation;
        }
    }
}
