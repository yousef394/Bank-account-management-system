package com.bank_account_management_system.model;

import java.time.LocalDateTime;

public abstract class BankAccount implements Printable, Auditable {

    protected int accountId;
    protected String holderName;
    protected String password;
    protected double balance;
    protected LocalDateTime dateCreated;

    // Constructor for new accounts
    public BankAccount(int accountId, String password, String holderName, double balance) {
        this.accountId = accountId;
        this.password = password;
        this.holderName = holderName;
        this.balance = Math.max(balance, 0);
        this.dateCreated = LocalDateTime.now();
    }

    // Constructor for loading from file
    public BankAccount(int accountId, String password, String holderName,
                       double balance, LocalDateTime dateCreated) {
        this.accountId = accountId;
        this.password = password;
        this.holderName = holderName;
        this.balance = Math.max(balance, 0);
        this.dateCreated = dateCreated;
    }

    // ================= Getters =================

    public int getAccountId() {
        return accountId;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getPassword() {
        return password;
    }

    // ================= Setters =================

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    protected void setBalance(double balance) {
        this.balance = Math.max(balance, 0);
    }

    // ================= Core Logic =================

    public boolean deposit(double amount) {
        if (amount <= 0) return false;

        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) return false;

        balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount target, double amount) {
        if (target == null) return false;

        if (this.withdraw(amount)) {
            target.deposit(amount);
            return true;
        }
        return false;
    }

    // ================= Interfaces =================

    @Override
    public String printDetails() {
        return "Account ID: " + accountId +
                " | Name: " + holderName +
                " | Balance: " + balance +
                " | Created: " + dateCreated;
    }

    // ================= Abstract =================

    public abstract void applyMonthlyUpdate();

}
