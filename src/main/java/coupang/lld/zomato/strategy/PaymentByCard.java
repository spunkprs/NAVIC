package coupang.lld.zomato.strategy;

import coupang.lld.zomato.model.payment.PaymentRequest;

public class PaymentByCard implements PaymentStrategy {

    @Override
    public boolean processPayment(PaymentRequest paymentRequest, double amount) {
        //Implementation details can be added as part of v2
        return false;
    }
}
