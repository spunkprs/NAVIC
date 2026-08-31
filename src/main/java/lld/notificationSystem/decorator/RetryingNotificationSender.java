package lld.notificationSystem.decorator;

import lld.notificationSystem.model.Message;
import lld.notificationSystem.strategy.SendNotificationOverChannelStrategy;

public class RetryingNotificationSender implements SendNotificationOverChannelStrategy {

    private SendNotificationOverChannelStrategy strategy;
    private int maxAttempts;

    public RetryingNotificationSender(SendNotificationOverChannelStrategy strategy, int maxAttempts) {
        this.strategy = strategy;
        this.maxAttempts = maxAttempts;
    }

    @Override
    public boolean sendMessage(Message message) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            if (strategy.sendMessage(message)) {
                return true;
            }
        }
        return false;
    }

    }

