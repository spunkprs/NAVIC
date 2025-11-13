package multithreading.bankingApplication.operations;

import multithreading.bankingApplication.entity.Account;

public class AccountOperations {

    public double fetchBalance(Account account) {
        return account.getBalance();
    }

    public void deposit(Account accountHolder, double amount) {
        if (amount > 0) {
            double existingBalance = accountHolder.getBalance();
            accountHolder.setBalance(existingBalance + amount);
        }
    }

    public void withdraw(Account accountHolder, double amount) {
        if (amount > 0) {
            double existingBalance = accountHolder.getBalance();
            if (existingBalance >= amount) {
                accountHolder.setBalance(existingBalance - amount);
            }
        }
    }

    public void transferFunds(Account holderFrom, Account holderTo, double amount) {

    }

    //Fetch statement for some configurable number of days can be another method here
}
