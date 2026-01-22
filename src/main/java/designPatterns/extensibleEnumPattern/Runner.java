package designPatterns.extensibleEnumPattern;

/**
 No, one enum cannot extend another in Java. All enums in Java implicitly extend the abstract base class java.lang.Enum.
 Since Java does not support multiple inheritance for classes, an enum is restricted to this single parent class and cannot extend any other class or enum.

 Reasons for the Restriction:-

 a.) Single Inheritance: Java classes can only have one direct superclass. Enums use their single inheritance slot for java.lang.Enum.
 b.) Implicitly Final: Enums are implicitly final unless a constant has a class body, which prevents them from being subclassed by other enums or classes.
 c.) Type Safety: Enums are designed to represent a fixed, closed set of constants known at compile time.

 * */

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
