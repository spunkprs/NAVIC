package multithreading.basics.immutable;

/*
In this example we are making use of immutable object{state of the object can't be altered once created} &&
we are printing the state of the object in the multithreaded environment

Following are the practices to make any class immutable :-
a.) Make class final such that no class can inherit it && get the capacity to alter the state
b.) Make all the instance variables as final
c.) If the instance variables are non primitive then all the primitive instance variables inside that non primitive object shall also
be declared final
d.) No exposed setter methods
e.) No methods that exposes non primitive objects
*
* */


public class AccessingImmutableObjects {

    public static void main(String ar[]) {

        //Object once created can't be altered later
        Holder holder = new Holder(10);

        Thread threadOne = new Thread(() -> {
            System.out.println("Here is the holder obj getting printed in thread 1:: " + holder.getN());

        });

        Thread threadTwo = new Thread(() -> {
            System.out.println("Here is the holder obj getting printed in thread 2:: " + holder.getN());
        });

        //Starting the threads
        threadOne.start();
        threadTwo.start();
    }

}
