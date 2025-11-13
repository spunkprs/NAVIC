package multithreading.bankingApplication.operations;

import multithreading.bankingApplication.entity.Account;

public class AccountOperations {

    public double fetchBalance(Account account) {
        return account.getAtomicBalance().get();
    }

    public void deposit(Account accountHolder, double amount) {
        if (amount > 0) {
            double existingBalance = accountHolder.getAtomicBalance().get();
            accountHolder.setBalance(existingBalance + amount);
            int attemptCount = 0;
            do {
                existingBalance = accountHolder.getAtomicBalance().get();
                attemptCount++;
            } while (!accountHolder.getAtomicBalance().compareAndSet(existingBalance, existingBalance + amount) && attemptCount <= 5);
            //Raise exception in case attemptCount >= 6
        }
    }

    public void withdraw(Account accountHolder, double amount) {
        if (amount > 0) {
            double existingBalance = accountHolder.getAtomicBalance().get();
            int attemptCount = 0;
            if (existingBalance >= amount) {
                do {
                    existingBalance = accountHolder.getAtomicBalance().get();
                    attemptCount++;
                } while (!accountHolder.getAtomicBalance().compareAndSet(existingBalance, existingBalance - amount) && attemptCount <= 5);
                //Raise exception in case attemptCount >= 6
            }
        }
    }

    public void transferFunds(Account holderFrom, Account holderTo, double amount) {
        if (amount > 0) {
            double existingBalance = holderFrom.getBalance();
            if (existingBalance >= amount) {
                holderFrom.setBalance(existingBalance - amount);
                holderTo.setBalance(holderTo.getBalance() + amount);
            }
        }
    }

    //Fetch statement for some configurable number of days can be another method here
}
