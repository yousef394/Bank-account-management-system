package com.bank_account_management_system.model;


import java.time.LocalDateTime;


public class CarLoan extends LoanAccount {
    private String CarModel;

    public CarLoan(int accountId,String password, String holderName,
                   double balance,double loanAmount, double remainingAmount ,String CarModel) {
        super(accountId,password, holderName, balance, loanAmount, remainingAmount);
        setCarModel(CarModel);

    }

    public CarLoan(int accountId, String password, LocalDateTime dateCreated, String holderName,
                   double balance, double loanAmount, double remainingAmount , String CarModel) {
        super(accountId,password,dateCreated, holderName, balance, loanAmount, remainingAmount);
        setCarModel(CarModel);

    }

    public String getCarModel() {
        return CarModel;
    }
    public void setCarModel(String CarModel) {
        this.CarModel = CarModel;
    }


    @Override
    public String printDetails() {
        return super.printDetails()+" CarModel: "+CarModel;
    }

}
