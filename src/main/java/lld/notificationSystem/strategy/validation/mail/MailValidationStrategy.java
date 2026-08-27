package lld.notificationSystem.strategy.validation.mail;

import lld.notificationSystem.strategy.validation.ValidationStrategy;

public class MailValidationStrategy implements ValidationStrategy {

    @Override
    public boolean validateMessage(String content, String fromAddress, String toAddress) {
        return !content.isEmpty() && !fromAddress.isEmpty() && fromAddress.contains("@")
                && !toAddress.isEmpty() && toAddress.contains("@");
    }
}
