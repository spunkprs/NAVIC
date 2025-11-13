package multithreading.bankingApplication.entity;

import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

public class Account {
    private int accId;
    private AtomicReference<Double> atomicBalance;
    private double balance;
    private Date openedDate;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Person accountHolder;

    public double getBalance() {
        return atomicBalance.get();
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AtomicReference<Double> getAtomicBalance() {
        return atomicBalance;
    }
}
