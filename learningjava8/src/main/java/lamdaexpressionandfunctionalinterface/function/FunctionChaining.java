package lamdaexpressionandfunctionalinterface.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FunctionChaining {

    public static void main(String[] args) {
        Employee eOne = new Employee(123, "ABC", 100000, "Developer");
        Employee eTwo = new Employee(234, "CDE", 150000, "Manager");
        Employee eThree = new Employee(345, "PQR", 120000, "Developer");
        Employee eFour = new Employee(567, "MNP", 180000, "Manager");

        List<Employee> employeeList = Arrays.asList(eOne, eTwo, eThree, eFour);

        System.out.println("Printing employees before modification !! " + employeeList);

        //First increase salary of every employee by 2000 then update their username by appending @test.com to it

        Function<Employee, Employee> functionOne = employee -> {
            employee.setSalary(employee.getSalary() + 2000);
            return employee;
        };

        Function<Employee, Employee> functionTwo = employee -> {
            employee.setUserName(employee.getUserName().toLowerCase() + "@test.com");
            return employee;
        };

        for (Employee e : employeeList) {
            functionOne.andThen(functionTwo).apply(e);
        }
        System.out.println("Printing employees post modification !! " + employeeList);
    }
}
