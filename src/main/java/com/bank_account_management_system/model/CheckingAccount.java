package com.bank_account_management_system.model;

import com.bank_account_management_system.Repository.CheckingAccountRepository;
import com.bank_account_management_system.Repository.TransactionRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private double fee = 0;

    // constructor for new account
    public CheckingAccount(int id ,String Password,String holderName,
                           double balance, double overdraft ) {
        super(id,Password,holderName,balance);
        this.overdraftLimit = overdraft;
        this.fee = fee;
    }
   // constructor for existed account
    public CheckingAccount(int id , String Password, LocalDateTime dateCreated, String holderName,
                           double balance, double overdraft ) {
        super(id,Password,holderName,balance,dateCreated);
        this.overdraftLimit = overdraft;
        this.fee = fee;

    }

    public CheckingAccount(int id ,String Password, LocalDateTime dateCreated,String holderName,
                           double balance, double overdraft, double fee ) {
        super(id,Password,holderName,balance);
        this.overdraftLimit = overdraft;
        this.fee = fee;
    }



    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraft) {
        this.overdraftLimit = overdraft;
    }

    public double getFee() {
        return fee;
    }
    public void setFee(double fee) {
        this.fee = fee;
    }


    @Override
    public boolean withdraw(double amount)  {
        if(amount>0) {
            if (getBalance() >= amount) {
                return super.withdraw(amount);
            }
            else if (overdraftLimit + getBalance() >= amount) {

                fee = (amount - getBalance()) * 0.25;
                super.withdraw(amount);

                return true;
            } else
                return false;

        }
        else
            return false;
        }

    @Override
    public void applyMonthlyUpdate() {
          if (fee>0)

          {
              if( getBalance()>=fee ) {
                  setBalance(getBalance() - fee);
                    fee = 0;
              }

              else
                  fee*=1.5;


          }

          else
              fee=0;
    }

    @Override
    public String printDetails() {
        return  super.printDetails()+ " | OverdraftLimit: "+overdraftLimit
                + " Fee: "+fee;

    }


}