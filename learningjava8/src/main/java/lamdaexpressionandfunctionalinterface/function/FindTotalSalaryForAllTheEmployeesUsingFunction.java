package lamdaexpressionandfunctionalinterface.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class FindTotalSalaryForAllTheEmployeesUsingFunction {

    public static void main(String[] args) {
        Employee eOne = new Employee(123, "ABC", 100000, "Developer");
        Employee eTwo = new Employee(234, "CDE", 150000, "Developer");
        Employee eThree = new Employee(345, "PQR", 120000, "Developer");
        Employee eFour = new Employee(567, "MNP", 180000, "Developer");

        List<Employee> employeeList = Arrays.asList(eOne, eTwo, eThree, eFour);

        Function<List<Employee>, Double> totalSalaryFunction = employees -> {
            double totalSalaryForAllEmployees = 0.0;
            for (Employee employee : employees) {
                totalSalaryForAllEmployees += employee.getSalary();
            }
            return totalSalaryForAllEmployees;
        };
        System.out.println("Total Salary for all the employees :: " + totalSalaryFunction.apply(employeeList));
    }
}
