package lamdaexpressionandfunctionalinterface.supplier;

import java.util.function.Supplier;

public class GenerateOTPUsingSupplier {

    /*
    Ask here is to generate 6 digit number && then print it to console
    * */
    public static void main(String[] args) {
        Supplier<String> sixDigitOTP = () -> {
            String otp = "";
            for (int i = 1; i <= 6; i++) {
                otp += ((int)(Math.random() * 10));
            }
            return otp;
        };
        System.out.println("Six digit OTP is ready " + sixDigitOTP.get());
    }
}
