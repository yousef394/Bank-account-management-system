package com.bank_account_management_system.model;

import java.time.LocalDateTime;

public abstract class LoanAccount extends BankAccount {

    private final double loanAmount;
    private double remainingAmount;

    //=========constructors==============

    //for new loan
    public LoanAccount(String password, String holderName,
                       double balance,double loanAmount, double remainingAmount) {
        super(password, holderName, balance);
        this.loanAmount = loanAmount;
        this.remainingAmount = remainingAmount;
    }

    //for existed loan
    public LoanAccount(int accountId, String password, LocalDateTime dateCreated, String holderName,
                       double balance, double loanAmount, double remainingAmount) {
        super(accountId,password, holderName, balance,dateCreated);
        this.loanAmount = loanAmount;
        this.remainingAmount = remainingAmount;
    }


    //========= Getters =========
    public double getLoanAmount() {
        return loanAmount;
    }
    public double getRemainingAmount() {
        return remainingAmount;
    }

    //========= Setters =========
    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }


    //=======Core Logic ========

     public boolean payInstallment(double amount) {

        if(amount <= 0) { return false; }

        if (remainingAmount < amount) { return false; }

         if(amount > getBalance()) {

             return false; }

         setBalance(getBalance() - amount);
         remainingAmount -= amount;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    //=========Override methods========
    @Override
    public boolean  applyMonthlyUpdate(){

        // we assume that loan payment in year
       return payInstallment(loanAmount/12);
    }

    @Override
    public String printDetails() {

       return super.printDetails()
               +" | Loan Amount: "+loanAmount+" | Remaining Amount: "+remainingAmount;
    }

    // Loan accounts do not support withdrawals


}

