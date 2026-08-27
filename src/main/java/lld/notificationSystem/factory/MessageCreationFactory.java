package lld.notificationSystem.factory;

import lld.notificationSystem.channel.Channels;
import lld.notificationSystem.model.EmailMessage;
import lld.notificationSystem.model.Message;
import lld.notificationSystem.model.SmsMessage;
import lld.notificationSystem.model.WhatsAppMessage;
import lld.notificationSystem.strategy.validation.ValidationStrategy;
import lld.notificationSystem.strategy.validation.mail.MailValidationStrategy;
import lld.notificationSystem.strategy.validation.sms.SmsValidationStrategy;

import java.util.HashMap;
import java.util.Map;

public class MessageCreationFactory {

    private Map<Channels, ValidationStrategy> validationStrategyMap;

    public MessageCreationFactory() {
        this.validationStrategyMap = new HashMap<>();
        this.validationStrategyMap.put(Channels.MAIL, new MailValidationStrategy());
        this.validationStrategyMap.put(Channels.SMS, new SmsValidationStrategy());
    }

    public Message createMessage(Channels channel, String content, String fromAddress, String toAddress) {
        if (Channels.MAIL == channel && this.validationStrategyMap.get(Channels.MAIL)
                .validateMessage(content, fromAddress, toAddress)) {
            return new EmailMessage(fromAddress, toAddress, content);
        } else if (Channels.SMS == channel && this.validationStrategyMap.get(Channels.SMS)
                .validateMessage(content, fromAddress, toAddress)) {
            return new SmsMessage(Long.parseLong(fromAddress), Long.parseLong(toAddress), content);
        } else {
            //Will add validation logic for WhatsApp too once I add validation against WhatsApp
            return new WhatsAppMessage(content, Long.parseLong(fromAddress), Long.parseLong(toAddress));
        }
    }
}
