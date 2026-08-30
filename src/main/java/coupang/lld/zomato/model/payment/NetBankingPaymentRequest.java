package coupang.lld.zomato.model.payment;

public class NetBankingPaymentRequest extends PaymentRequest {

    private String uniqueNbSpecificId;
    private String encryptedPassword;

    public NetBankingPaymentRequest(String userId, String uniqueNbSpecificId, String encryptedPassword) {
        super(userId);
        this.uniqueNbSpecificId = uniqueNbSpecificId;
        this.encryptedPassword = encryptedPassword;
    }
}
