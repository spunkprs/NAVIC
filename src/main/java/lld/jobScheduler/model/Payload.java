package lld.jobScheduler.model;

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
