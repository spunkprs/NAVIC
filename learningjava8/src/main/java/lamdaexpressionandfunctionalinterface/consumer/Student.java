package lamdaexpressionandfunctionalinterface.consumer;

public class Student {
    private String name;
    private double totalMarks;
    private double totalMarksObtained;
    private double percentage;
    private char grade;
    private String studentStatus;

    public Student(String name, double totalMarks, double totalMarksObtained) {
        this.name = name;
        this.totalMarks = totalMarks;
        this.totalMarksObtained = totalMarksObtained;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public double getTotalMarksObtained() {
        return totalMarksObtained;
    }

    public double getPercentage() {
        return percentage;
    }

    public char getGrade() {
        return grade;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", percentage=" + percentage +
                '}';
    }
}
