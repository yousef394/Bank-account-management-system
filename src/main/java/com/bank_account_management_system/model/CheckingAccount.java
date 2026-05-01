package com.bank_account_management_system.model;

import java.time.LocalDateTime;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private double fee = 0;

    public CheckingAccount(int id ,String Password,String holderName,
                           double balance, double overdraft ) {
        super(id,Password,holderName,balance);
        this.overdraftLimit = overdraft;
        this.fee = fee;
    }
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

    @Override
    public boolean withdraw(double amount) {
        if(amount>0) {
            if (getBalance() >= amount) {
                return super.withdraw(amount);
            }
            else if (overdraftLimit + getBalance() >= amount) {

                fee = (amount - getBalance()) * 1.25;
                super.withdraw(amount);
                setAuditlog("there is a fee = " + fee);
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
                  setAuditlog("fee applied :" + fee);
                  fee = 0;
              }

              else
              {
                  setAuditlog("Your Balance is lower than "+fee);
                  fee*=1.5;
                  setAuditlog("the fee increased to "+fee);

              }

          }

          else
              setAuditlog("there is no fee");

    }

    @Override
    public String printDetails() {
        return  super.printDetails("checking Account")+ " OverdraftLimit: "+overdraftLimit
                + " Fee: "+fee;

    }


}