package com.bank_account_management_system.model;

import java.util.ArrayList;
import java.util.List;

 public abstract class BankAccount {
        private int accountId;
        private String holderName;
        private double balance;
        private List< String > auditLog = new ArrayList<>();

        public BankAccount(int accountId, String holderName, double balance) {
            this.accountId = accountId;
            this.holderName = holderName;
            this.balance = balance;
            auditLog.add("Account created with balance: " + this.balance);
        }

        public int getAccountId() {
            return accountId;
        }

        public String getHolderName() {
            return holderName;
        }

        public void setHolderName(String holderName) {
            this.holderName = holderName;
        }

        public double getBalance() {
            return balance;
        }

        public boolean deposit(double amount) {
            if(amount > 0)
            {
                this.balance += amount;
                auditLog.add("deposited :"+amount +" | Balance now is :"+balance);
                return true;
            }
            else
                return false;


        }

        public boolean withdraw(double amount) {
            if (balance >= amount && amount>0) {
                this.balance -= amount;
                auditLog.add("Withdraw :"+amount +" | Balance now is :"+balance);
                return true;
            }
            else
                return false;

        }

        public String printDetails() {
            return "Account Id: " + accountId +
                    "\nHolder Name: " + holderName +
                    "\nBalance: " + balance;
        }

         public List< String >  getAuditLog(){
                 return new ArrayList<>(auditLog);
         }

         public abstract void applyMonthlyUpdate();

}


