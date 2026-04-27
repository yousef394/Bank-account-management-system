package com.bank_account_management_system.model;

public class CarLoan extends LoanAccount {
    private String CarModel;

    public CarLoan(int accountId, String holderName,
                   double balance,double loanAmount, double remainingAmount ,String CarModel) {
        super(accountId, holderName, balance, loanAmount, remainingAmount);
        this.CarModel = CarModel;

    }

    String getCarModel() {
        return CarModel;
    }
    public void setCarModel(String CarModel) {
        this.CarModel = CarModel;
    }


    @Override
    public void payInstallment(double amount)
    {

    }

    @Override
    public void applyMonthlyUpdate()
    {

    }

}
