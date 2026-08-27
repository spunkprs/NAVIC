package lld.notificationSystem.strategy.validation.sms;

import lld.notificationSystem.strategy.validation.ValidationStrategy;

public class SmsValidationStrategy implements ValidationStrategy {

    @Override
    public boolean validateMessage(String content, String fromAddress, String toAddress) {
        try {
            Long.parseLong(fromAddress);
            Long.parseLong(toAddress);
        } catch (NumberFormatException e) {
            System.out.print(e.getMessage());
            return false;
        }
        return !content.isEmpty() && fromAddress.length() == 10 && toAddress.length() == 10;
    }
}
