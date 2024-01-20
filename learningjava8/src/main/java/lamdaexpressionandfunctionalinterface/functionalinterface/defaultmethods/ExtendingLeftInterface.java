package lamdaexpressionandfunctionalinterface.functionalinterface.defaultmethods;

public interface ExtendingLeftInterface extends Left {

    default void method() {
        System.out.println("Inside default method for interface ExtendingLeftInterface");
    }
}
