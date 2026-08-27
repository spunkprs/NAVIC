package lld.notificationSystem.strategy.sms;

import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.SendNotificationOverChannelStrategy;

public class SmsStrategy implements SendNotificationOverChannelStrategy {

    private SmsSenderService smsSenderService;

    public SmsStrategy(SmsSenderService smsSenderService) {
        this.smsSenderService = smsSenderService;
    }

    @Override
    public boolean sendMessage(Message message) {
        String content = message.getMessageContent();
            try {
                smsSenderService.sendSms(content, Long.parseLong(message.fromAddress()), Long.parseLong(message.toAddress()));
                return true;
            } catch (Exception e) {
                System.out.print(e.getMessage());
                throw e;
            }
    }
}
