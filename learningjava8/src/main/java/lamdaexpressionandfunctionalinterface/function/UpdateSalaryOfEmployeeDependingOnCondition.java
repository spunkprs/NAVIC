package lamdaexpressionandfunctionalinterface.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class UpdateSalaryOfEmployeeDependingOnCondition {

    public static void main(String[] args) {
        Employee eOne = new Employee(123, "ABC", 100000, "Developer");
        Employee eTwo = new Employee(234, "CDE", 150000, "Manager");
        Employee eThree = new Employee(345, "PQR", 120000, "Developer");
        Employee eFour = new Employee(567, "MNP", 180000, "Manager");

        List<Employee> employeeList = Arrays.asList(eOne, eTwo, eThree, eFour);

        System.out.println("Printing employees before hike !! " + employeeList);

        //Increase salary by 10000 only for those employees who are not managers

        Predicate<Employee> predicate = employee -> employee.getDesignation().equals("Manager");

        Function<Employee, Employee> function = employee -> {
            employee.setSalary(employee.getSalary() + 10000);
            return employee;
        };

        for (Employee e : employeeList) {
            if (predicate.negate().test(e)) {
                function.apply(e);
            }
        }

        System.out.println("Printing employees post hike !! " + employeeList);
    }

}
