package com.bank_account_management_system.model;

import com.bank_account_management_system.Repository.HomeLoanRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
    public String printDetails() {
        return super.printDetails()+" propertyAddress: " + propertyAddress;

    }


}
