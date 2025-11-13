package multithreading.bankingApplication.entity;

import java.util.Date;

public class Account {
    private int accId;
    private double balance;
    private Date openedDate;
    private AccountType accountType;
    private AccountStatus accountStatus;
    private Person accountHolder;

    public double getBalance() {
        return balance;
    }

    public Date getOpenedDate() {
        return openedDate;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
