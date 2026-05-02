package com.bank_account_management_system.model;

import java.time.LocalDateTime;

public abstract class LoanAccount extends BankAccount {

    private double loanAmount;
    private double remainingAmount;

    public LoanAccount(int accountId,String password, String holderName,
                       double balance,double loanAmount, double remainingAmount) {
        super(accountId,password, holderName, balance);
    }

    public LoanAccount(int accountId, String password, LocalDateTime dateCreated, String holderName,
                       double balance, double loanAmount, double remainingAmount) {
        super(accountId,password, holderName, balance,dateCreated);
        this.loanAmount = loanAmount;
        this.remainingAmount = remainingAmount;
    }

    public double getLoanAmount() {
        return loanAmount;
    }
    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
    public double getRemainingAmount() {
        return remainingAmount;
    }
    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }



     public void payInstallment(double amount)
    {


    }

    @Override
    public void applyMonthlyUpdate()
    {
        payInstallment(0);
    }

    public String printDetails()
    {
       return super.printDetails("Loan Account: ")
               +" Loan Amount: "+loanAmount+" Remaining Amount: "+remainingAmount;
    }


    //because it is a LoanAccount -> clint can't make withdraw
    @Override
    public boolean withdraw(double amount) {
            return false;
    }


}

