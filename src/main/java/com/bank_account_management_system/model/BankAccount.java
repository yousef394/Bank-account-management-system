package com.bank_account_management_system.model;

import java.util.ArrayList;
import java.util.List;

abstract public class BankAccount {
        private int accountId;
        private String holderName;
        private double balance;

        public BankAccount(int accountId, String holderName, double balance) {
            this.accountId = accountId;
            this.holderName = holderName;
            this.balance = balance;
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

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public void deposit(double amount) {
            this.balance += amount;

        }

        public void withdraw(double amount) {
            this.balance -= amount;
        }

        public String printDetails() {
            return "Account Id: " + accountId +
                    "\nHolder Name: " + holderName +
                    "\nBalance: " + balance;
        }

        ArrayList< String >  getAuditLog(){

            ArrayList< String > auditLog = new ArrayList<>();
            /*

            auditLog.add("Account Id: " + accountId);
            auditLog.add("Holder Name: " + holderName);
            auditLog.add("Balance: " + balance);
            return auditLog;

             */
            return auditLog;
        }

}


