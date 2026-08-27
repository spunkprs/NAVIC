package lld.notificationSystem.strategy.sms;

public class SmsSenderService {

    public void sendSms(String content, long fromNumber, long toNumber) {
        //Logic for sending sms
        System.out.print("SMS sent with content " + content + " from number " + fromNumber + " to number " + toNumber);
    }
}
