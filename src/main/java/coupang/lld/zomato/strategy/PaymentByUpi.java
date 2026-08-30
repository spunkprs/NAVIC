package coupang.lld.zomato.strategy;

import coupang.lld.zomato.model.payment.UpiPaymentRequest;

public class PaymentByUpi implements PaymentStrategy<UpiPaymentRequest> {

    @Override
    public boolean processPayment(UpiPaymentRequest paymentRequest, double amount) {
        //Implementation details can be added as part of v2
        return false;
    }
}
