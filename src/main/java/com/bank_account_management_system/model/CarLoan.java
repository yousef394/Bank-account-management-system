package com.bank_account_management_system.model;


import java.time.LocalDateTime;


public class CarLoan extends LoanAccount {
    private String CarModel;

    //========= Constructors ==========
    //for new loan
    public CarLoan(String password, String holderName,
                   double balance,double loanAmount, double remainingAmount ,String CarModel) {
        super(password, holderName, balance, loanAmount, remainingAmount);
        this.CarModel = CarModel;

    }

    //for load loan
    public CarLoan(int accountId, String password, LocalDateTime dateCreated, String holderName,
                   double balance, double loanAmount, double remainingAmount , String CarModel) {
        super(accountId,password,dateCreated, holderName, balance, loanAmount, remainingAmount);
        this.CarModel = CarModel;

    }

    //=========== getter & setter =======
    public String getCarModel() {
        return CarModel;
    }
    public void setCarModel(String CarModel) {
        this.CarModel = CarModel;
    }

    //======== Override method =======
    @Override
    public String printDetails() {
        return super.printDetails()+" CarModel: "+CarModel;
    }

}
