package lamdaexpressionandfunctionalinterface.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerChainingTest {


    /*
    This application first computes percentage of student followed by grade then status then display information
    of all students
    * */
    public static void main(String[] args) {
        Student sOne = new Student("ABC", 500, 177);
        Student sTwo = new Student("PQR", 500, 389);
        Student sThree = new Student("MNP", 500, 442);
        Student sFour = new Student("JKL", 500, 309);
        Student sFive = new Student("QQQ", 500, 167);

        List<Student> studentList = Arrays.asList(sOne, sTwo, sThree, sFour, sFive);

        Consumer<Student> computePercentage = student -> {
            student.setPercentage((student.getTotalMarksObtained()/ student.getTotalMarks()) * 100.0);
        };

        Consumer<Student> computeGrade = student -> {
            if (student.getPercentage() >= 85.0) {
                student.setGrade('A');
            } else if (student.getPercentage() >= 65 && student.getPercentage() < 85.0) {
                student.setGrade('B');
            } else if (student.getPercentage() >= 50 && student.getPercentage() < 65.0) {
                student.setGrade('C');
            } else if (student.getPercentage() >= 35 && student.getPercentage() < 50.0) {
                student.setGrade('D');
            } else {
                student.setGrade('F');
            }
        };

        Consumer<Student> computeStatus = student -> {
            student.setStudentStatus(student.getGrade() == 'F' ? "FAIL" : "PASS");
        };

        Consumer<Student> displayInformation = student -> {
            System.out.println(student);
            System.out.println(student.getGrade());
            System.out.println(student.getStudentStatus());
            System.out.println();
        };

        for (Student student : studentList) {
            computePercentage
                    .andThen(computeGrade)
                    .andThen(computeStatus)
                    .andThen(displayInformation)
                    .accept(student);
        }
    }
}
