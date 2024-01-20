package lamdaexpressionandfunctionalinterface.functionalinterface;

public class TestNumbersAddition {

    public static void main(String ar[]) {
        NumbersAddition naImplementationOne = (numOne, numTwo) ->  numOne + numTwo;
        NumbersAddition naImplementationTwo = (int numOne, int numTwo) ->  {
            System.out.println("Addition of numbers " + numOne + " and " + numTwo + " going to happen");
            return numOne + numTwo;
        };

        System.out.println("First lambda expression implementation " + naImplementationOne.add(4, 5));
        System.out.println("Second lambda expression implementation " + naImplementationTwo.add(4, 5));
    }
}
