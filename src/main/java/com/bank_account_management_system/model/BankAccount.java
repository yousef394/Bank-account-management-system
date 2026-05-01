package com.bank_account_management_system.model;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


 public abstract class BankAccount implements Auditable , Printable {

        private int accountId;
        private String holderName;
        private String password;
        private double balance;
        private LocalDateTime DateCreated;
        private List< String > auditLog = new ArrayList<>();

        protected void setAuditlog(String Operation)
        {
            auditLog.add(Operation);
        }

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

        public BankAccount(int accountId,String password, String holderName, double balance ,LocalDateTime DateCreated) {
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

        protected void setBalance(double balance) {
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



        public boolean deposit(double amount) {
            if(amount > 0)
            {
                setAuditlog(new Transaction (this.accountId,TransactionType.DEPOSIT
                    ,amount,this.balance,
                    this.balance+amount,LocalDateTime.now()).printDetails());

                this.balance += amount;

                return true;
            }
            else
                return false;


        }

        public boolean withdraw(double amount) {
            if (balance >= amount && amount>0) {
                setAuditlog(new Transaction (this.accountId,TransactionType.WITHDRAW
                        ,amount,this.balance,
                        this.balance+amount,LocalDateTime.now()).printDetails());
                this.balance -= amount;
                return true;
            }
            else
                return false;

        }

        protected String printDetails(String Type) {
            return Type+"\n"+"Account Id: " + accountId +
                    "\nHolder Name: " + holderName +
                    "\nBalance: " + balance;
        }

         public List< String >  getAuditLog(){
                 return new ArrayList<>(auditLog);
         }

         public abstract void applyMonthlyUpdate();

        public abstract String printDetails();
}


