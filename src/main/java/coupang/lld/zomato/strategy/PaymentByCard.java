package coupang.lld.zomato.strategy;

import coupang.lld.zomato.model.payment.CardPaymentRequest;

public class PaymentByCard implements PaymentStrategy<CardPaymentRequest> {

    @Override
    public boolean processPayment(CardPaymentRequest paymentRequest, double amount) {
        //Implementation details can be added as part of v2
        return false;
    }

}
