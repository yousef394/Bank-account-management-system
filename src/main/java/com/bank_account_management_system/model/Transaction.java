package com.bank_account_management_system.model;

import java.time.LocalDate;

public class Transaction implements Printable {
    private int transactionId;
    private TransactionType type;
    private double amount;
    private LocalDate date;

    Transaction(int transactionId, TransactionType type, double amount, LocalDate date) {
        this.transactionId = transactionId;
        this.type = type;
        this.amount = amount;
        this.date = date;
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
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setType(TransactionType type) {
        this.type = type;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String printDetails()
    {
        return "Transaction Id: "+transactionId+
                " Type: "+type+
                " Amount: "+ amount+
                " Date: "+date.toString();

    }



}

