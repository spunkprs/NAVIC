package multithreading.basics.immutable;

/*
In this example we are making use of immutable object{state of the object can't be altered once created} &&
we are printing the state of the object in the multithreaded environment
*
* */


public class AccessingImmutableObjects {

    public static void main(String ar[]) {

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
