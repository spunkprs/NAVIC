package lamdaexpressionandfunctionalinterface.function;

public class Employee {

    private int employeeId;
    private String employeeName;
    private double salary;
    private String designation;
    private String userName;

    public Employee(int employeeId, String employeeName, double salary, String designation) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.designation = designation;
        this.userName = employeeName;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getSalary() {
        return salary;
    }

    public String getDesignation() {
        return designation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", designation='" + designation + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
