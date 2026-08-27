package lld.notificationSystem.factory;

import lld.notificationSystem.model.Message;

public interface MessageCreator {
     Message createMessage(String content, String fromAddress, String toAddress);
}
