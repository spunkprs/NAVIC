package designPatterns.extensibleEnumPattern;

public class Runner {

    public static void main(String ar[]) {
        String inputOne = "   HELLO !!!   ";
        String inputTwo = "HELLO !!";
        String inputThree = "hello !!";

        /**
        Exploring polymorphism
         * */

        Operation instanceOne = BasicOperation.TRIM;
        Operation instanceTwo = BasicOperation.LOWER_CASE;
        Operation instanceThree = BasicOperation.UPPER_CASE;
        Operation instanceFour = ExtendedOperation.MD5_ENCODE;

        System.out.println("Output post trimming for input " + inputOne + " is " + instanceOne.apply(inputOne));
        System.out.println("Lower case output for input " + inputTwo + " is " + instanceTwo.apply(inputTwo));
        System.out.println("Upper case output for input " + inputThree + " is " + instanceThree.apply(inputThree));

        System.out.println("Encoded output for input " + inputTwo + " is " + instanceFour.apply(inputTwo));
    }
}
