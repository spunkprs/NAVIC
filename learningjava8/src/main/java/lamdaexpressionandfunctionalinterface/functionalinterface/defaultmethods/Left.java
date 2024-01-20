package lamdaexpressionandfunctionalinterface.functionalinterface.defaultmethods;


public interface Left {
    default void method() {
        System.out.println("Inside default method for interface Left");
    }
}
