package com.bank_account_management_system.model;


import com.bank_account_management_system.Data.BankAccountData;
import com.bank_account_management_system.Data.TransactionData;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


 public abstract class BankAccount implements Auditable , Printable {

        private int accountId;
        private String holderName;
        private String password;
        private double balance;
        private LocalDateTime DateCreated;


        public BankAccount(int accountId,String password, String holderName, double balance) {
            this.accountId = accountId;
            this.holderName = holderName;
            this.password = password;

            if(balance>=0){
                this.balance = balance;
            }
            else
                this.balance = 0;

            this.DateCreated = LocalDateTime.now();


        }

        public BankAccount(int accountId,String password, String holderName,
                           double balance ,LocalDateTime DateCreated) {
         this.accountId = accountId;
         this.holderName = holderName;
         this.password = password;

         if(balance>=0){
             this.balance = balance;
         }
         else
             this.balance = 0;

         this.DateCreated =DateCreated;


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

        public LocalDateTime getDateCreated() {
            return DateCreated;
        }

        public void setDateCreated(LocalDateTime dateCreated) {
            DateCreated = dateCreated;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }



        public boolean deposit(double amount) throws IOException {
            if(amount > 0)
            {
                TransactionData.saveTransaction(new Transaction (this.accountId,TransactionType.DEPOSIT
                    ,amount,this.balance,
                    this.balance+amount));

                this.balance += amount;

                return true;
            }
            else
                return false;


        }

        public boolean withdraw(double amount) throws IOException {
            if (balance >= amount && amount>0) {
                TransactionData.saveTransaction(new Transaction (this.accountId,TransactionType.WITHDRAW
                        ,amount,this.balance,
                        this.balance+amount));
                this.balance -= amount;
                return true;
            }
            else
                return false;

        }

        public boolean transfer(double amount) throws IOException {

            return withdraw(amount);

        }

        public String printDetails(String Type) {
            return Type+" "+"Account Id: " + accountId +
                    " Holder Name: " + holderName +
                    " Balance: " + balance+
                    " DateCreated: " + DateCreated;
        }

         public List< String >  getAuditLog() throws IOException {

            ArrayList< String > log = new ArrayList<>();
            ArrayList<Transaction> transactions = TransactionData.findAccountTransaction(accountId);

            for (Transaction transaction : transactions) {
                log.add(transaction.printDetails());
            }

                 return new ArrayList<>();
         }

         public abstract boolean Save() throws IOException;


         public abstract void applyMonthlyUpdate();

        public abstract String printDetails();
}


