package multithreading.bankingApplication.operations;

import multithreading.bankingApplication.entity.Account;
import multithreading.bankingApplication.entity.ExceptionMessages;
import multithreading.bankingApplication.exception.DepositException;
import multithreading.bankingApplication.exception.WithdrawException;

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
            } while (attemptCount <= 5 && !accountHolder.getAtomicBalance().compareAndSet(existingBalance, existingBalance + amount));
            //Raise exception in case attemptCount >= 6
            if (attemptCount >= 6) {
                throw new DepositException(ExceptionMessages.DEPOSIT_EXCEPTION.getExceptionMessage());
            }
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
                } while (existingBalance >= amount
                        && attemptCount <= 5
                        && !accountHolder.getAtomicBalance().compareAndSet(existingBalance, existingBalance - amount));
                //Raise exception in case attemptCount >= 6
                if (attemptCount >= 6) {
                    throw new WithdrawException(ExceptionMessages.WITHDRAW_EXCEPTION.getExceptionMessage());
                }
            }
        }
    }

    public void transferFunds(Account holderFrom, Account holderTo, double amount) {
        try {
            withdraw(holderFrom, amount);
            deposit(holderTo, amount);
        } catch (WithdrawException e) {
            System.out.print("Transfer failed and reverse computation is not needed to maintain data atomicity && consistency !!");
        } catch (DepositException e) {
            System.out.print("Transfer failed and reverse computation is needed to maintain data atomicity && consistency !!");
            depositMoneyBack(holderFrom, amount);
        }
    }

    private void depositMoneyBack(Account accountHolder, double amount) {
        if (amount > 0) {
            double existingBalance = accountHolder.getAtomicBalance().get();
            accountHolder.setBalance(existingBalance + amount);
            do {
                existingBalance = accountHolder.getAtomicBalance().get();
            } while (!accountHolder.getAtomicBalance().compareAndSet(existingBalance, existingBalance + amount));
        }
    }

    //Fetch statement for some configurable number of days can be another method here
}
