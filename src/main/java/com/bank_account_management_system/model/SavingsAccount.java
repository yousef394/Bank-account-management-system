package com.bank_account_management_system.model;
public class SavingAccount extends BankAccount{

    private double interestRate;

    public SavingAccount(int id , String holderName, double balance, double interestRate) {
        super(id, holderName, balance);
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

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
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