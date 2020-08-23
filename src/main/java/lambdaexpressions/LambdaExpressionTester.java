package lambdaexpressions;

public class LambdaExpressionTester {

    public static void main(String ar[]) {
        MathOperation addition = (int a, int b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (int a, int b) -> { return a * b; };
        MathOperation division = (a, b) -> a / b;

        System.out.println("performing addition" + " " + "10 + 5 = " + addition.operation(10, 5));
        System.out.println("performing subtraction" + " " + "10 - 5 = " + subtraction.operation(10, 5));
        System.out.println("performing multiplication" + " " + "10 * 5 = " + multiplication.operation(10, 5));
        System.out.println("performing multiplication" + " " + "10 / 5 = " + division.operation(10, 5));


        GreetingsService greetService1 = message -> {System.out.println("Hello " + message);};

        GreetingsService greetService2 = (message) -> System.out.println("Hello " + message);

        greetService1.sayMessage("Prateek");
        greetService2.sayMessage("Piyush");
    }
}
