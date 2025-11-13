package multithreading.bankingApplication.entity;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class Account {
    private int accId;
    private AtomicInteger atomicBalance;
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
}
