package designPatterns.extensibleEnumPattern;

public class Runner {

    public static void main(String ar[]) {
        String inputOne = "   HELLO !!!   ";
        String inputTwo = "Hello !!!";
        String inputThree = "Hello !!!";

        /**
        Exploring polymorphism
         * */

        Operation instanceOne = BasicOperation.TRIM;
        Operation instanceTwo = BasicOperation.LOWER_CASE;
        Operation instanceThree = BasicOperation.UPPER_CASE;

        System.out.println("Output post trimming for input " + inputOne + " is " + instanceOne.apply(inputOne));
        System.out.println("Lower case output for input " + inputTwo + " is " + instanceTwo.apply(inputOne));
        System.out.println("Upper case output for input " + inputThree + " is " + instanceThree.apply(inputOne));
    }
}
