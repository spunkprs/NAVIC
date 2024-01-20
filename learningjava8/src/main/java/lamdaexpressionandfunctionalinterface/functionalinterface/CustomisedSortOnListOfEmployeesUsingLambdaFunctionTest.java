package lamdaexpressionandfunctionalinterface.functionalinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomisedSortOnListOfEmployeesUsingLambdaFunctionTest {


    static class Employee {
        private int employeeNumber;
        private String employeeName;


        public int getEmployeeNumber() {
            return employeeNumber;
        }

        public String getEmployeeName() {
            return employeeName;
        }

        public Employee(int employeeNumber, String employeeName) {
            this.employeeNumber = employeeNumber;
            this.employeeName = employeeName;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "employeeNumber=" + employeeNumber +
                    ", employeeName='" + employeeName + '\'' +
                    '}';
        }
    }

    /*
    This exercise aims at sorting employees by their name in ascending order, in case of collision on names employee
    shall be sorted in ascending order of their employee number
    * */
    public static void main(String ar[]) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(100, "Prateek"));
        employeeList.add(new Employee(200, "Naina"));
        employeeList.add(new Employee(300, "Shaa"));
        employeeList.add(new Employee(400, "Naina"));
        employeeList.add(new Employee(500, "Prateek"));

        //Printing list of employees before customized sort
        System.out.println(employeeList);

        /*Comparator<Employee> employeeComparator = (empOne, empTwo) -> {
            int num = empOne.getEmployeeName().compareTo(empTwo.getEmployeeName());
            if (num < 0) {
                return -1;
            } else if (num > 0) {
                return 1;
            } else {
                return empOne.getEmployeeNumber() < empTwo.getEmployeeNumber() ? -1 :
                        empOne.getEmployeeNumber() > empTwo.getEmployeeNumber() ? 1 : 0;
            }
        };*/

        //Printing list of employees before customized sort
        Collections.sort(employeeList, (empOne, empTwo) -> {
            int num = empOne.getEmployeeName().compareTo(empTwo.getEmployeeName());
            if (num < 0) {
                return -1;
            } else if (num > 0) {
                return 1;
            } else {
                return empOne.getEmployeeNumber() < empTwo.getEmployeeNumber() ? -1 :
                        empOne.getEmployeeNumber() > empTwo.getEmployeeNumber() ? 1 : 0;
            }
        });
        System.out.println(employeeList);
    }
}
