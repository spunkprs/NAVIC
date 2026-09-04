package lld.jobScheduler.model;

/**
Payload that will be executed by the worker threads, could have made it non runnable too but since
I am pushing it to ExecutorService hence made it a Runnable
 * */

public class Payload implements Runnable {

    private String attachedPayload;

    public Payload(String attachedPayload) {
        this.attachedPayload = attachedPayload;
    }

    @Override
    public void run() {
        System.out.print("Executing payload " + this.attachedPayload);
    }
}
