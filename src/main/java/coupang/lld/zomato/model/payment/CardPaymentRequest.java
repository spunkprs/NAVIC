package coupang.lld.zomato.model.payment;

public class CardPaymentRequest extends PaymentRequest {

    private String encryptedCardNumber;
    private String encryptedCvv;
    private String encryptedCardExpiryInformation;

    public CardPaymentRequest(String encryptedCardNumber, String encryptedCvv, String encryptedCardExpiryInformation, String userId) {
        super(userId);
        this.encryptedCardNumber = encryptedCardNumber;
        this.encryptedCvv = encryptedCvv;
        this.encryptedCardExpiryInformation = encryptedCardExpiryInformation;
    }
}
