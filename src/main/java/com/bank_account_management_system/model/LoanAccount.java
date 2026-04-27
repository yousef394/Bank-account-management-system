package com.bank_account_management_system.model;

public abstract class LoanAccount extends BankAccount {

    private double loanAmount;
    private double remainingAmount;

    public LoanAccount(int accountId, String holderName,
                       double balance,double loanAmount, double remainingAmount) {
        super(accountId, holderName, balance);
    }


    abstract public void payInstallment(double amount);


    //because it is a LoanAccount -> clint can't make withdraw
    @Override
    public boolean withdraw(double amount) {
            return false;
    }



}

