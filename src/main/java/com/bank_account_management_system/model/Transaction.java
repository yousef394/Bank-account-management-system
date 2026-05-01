package com.bank_account_management_system.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction implements Printable {
    static private int transactionId=0;
    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private int AccountId;
    private double BalanceBefore;
    private double BalanceAfter;

    Transaction(int AccountId, TransactionType type, double amount,
                double BalanceBefore,double BalanceAfter, LocalDateTime date) {
        this.transactionId ++;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.AccountId = AccountId;
        this.BalanceBefore = BalanceBefore;
        this.BalanceAfter = BalanceAfter;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public TransactionType getType() {
        return type;
    }
    public double getAmount() {
        return amount;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public void setAccountId(int AccountId) {
        this.AccountId = AccountId;
    }
    public void setBalanceBefore(double BalanceBefore) {
        this.BalanceBefore = BalanceBefore;
    }
    public void setBalanceAfter(double BalanceAfter) {
        this.BalanceAfter = BalanceAfter;
    }



    @Override
    public String printDetails()
    {
        return "Transaction Id: "+transactionId+
                " Account Id: "+AccountId+
                " Type: "+type.toString()+
                " Amount: "+ amount+
                " BalanceBefore: "+BalanceBefore+
                " BalanceAfter: "+BalanceAfter+
                " Date: "+date.toString();

    }



}

