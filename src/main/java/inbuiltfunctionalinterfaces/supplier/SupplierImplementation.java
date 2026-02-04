package inbuiltfunctionalinterfaces.supplier;

import java.util.function.Supplier;

public class SupplierImplementation {

    /**
     *
     * Few of the useful links for the same :-
     * 1.) https://www.baeldung.com/java-8-functional-interfaces
     * 2.) https://examples.javacodegeeks.com/core-java/java-8-consumer-supplier-example/

     Chaining is not possible with Supplier because of absence of default methods that were present in Predicate, Function && Consumer
     Reference --> https://medium.com/javarevisited/mastering-javas-functional-interfaces-consumer-biconsumer-and-supplier-2686e647619a
     */

    public static void main(String ar[]) {

        int numOne = 16;
        Supplier<Double> supplierOne = () -> {
            try {
                System.out.println("Sleep induced before computing square of number !!");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return  Double.valueOf(numOne);};

        Supplier<Double> supplierTwo = () -> {
            try {
                System.out.println("Sleep induced before computing cube of number !!");
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Double.valueOf(numOne);};

        Supplier<Double> supplierThree = () -> {
            try {
                System.out.println("Sleep induced before computing square root of number !!");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Math.sqrt(Double.valueOf(numOne));};

        System.out.println("Square of " + numOne + " is " + squareLazy(supplierOne));
        System.out.println("Cube of " + numOne + " is " + cubeLazy(supplierTwo));
        System.out.println("Square root of " + numOne + " is " + supplierThree.get());
    }

    public static double squareLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 2);
    }

    public static double cubeLazy(Supplier<Double> lazyValue) {
        return Math.pow(lazyValue.get(), 3);
    }
}
