package lld.notificationSystem.strategy.mail;

public class MailSenderService {

    public void sendMail(String content, String fromMailAddress, String toMailAddress) {
        //Logic for sending mail
        System.out.print("Mail sent with content " + content + " from address " + fromMailAddress + " to mail address " + toMailAddress);
    }
}
