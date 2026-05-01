package com.bank_account_management_system.model;

import java.time.LocalDateTime;

public class HomeLoan extends LoanAccount {
    private String propertyAddress;

    public HomeLoan(int accountId,String password, String holderName,
                   double balance,double loanAmount, double remainingAmount ,String propertyAddress) {
        super(accountId,password, holderName, balance, loanAmount, remainingAmount);
        this.propertyAddress = propertyAddress;

    }

    public HomeLoan(int accountId, String password, LocalDateTime dateCreated, String holderName,
                    double balance, double loanAmount, double remainingAmount , String propertyAddress) {
        super(accountId,password,dateCreated, holderName, balance, loanAmount, remainingAmount);
        this.propertyAddress = propertyAddress;

    }


    public String getPropertyAddress() {
        return propertyAddress;
    }
    public void setPropertyAddress(String PropertyAddress) {
        this.propertyAddress = PropertyAddress;
    }


    @Override
    public void payInstallment(double amount)
    {

    }

    @Override
    public void applyMonthlyUpdate()
    {

    }

    @Override
    public String printDetails() {
        return super.printDetails("HomeLoan");
    }
}
