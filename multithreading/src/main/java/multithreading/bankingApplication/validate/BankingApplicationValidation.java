package multithreading.bankingApplication.validate;

import multithreading.bankingApplication.entity.Account;
import multithreading.bankingApplication.operations.AccountOperations;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BankingApplicationValidation {

    public static void main(String ar[]) {

        Account accOne = new Account();
        AtomicInteger balanceOne = new AtomicInteger();
        balanceOne.set(0);
        accOne.setAtomicBalance(balanceOne);
        accOne.setAccId(1);


        Account accTwo = new Account();
        AtomicInteger balanceTwo = new AtomicInteger();
        balanceTwo.set(50);
        accTwo.setAtomicBalance(balanceTwo);
        accTwo.setAccId(2);

        Account accThree = new Account();
        AtomicInteger balanceThree = new AtomicInteger();
        balanceThree.set(150);
        accThree.setAtomicBalance(balanceThree);
        accThree.setAccId(3);

        Account accFour = new Account();
        AtomicInteger balanceFour = new AtomicInteger();
        balanceFour.set(200);
        accFour.setAtomicBalance(balanceFour);
        accFour.setAccId(4);

        AccountOperations accountOperations = new AccountOperations();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.submit(() -> {
            accountOperations.deposit(accOne, 100);
        });

        executorService.submit(() -> {
            accountOperations.withdraw(accTwo, 50);
        });

        executorService.submit(() -> {
            accountOperations.transferFunds(accThree, accTwo, 150);
        });

        executorService.submit(() -> {
            accountOperations.transferFunds(accFour, accTwo, 200);
        });

        executorService.submit(() -> {
            accountOperations.transferFunds(accOne, accFour, 100);
        });

        executorService.shutdown();

        Map<Integer, Account> map = new HashMap<>();
        map.put(accOne.getAccId(), accOne);
        map.put(accTwo.getAccId(), accTwo);
        map.put(accThree.getAccId(), accThree);
        map.put(accFour.getAccId(), accFour);

        for (int i = 1; i <= 4; i++) {
            System.out.println("Balance in account having id " + i + " is " + map.get(i).getAtomicBalance().get());
        }

    }
}
