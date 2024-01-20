package lamdaexpressionandfunctionalinterface.consumer;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Test {

    public static void main(String[] args) {
        /*
        This application will first compute percentage of marks for each student, then filter those students having percentage of marks
        >=60, then display student information to console
        * */

        Student sOne = new Student("ABC", 500, 197);
        Student sTwo = new Student("PQR", 500, 389);
        Student sThree = new Student("MNP", 500, 442);
        Student sFour = new Student("JKL", 500, 309);
        Student sFive = new Student("QQQ", 500, 167);

        List<Student> studentList = Arrays.asList(sOne, sTwo, sThree, sFour, sFive);

        Predicate<Student> filterStudentsOnPercentage = student -> student.getPercentage() >= 60.0;

        //Consumer to compute percentage of marks obtained
        Consumer<Student> computePercentage = student -> {
            student.setPercentage((student.getTotalMarksObtained()/student.getTotalMarks()) * 100.0);
        };

        for (Student s : studentList) {
            computePercentage.accept(s);
        }

        //Consumer to compute print information of students
        Consumer<Student> printStudentInformation = student -> {
            System.out.println(student);
        };

        for (Student s : studentList) {
            if (filterStudentsOnPercentage.test(s)) {
                printStudentInformation.accept(s);
            }
        }

    }
}
