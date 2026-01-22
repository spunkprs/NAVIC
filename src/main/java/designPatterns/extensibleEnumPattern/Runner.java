package designPatterns.extensibleEnumPattern;

public class Runner {

    public static void main(String ar[]) {
        String inputOne = "   HELLO !!!   ";
        String inputTwo = "Hello !!!";
        String inputThree = "Hello !!!";

        System.out.println("Output post trimming for input " + inputOne + " is " + BasicOperation.TRIM.apply(inputOne));
        System.out.println("Lower case output for input " + inputTwo + " is " + BasicOperation.LOWER_CASE.apply(inputOne));
        System.out.println("Upper case output for input " + inputThree + " is " + BasicOperation.UPPER_CASE.apply(inputOne));
    }
}
