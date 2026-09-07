package lld.zomato.resolver;

import lld.zomato.model.payment.CardPaymentRequest;
import lld.zomato.model.payment.NetBankingPaymentRequest;
import lld.zomato.model.payment.PaymentRequest;
import lld.zomato.model.payment.UpiPaymentRequest;
import lld.zomato.strategy.PaymentByCard;
import lld.zomato.strategy.PaymentByNetBanking;
import lld.zomato.strategy.PaymentByUpi;
import lld.zomato.strategy.PaymentStrategy;

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
