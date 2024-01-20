package lamdaexpressionandfunctionalinterface.functionalinterface;

public class ImplementingRunnableUsingLambdaFunctionTest {

    public static void main(String ar[]) {
        System.out.println("On main thread !!");
        Runnable runnableInstance = () -> {
            System.out.println("Going to print first 10 numbers not on main thread !!");
            for (int i = 1; i <=10 ; i ++) {
                System.out.println(i);
            }
        };
        Thread t = new Thread(runnableInstance);
        t.start();
    }
}
