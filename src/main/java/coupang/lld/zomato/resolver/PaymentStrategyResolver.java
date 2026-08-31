package coupang.lld.zomato.resolver;

import coupang.lld.zomato.model.payment.CardPaymentRequest;
import coupang.lld.zomato.model.payment.NetBankingPaymentRequest;
import coupang.lld.zomato.model.payment.PaymentRequest;
import coupang.lld.zomato.model.payment.UpiPaymentRequest;
import coupang.lld.zomato.strategy.PaymentByCard;
import coupang.lld.zomato.strategy.PaymentByNetBanking;
import coupang.lld.zomato.strategy.PaymentByUpi;
import coupang.lld.zomato.strategy.PaymentStrategy;

import java.util.HashMap;
import java.util.Map;

public class PaymentStrategyResolver {

    private final Map<Class<? extends PaymentRequest>, PaymentStrategy<? extends PaymentRequest>> strategyMap;

    public PaymentStrategyResolver() {
        strategyMap = new HashMap<>();
        strategyMap.put(UpiPaymentRequest.class, new PaymentByUpi());
        strategyMap.put(NetBankingPaymentRequest.class, new PaymentByNetBanking());
        strategyMap.put(CardPaymentRequest.class, new PaymentByCard());
    }

    @SuppressWarnings("unchecked")
    public boolean process(PaymentRequest paymentRequest, double amount) {
        PaymentStrategy<PaymentRequest> strategy =
                (PaymentStrategy<PaymentRequest>) strategyMap.get(paymentRequest.getClass());

        if (strategy == null) {
            throw new UnsupportedOperationException("No strategy registered for " + paymentRequest.getClass());
        }
        return strategy.processPayment(paymentRequest, amount);
    }
}
