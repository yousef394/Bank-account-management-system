package com.bank_account_management_system.model;
public class SavingAccount extends BankAccount{

    private double interestRate;

    public SavingAccount(int id , String holderName, double balance, double interestRate) {
        super(id, holderName, balance);
        this.interestRate = interestRate;

    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void applyInterest()
    {
        setBalance(getBalance()*interestRate);
    }

    @Override
    public void applyMonthlyUpdate()
    {

    }


}