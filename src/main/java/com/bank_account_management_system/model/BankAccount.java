package com.bank_account_management_system.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

 public abstract class BankAccount implements Auditable , Printable {
        private int accountId;
        private String holderName;
        private double balance;
        private LocalDateTime DateCreated;
        private List< String > auditLog = new ArrayList<>();

        protected void setAuditlog(String Operation)
        {
            auditLog.add(Operation+" | "+"Now Account balance is "+getBalance());
        }

        public BankAccount(int accountId, String holderName, double balance) {
            this.accountId = accountId;
            this.holderName = holderName;

            if(balance>=0){
                this.balance = balance;
            }
            else
                this.balance = 0;

            this.DateCreated = LocalDateTime.now();
            setAuditlog("Account created in "+this.DateCreated);
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

        protected void setBalance(double balance) {
            this.balance = balance;
        }

        public LocalDateTime getDateCreated() {
            return DateCreated;
        }

        public void setDateCreated(LocalDateTime dateCreated) {
            DateCreated = dateCreated;
        }

        public boolean deposit(double amount) {
            if(amount > 0)
            {
                this.balance += amount;
                setAuditlog("Deposit :"+amount);
                return true;
            }
            else
                return false;


        }

        public boolean withdraw(double amount) {
            if (balance >= amount && amount>0) {
                this.balance -= amount;
                setAuditlog("Withdraw :"+amount);
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


