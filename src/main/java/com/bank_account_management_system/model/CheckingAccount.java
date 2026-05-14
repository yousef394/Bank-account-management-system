package com.bank_account_management_system.model;


import java.time.LocalDateTime;

public class CheckingAccount extends BankAccount {

    private double overdraftLimit;
    private double fee = 0;


    //=========constructors=================
    //for new account
    public CheckingAccount(String password,String holderName,double balance, double overdraft ) {
        super(password,holderName,balance);
        this.overdraftLimit = overdraft;

    }

   // for existed account
    public CheckingAccount(int id ,String password, LocalDateTime dateCreated,String holderName,
                           double balance, double overdraft, double fee ) {
        super(id,password,holderName,balance,dateCreated);
        this.overdraftLimit = overdraft;
        this.fee = fee;
    }


    //========getter===========
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    public double getFee() {
        return fee;
    }

    //=======setter==========
    public void setOverdraftLimit(double overdraft) {
        this.overdraftLimit = overdraft;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }



    //========override methods=========
    @Override
    protected void setBalance(double balance) {
        super.setBalance(balance);
    }

    @Override
    public boolean withdraw(double amount)  {

        if (amount <= 0) return false;


         if (getBalance() >= amount)
             return super.withdraw(amount);

         else if (overdraftLimit + getBalance() >= amount) {

             //we make fees and take it later with monthly update
             fee = (amount - getBalance()) * 0.25;
             setBalance(getBalance() - amount);
             return true;
         }

          return false;

        }


    @Override
    public boolean applyMonthlyUpdate() {
          if (fee>0)
          {
              if( getBalance()>=fee ) {
                  setBalance(getBalance() - fee);
                    fee = 0;
                    return true;
              }

              else {
                  fee *= 1.25;
                  return false;
              }

          }

          else {
              fee = 0;
          return false;
          }
    }

    @Override
    public String printDetails() {
        return  super.printDetails()+ " | OverdraftLimit: "+overdraftLimit
                + " | Fee: "+fee;

    }


}