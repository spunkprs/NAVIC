package multithreading.basics.atomics;


import java.util.concurrent.atomic.AtomicInteger;


/*
There are a total of sixteen atomic classes divided into four groups:

a.) Scalars
b.) Field updaters
c.) Arrays
d.) Compound variables

Most well-known and commonly used are the scalar ones such as AtomicInteger, AtomicLong, AtomicReference, which support the CAS (compare-and-set)
Other primitive types such as double and float can be simulated by casting short or byte values to and from int and using methods floatToIntBits()
and doubleToLongBits() for floating point numbers. Atomic scalar classes extend from Number and don’t redefine hashCode() or equals()

Integer class has the same hashcode for the same integer value but that’s not the case for AtomicInteger.
Thus Atomic* scalar classes are unsuitable as keys for collections that rely on hashcode
* */

public class NonPrimitivesDemonstration {

        public static void main( String args[] ) {
            AtomicInteger atomicIntegerOne = new AtomicInteger(5);
            AtomicInteger atomicIntegerTwo = new AtomicInteger(5);

            System.out.println("atomicIntegerOne.equals(atomicIntegerTwo) : " + atomicIntegerOne.equals(atomicIntegerTwo));
            System.out.println("atomicIntegerOne.hashCode() == atomicIntegerTwo.hashCode() : "
                    + (atomicIntegerOne.hashCode() == atomicIntegerTwo.hashCode()));


            Integer integer1 = new Integer(23235);
            Integer integer2 = new Integer(23235);

            System.out.println("integer1.equals(integer2) : " + integer1.equals(integer2));
            System.out.println("integer1.hashCode() == integer2.hashCode() : " + (integer1.hashCode() == integer2.hashCode()));
        }
}
