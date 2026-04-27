package com.bank_account_management_system.model;

public class CheckingAccount extends BankAccount {
    private double overdraft;

    public CheckingAccount(int id ,String holderName,double balance, double overdraft) {
        super(id,holderName,balance);
        this.overdraft = overdraft;
    }
    public double getOverdraft() {
        return overdraft;
    }
    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }
    
}