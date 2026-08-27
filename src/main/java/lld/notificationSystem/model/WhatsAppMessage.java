package lld.notificationSystem.model;

public class WhatsAppMessage extends Message {

    private long fromNumber;
    private long toNumber;

    public WhatsAppMessage(String messageContent, long fromNumber, long toNumber) {
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
