package lamdaexpressionandfunctionalinterface.supplier;

import java.util.function.Supplier;

public class GenerateRandomPasswordUsingSupplier {

    /*
    Ask here is to generate password that abide by following rules :-
    1.) Length of password shall be 8
    2.) Digits shall be present at even positions i.e 2, 4, 6, 8
    3.) Upper Case characters && @, $, &, * shall be present at odd positions i.e 1, 3, 5, 7
    * */
    public static void main(String[] args) {

        Supplier <String> generateRandomDigitSupplier = () -> String.valueOf((int)(Math.random() * 10));
        String allowedAplhabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ@$&*";
        Supplier <String> generateAlphabetsAndSpecialCharacters = () -> String.valueOf(allowedAplhabets.charAt((int)(Math.random() * allowedAplhabets.length())));

        String randomPassword = "";
        for (int i = 1; i <= 8; i++) {
            if (i % 2 == 0) {
                randomPassword += generateRandomDigitSupplier.get();
            } else {
                randomPassword += generateAlphabetsAndSpecialCharacters.get();
            }
        }
        System.out.println("Generated random password :: " + randomPassword);
    }
}
