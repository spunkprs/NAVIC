package lld.zomato.strategy;

import lld.zomato.model.payment.PaymentRequest;

public interface PaymentStrategy<T extends PaymentRequest> {

    boolean processPayment(T paymentRequest, double amount);
}
