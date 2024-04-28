package multithreading.cyclicbarrier;


/*
* Useful when x number of independent needs to be triggered independently {breaking down a large problem into multiple
* smaller problems that can be taken care of independently}, catch is all those dependent tasks can perform some pre-waiting
* work, post that they need to wait for each other && once all dependent tasks have accomplished that pre waiting work, barrier
* is tripped && post that all the dependent task can continue with their remaining work at their own pace
*
* Useful links -->
* a.) https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/CyclicBarrier.html
* */
public class CyclicBarrierApplication {

    public static void main(String ar[]) {

    }
}
