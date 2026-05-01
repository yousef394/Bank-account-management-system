package com.bank_account_management_system.model;

public class HomeLoan extends LoanAccount {
    private String propertyAddress;

    public HomeLoan(int accountId,String password, String holderName,
                   double balance,double loanAmount, double remainingAmount ,String CarModel) {
        super(accountId,password, holderName, balance, loanAmount, remainingAmount);
        this.propertyAddress = CarModel;

    }


    String getPropertyAddress() {
        return propertyAddress;
    }
    public void setPropertyAddress(String CarModel) {
        this.propertyAddress = CarModel;
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
