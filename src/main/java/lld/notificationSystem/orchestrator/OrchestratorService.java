package lld.notificationSystem.orchestrator;

import lld.notificationSystem.channel.Channels;
import lld.notificationSystem.factory.MessageCreationFactory;
import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.SendNotificationOverChannelStrategy;
import lld.notificationSystem.strategy.mail.MailSenderService;
import lld.notificationSystem.strategy.mail.MailStrategy;
import lld.notificationSystem.strategy.sms.SmsSenderService;
import lld.notificationSystem.strategy.sms.SmsStrategy;

import java.util.HashMap;
import java.util.Map;

public class OrchestratorService {

    private MessageCreationFactory messageCreationFactory;
    private Map<Channels, SendNotificationOverChannelStrategy> strategyMap;
    private SmsSenderService smsSenderService;
    private MailSenderService mailSenderService;

    public OrchestratorService(MessageCreationFactory messageCreationFactory, SmsSenderService smsSenderService,
                               MailSenderService mailSenderService) {
        this.messageCreationFactory = messageCreationFactory;
        this.smsSenderService = smsSenderService;
        this.mailSenderService = mailSenderService;
        this.strategyMap = new HashMap<>();
        this.strategyMap.put(Channels.SMS, new SmsStrategy(this.smsSenderService));
        this.strategyMap.put(Channels.MAIL, new MailStrategy(this.mailSenderService));
        //Will add WhatsAppStrategy to it once I have WhatsAppSenderService ready
    }

    public void sendNotification(String content, String fromAddress, String toAddress, Channels channels) {
        SendNotificationOverChannelStrategy strategy = this.strategyMap.get(channels);
        Message notificationMessage = messageCreationFactory.createMessage(channels, content, fromAddress, toAddress);
        strategy.sendMessage(notificationMessage);
    }
}
