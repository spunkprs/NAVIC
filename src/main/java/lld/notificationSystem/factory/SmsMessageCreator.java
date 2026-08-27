package lld.notificationSystem.factory;

import lld.notificationSystem.model.Message;
import lld.notificationSystem.model.SmsMessage;
import lld.notificationSystem.strategy.validation.ValidationStrategy;

public class SmsMessageCreator implements MessageCreator {

    private ValidationStrategy validationStrategy;

    public SmsMessageCreator(ValidationStrategy validationStrategy) {
        this.validationStrategy = validationStrategy;
    }

    @Override
    public Message createMessage(String content, String fromAddress, String toAddress) {
        if (!validationStrategy.validateMessage(content, fromAddress, toAddress)) {
            throw new IllegalArgumentException("Invalid SMS message");
        }
        return new SmsMessage(Long.parseLong(fromAddress),
                Long.parseLong(toAddress), content);
    }
}
