package com.bank_account_management_system.model;


import java.time.LocalDateTime;


public class SavingsAccount extends BankAccount{

    private double interestRate;

    //=========== Constructors ===============
    //for new account
    public SavingsAccount(int id ,String password, String holderName, double balance, double interestRate)
    {
        super(id,password, holderName, balance);
        this.interestRate = interestRate;
    }
    //for load
    public SavingsAccount(int id , String password, LocalDateTime dateCreated ,String holderName,
                          double balance, double interestRate)
    {
        super(id,password, holderName, balance,dateCreated);
        this.interestRate = interestRate;
    }


    // ====== setters and getters =========
    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        if(interestRate > 0 ) {
            if(interestRate <1) {
                this.interestRate = interestRate;
            }
            else {
                this.interestRate = interestRate/100;
            }

        }
        else {
            this.interestRate = 0;
        }

    }


    //=========== Core Logic ==========
    public void applyInterest() {
        double amount = getBalance()*interestRate;
        setBalance((getBalance()*interestRate)+getBalance());

    }

    //Saving Account does not support withdraw
    @Override
    public boolean withdraw(double amount) {
       return false;
    }


    //=========== Override methods ============
    @Override
    public void applyMonthlyUpdate()
    {
        applyInterest();

    }

    @Override
    public String printDetails() {
        return  super.printDetails()+
                " | interestRate = "+interestRate;
    }


}