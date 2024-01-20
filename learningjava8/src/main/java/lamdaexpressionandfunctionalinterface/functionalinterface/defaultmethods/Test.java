package lamdaexpressionandfunctionalinterface.functionalinterface.defaultmethods;

public class Test {

    public static void main(String[] args) {
        /*
        https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html#:~:text=Default%20methods%20enable%20you%20to%20add%20new%20functionality%20to%20existing,as%20parameters%20to%20existing%20interfaces.
        * */

        ImplementationClassOne implementationClassOne = new ImplementationClassOne();
        implementationClassOne.method();

        /*
        * Making use of anonymous inner class here as method is not a abstract rather default method
        hence can't make use of lambda expression
        * */
        ExtendingLeftInterface anonymousInnerClassLeftInterface = new ExtendingLeftInterface() {
            @Override
            public void method() {
            ExtendingLeftInterface.super.method();
            }
        };
        anonymousInnerClassLeftInterface.method();

        ExtendingRightInterface anonymousInnerClassRightInterface = () -> {
          System.out.println("Providing implementation for abstract method present in interface ExtendingRightInterface")  ;
        };
        anonymousInnerClassRightInterface.method();
    }
}
