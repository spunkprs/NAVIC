package lld.notificationSystem.strategy.whatsapp;

import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.SendNotificationOverChannelStrategy;

public class WhatsAppStrategy implements SendNotificationOverChannelStrategy {

    @Override
    public boolean sendMessage(Message message) {
        return false;
    }
}
