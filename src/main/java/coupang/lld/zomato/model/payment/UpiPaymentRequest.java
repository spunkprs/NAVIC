package coupang.lld.zomato.model.payment;

public class UpiPaymentRequest extends PaymentRequest {

    private String upiId;

    public UpiPaymentRequest(String upiId, String userId) {
        super(userId);
        this.upiId = upiId;
    }
}
