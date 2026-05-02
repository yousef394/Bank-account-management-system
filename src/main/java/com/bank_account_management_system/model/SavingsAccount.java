package com.bank_account_management_system.model;

import com.bank_account_management_system.Data.BankAccountData;

import java.io.IOException;
import java.time.LocalDateTime;

public class SavingsAccount extends BankAccount{

    private double interestRate;

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

    public void applyInterest()
    {
        setBalance((getBalance()*interestRate)+getBalance());
        setAuditlog("Interest is applied "+getInterestRate());
    }

    //because it is saving account -->clint can't make withdraw
    @Override
    public boolean withdraw(double amount) {
       return false;
    }

    @Override
    public boolean Save() throws IOException {
        return BankAccountData.SavingAccountData.Update(getAccountId(),getPassword(),
                getHolderName(),getBalance(),getInterestRate());
    }

    @Override
    public void applyMonthlyUpdate()
    {
        applyInterest();
        setAuditlog("MonthlyUpdate is applied");
    }

    @Override
    public String printDetails() {
        return "Saving Account: "+ super.printDetails("Saving Account")+
                " interestRate = "+interestRate;
    }


}