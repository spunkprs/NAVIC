package multithreading.basics.immutable;

/*
*
* Have made class final && all it's fields as final too
* NO setter methods have been exposed to alter the state of the object
* */

public final class Holder {

    private final int n;

    public Holder(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }
}
