package lamdaexpressionandfunctionalinterface.functionalinterface.defaultmethods;


public interface Right {

    default void method() {
        System.out.println("Inside default method for interface Right");
    }
}
