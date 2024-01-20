package lamdaexpressionandfunctionalinterface.functionalinterface.defaultmethods;

public class ImplementationClassOne implements Left, Right {

    /*
    Access specifier of overriding method always has to be public otherwise compilation issues
    would be seen leading to weak access modifier presence
    */
    public void method() {
        /*
        Way to call default method of parent interfaces
        * */
        Left.super.method();
        Right.super.method();
        System.out.println("Inside overriding method of Implementing class");
    }
}
