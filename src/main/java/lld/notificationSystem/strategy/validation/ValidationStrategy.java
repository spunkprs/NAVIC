package lld.notificationSystem.strategy.validation;

public interface ValidationStrategy {

    boolean validateMessage(String content, String fromAddress, String toAddress);
}
