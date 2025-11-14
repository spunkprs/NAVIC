package multithreading.bankingApplication.operations;

import multithreading.bankingApplication.entity.Account;
import multithreading.bankingApplication.entity.ExceptionMessages;
import multithreading.bankingApplication.exception.DepositException;
import multithreading.bankingApplication.exception.WithdrawException;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public enum AccountOperationsUsingPessimisticLocking {

    ACCOUNT_OPERATIONS;

    public double fetchBalance(Account account) {
        ReentrantReadWriteLock reentrantReadWriteLock = account.getReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = null;
        try {
           readLock = reentrantReadWriteLock.readLock();
           return account.getAccountBalance();
        } catch (Exception e) {
            System.out.print("Exception arised while fetching balance for accountId " + account.getAccId());
            throw e;
        } finally {
            readLock.unlock();
        }
    }

    public void deposit(Account accountHolder, double amount) {
        if (amount > 0) {
            ReentrantReadWriteLock.WriteLock writeLock = accountHolder.getReentrantReadWriteLock().writeLock();
            try {
                writeLock.tryLock(2000, TimeUnit.MILLISECONDS);
                accountHolder.setAccountBalance(accountHolder.getAccountBalance() + amount);
            } catch (Exception e) {
                System.out.print("Exception arised while depositing amount" + " " + amount + " into accountId " + accountHolder.getAccId());
                throw new DepositException(ExceptionMessages.DEPOSIT_EXCEPTION.getExceptionMessage());
            } finally {
                writeLock.unlock();
            }
        }
    }

    public void withdraw(Account accountHolder, double amount) {
        if (amount > 0) {
            ReentrantReadWriteLock.WriteLock writeLock = accountHolder.getReentrantReadWriteLock().writeLock();
            try {
                writeLock.tryLock(2000, TimeUnit.MILLISECONDS);
                if (accountHolder.getAccountBalance() >= amount) {
                    accountHolder.setAccountBalance(accountHolder.getAccountBalance() - amount);
                } else {
                    System.out.print("Insufficient funds to make withdraw for amount" + " " + amount + " from accountId " + accountHolder.getAccId());
                }
            } catch (Exception e) {
                System.out.print("Exception arised while withdrawing amount" + " " + amount + " from accountId " + accountHolder.getAccId());
                throw new WithdrawException(ExceptionMessages.WITHDRAW_EXCEPTION.getExceptionMessage());
            } finally {
                writeLock.unlock();
            }
        }
    }

    public void transferFunds(Account holderFrom, Account holderTo, int amount) {
            if (amount > 0) {
                ReentrantReadWriteLock.WriteLock writeLockFrom = holderFrom.getReentrantReadWriteLock().writeLock();
                ReentrantReadWriteLock.WriteLock writeLockTo = holderTo.getReentrantReadWriteLock().writeLock();
                boolean revertDeduction = false;
                try {
                    writeLockFrom.tryLock(2000, TimeUnit.MILLISECONDS);
                    if (holderFrom.getAccountBalance() >= amount) {
                        holderFrom.setAccountBalance(holderFrom.getAccountBalance() - amount);
                        try {
                            writeLockTo.tryLock(2000, TimeUnit.MILLISECONDS);
                            holderTo.setAccountBalance(holderTo.getAccountBalance() + amount);
                        } catch (Exception e) {
                            revertDeduction = true;
                            System.out.print("Exception arised while depositing amount" + " " + amount + " into accountId " + holderTo.getAccId());
                            throw new DepositException(ExceptionMessages.DEPOSIT_EXCEPTION.getExceptionMessage());
                        } finally {
                            writeLockTo.unlock();
                        }
                    } else {
                        System.out.print("Insufficient funds to make transfer for amount" + " " + amount + " from accountId " + holderFrom.getAccId());
                    }
                } catch (Exception e) {
                    if (revertDeduction) {
                        holderFrom.setAccountBalance(holderFrom.getAccountBalance() + amount);
                        throw new DepositException(ExceptionMessages.DEPOSIT_EXCEPTION.getExceptionMessage());
                    } else {
                        System.out.print("Exception arised while withdrawing amount" + " " + amount + " from accountId " + holderFrom.getAccId());
                        throw new WithdrawException(ExceptionMessages.WITHDRAW_EXCEPTION.getExceptionMessage());
                    }
                } finally {
                    writeLockFrom.unlock();
                }
            }
    }

    //Fetch statement for some configurable number of days can be another method here
}
