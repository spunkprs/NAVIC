package lld.zomato.strategy;

import lld.zomato.model.payment.NetBankingPaymentRequest;

public class PaymentByNetBanking implements PaymentStrategy<NetBankingPaymentRequest> {

    @Override
    public boolean processPayment(NetBankingPaymentRequest paymentRequest, double amount) {
        //Implementation details can be added as part of v2
        return false;
    }
}
