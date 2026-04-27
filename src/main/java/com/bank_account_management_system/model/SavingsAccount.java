package com.bank_account_management_system.model;
public class SavingsAccount extends BankAccount{

    private double interestRate;

    public SavingsAccount(int id , String holderName, double balance, double interestRate) {
        super(id, holderName, balance);
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

    @Override
    public void applyMonthlyUpdate()
    {
        applyInterest();
        setAuditlog("MonthlyUpdate is applied");
    }


}