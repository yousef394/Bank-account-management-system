package com.bank_account_management_system.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Transaction implements Printable {

    private TransactionType type;
    private double amount;
    private LocalDateTime date;
    private int AccountId;
    private double BalanceBefore;
    private double BalanceAfter;

    public Transaction(int AccountId, TransactionType type, double amount,
                double BalanceBefore,double BalanceAfter) {

        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.AccountId = AccountId;
        this.BalanceBefore = BalanceBefore;
        this.BalanceAfter = BalanceAfter;
        date = LocalDateTime.now();
    }

    public Transaction( int AccountId, TransactionType type, double amount,
                       double BalanceBefore,double BalanceAfter ,LocalDateTime date) {
        this.AccountId = AccountId;
        this.type = type;
        this.amount = amount;
        this.BalanceBefore = BalanceBefore;
        this.BalanceAfter = BalanceAfter;
        this.date = date;
    }


    public int getAccountId() {
        return AccountId;
    }
    public TransactionType getType() {
        return type;
    }
    public double getBalanceBefore() {
        return BalanceBefore;
    }
    public double getBalanceAfter() {
        return BalanceAfter;
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
        return
                " Account Id: "+AccountId+
                " | Type: "+type.toString()+
                " | Amount: "+ amount+
                " | BalanceBefore: "+BalanceBefore+
                " | BalanceAfter: "+BalanceAfter+
                " | Date: "+date.toString();

    }



}

