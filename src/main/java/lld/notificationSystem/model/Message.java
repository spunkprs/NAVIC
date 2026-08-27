package lld.notificationSystem.model;

public abstract class Message {
    private String messageContent;

    public Message(String messageContent) {
        this.messageContent = messageContent;
    }

    public abstract String fromAddress();
    public abstract String toAddress();

    public String getMessageContent() {
        return messageContent;
    }

}
