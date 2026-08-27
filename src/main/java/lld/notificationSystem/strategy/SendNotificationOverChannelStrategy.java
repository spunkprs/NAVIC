package lld.notificationSystem.strategy;

import lld.notificationSystem.model.Message;

public interface SendNotificationOverChannelStrategy {

    boolean sendMessage(Message message);
}
