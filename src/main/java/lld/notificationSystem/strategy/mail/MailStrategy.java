package lld.notificationSystem.strategy.mail;

import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.SendNotificationOverChannelStrategy;

public class MailStrategy implements SendNotificationOverChannelStrategy {

    private MailSenderService mailSenderService;

    public MailStrategy(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @Override
    public boolean sendMessage(Message message) {
        String content = message.getMessageContent();
        String fromMailAddress = message.fromAddress();
        String toMailAddress = message.toAddress();

            try {
                mailSenderService.sendMail(content, fromMailAddress, toMailAddress);
                return true;
            } catch (Exception e) {
                System.out.print(e.getMessage());
                throw e;
            }
    }
}
