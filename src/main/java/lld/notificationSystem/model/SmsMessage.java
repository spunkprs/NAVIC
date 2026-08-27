package lld.notificationSystem.model;

public class SmsMessage extends Message {

    private long fromNumber;
    private long toNumber;


    public SmsMessage(long fromNumber, long toNumber, String messageContent) {
        super(messageContent);
        this.fromNumber = fromNumber;
        this.toNumber = toNumber;
    }

    @Override
    public String fromAddress() {
        return String.valueOf(this.fromNumber);
    }

    @Override
    public String toAddress() {
        return String.valueOf(this.toNumber);
    }
}
