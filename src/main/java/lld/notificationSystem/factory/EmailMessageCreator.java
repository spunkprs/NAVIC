package lld.notificationSystem.factory;

import lld.notificationSystem.model.EmailMessage;
import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.validation.ValidationStrategy;

public class EmailMessageCreator implements MessageCreator {

    private ValidationStrategy validationStrategy;

    public EmailMessageCreator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    @Override
    public Message createMessage(String content, String from, String to) {
        if (!validationStrategy.validateMessage(content, from, to)) {
            throw new IllegalArgumentException("Invalid email message");
        }
        return new EmailMessage(from, to, content);
    }
}
