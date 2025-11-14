package multithreading.bankingApplication.entity;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Account {
    private int accId;
    private AtomicInteger atomicBalance; //used for optimistic locking, there is no AtomicDouble and AtomicReference<Double> is not working as expected
    // hence had to resort to AtomicInteger,  AtomicDouble is present in guava libraries developed by Google
    private Date openedDate;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Person accountHolder;
    private ReentrantReadWriteLock reentrantReadWriteLock; //used for optimistic locking
    private double accountBalance;

    public double getBalance() {
        return atomicBalance.get();
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public AtomicInteger getAtomicBalance() {
        return atomicBalance;
    }

    public void setAtomicBalance(AtomicInteger atomicBalance) {
        this.atomicBalance = atomicBalance;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public int getAccId() {
        return accId;
    }

    public ReentrantReadWriteLock getReentrantReadWriteLock() {
        return reentrantReadWriteLock;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
}
