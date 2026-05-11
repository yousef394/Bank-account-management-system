package com.bank_account_management_system.model;


import java.time.LocalDateTime;


public class HomeLoan extends LoanAccount {

    private String propertyAddress;

    //========= Constructors ==========

    //for new loan
    public HomeLoan(String password, String holderName,
                   double balance,double loanAmount, double remainingAmount ,String propertyAddress) {
        super(password, holderName, balance, loanAmount, remainingAmount);
        this.propertyAddress = propertyAddress;

    }

    //for load loan
    public HomeLoan(int accountId, String password, LocalDateTime dateCreated, String holderName,
                    double balance, double loanAmount, double remainingAmount , String propertyAddress) {
        super(accountId,password,dateCreated, holderName, balance, loanAmount, remainingAmount);
        this.propertyAddress = propertyAddress;

    }


    //=========== getter & setter =======
    public String getPropertyAddress() {
        return propertyAddress;
    }
    public void setPropertyAddress(String PropertyAddress) {
        this.propertyAddress = PropertyAddress;
    }


    //======== Override method =======
    @Override
    public String printDetails() {
        return super.printDetails()+" propertyAddress: " + propertyAddress;

    }


}
