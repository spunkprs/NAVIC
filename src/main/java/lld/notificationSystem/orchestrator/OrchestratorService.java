package lld.notificationSystem.orchestrator;

import lld.notificationSystem.channel.Channels;
import lld.notificationSystem.factory.MessageCreationFactory;
import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.SendNotificationOverChannelStrategy;
import java.util.Map;

public class OrchestratorService {

    private MessageCreationFactory messageCreationFactory;
    private Map<Channels, SendNotificationOverChannelStrategy> strategyMap;

    /**
     strategyMap.put(
     Channels.SMS,
     new RetryingNotificationSender(new SmsStrategy(smsSenderService), 4)
     );

     strategyMap.put(
     Channels.SMS,
     new RetryingNotificationSender(new MailStrategy(smsSenderService), 4)
     );

     strategyMap.put(
     Channels.SMS,
     new RetryingNotificationSender(new WhatsAppStrategy(smsSenderService), 4)
     );

     OR

     strategyMap.put(
     Channels.SMS,
     new WhatsAppStrategy(smsSenderService)
     );

     We can choose to skip retries entirely for some of the strategy hence decorator is the best pattern to be made use of

     * */

    public OrchestratorService(MessageCreationFactory messageCreationFactory,
                               Map<Channels, SendNotificationOverChannelStrategy> strategyMap) {
        this.messageCreationFactory = messageCreationFactory;
        this.strategyMap = strategyMap;
    }

    public void sendNotification(String content, String fromAddress, String toAddress, Channels channels) {
        SendNotificationOverChannelStrategy strategy = this.strategyMap.get(channels);
        Message notificationMessage = messageCreationFactory.createMessage(channels, content, fromAddress, toAddress);
        strategy.sendMessage(notificationMessage);
    }
}
