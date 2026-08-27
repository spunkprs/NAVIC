package lld.notificationSystem.model;

public class EmailMessage extends Message {

    private String fromAddress;
    private String toAddress;

    public EmailMessage(String fromAddress, String toAddress, String messageContent) {
        super(messageContent);
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
    }

    @Override
    public String fromAddress() {
        return this.fromAddress;
    }

    @Override
    public String toAddress() {
        return this.toAddress;
    }
}
