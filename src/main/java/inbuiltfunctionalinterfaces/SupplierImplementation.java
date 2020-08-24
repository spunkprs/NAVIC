package inbuiltfunctionalinterfaces;

import java.util.function.Supplier;

public class SupplierImplementation {

    /**
     *
     * Few of the useful links for the same :-
     * 1.) https://www.baeldung.com/java-8-functional-interfaces
     * 2.) https://examples.javacodegeeks.com/core-java/java-8-consumer-supplier-example/
     */
    /*
     */

    public static void main(String ar[]) {

        System.out.println("Going to compute square && cube of 7 lazily");
        int numOne = 7;
        Supplier<Double> supplierOne = () -> {
            try {
                System.out.println("Sleep induced before computing square of number !!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Double.valueOf(numOne);};

        Supplier<Double> supplierTwo = () -> {
            try {
                System.out.println("Sleep induced before computing cube of number !!");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Double.valueOf(numOne);};

        System.out.println("Square of " + numOne + " is " + squareLazy(supplierOne));
        System.out.println("Cube of " + numOne + " is " + cubeLazy(supplierTwo));
    }

    public static double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    public static double cubeLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 3);
    }
}
