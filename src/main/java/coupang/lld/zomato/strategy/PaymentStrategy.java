package coupang.lld.zomato.strategy;

import coupang.lld.zomato.model.payment.PaymentRequest;

public interface PaymentStrategy {

    boolean processPayment(PaymentRequest paymentRequest, double amount);
}
