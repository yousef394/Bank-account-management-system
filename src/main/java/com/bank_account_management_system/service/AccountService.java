package com.bank_account_management_system.service;


import com.bank_account_management_system.Repository.*;
import com.bank_account_management_system.model.*;


import java.util.ArrayList;


public class AccountService {

    private  final CheckingAccountRepository checkingRepo = new CheckingAccountRepository();
    private  final SavingsAccountRepository savingsRepo = new SavingsAccountRepository();
    private  final CarLoanRepository carLoanRepo = new CarLoanRepository();
    private  final HomeLoanRepository homeLoanRepo = new HomeLoanRepository();
    private  final TransactionRepository transactionRepo = new TransactionRepository();
    private  final User currentUser;

    public AccountService(User currentUser) {
       if(currentUser == null
               || UserService.findByUserNameAndPassword(currentUser.getUsername()
               , currentUser.getPassword()) == null){
           throw  new NullPointerException("Current user is null");
       }
        else
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public  boolean createAccount(BankAccount account) {
        if (account == null) { return false; }

        if (account instanceof CheckingAccount)
         return  checkingRepo.add((CheckingAccount) account);

        else if(account instanceof SavingsAccount)
         return  savingsRepo.add((SavingsAccount) account);

        else if (account instanceof CarLoan)
         return carLoanRepo.add((CarLoan) account);

        else if (account instanceof HomeLoan)
         return  homeLoanRepo.add((HomeLoan) account);

    return false;

    }

    public  boolean delete(int id) {

        return checkingRepo.delete(id) || savingsRepo.delete(id) || carLoanRepo.delete(id) || homeLoanRepo.delete(id);
    }

    public  BankAccount findById(int id) {

       BankAccount account;

       if ((account = checkingRepo.find(id) ) != null)
          return account;

       else if ((account = savingsRepo.find(id) ) != null)
           return account;

       else if ((account = carLoanRepo.find(id) ) != null)
           return account;

       else if ((account = homeLoanRepo.find(id) ) != null)
           return account;

       else return null;

    }

    public BankAccount findByIdAndPassword(int id , String password) {
        BankAccount account =  findById(id);

        if (account != null) {
            if (password.equals(account.getPassword())) {
                return account;
            }
        }
        return null;
    }

    public  boolean saveAccount(BankAccount account) {
        if  (account == null) { return false; }

        if (account instanceof CheckingAccount)
          return  checkingRepo.update((CheckingAccount) account);

        else if(account instanceof SavingsAccount)
          return savingsRepo.update((SavingsAccount) account);

        else if (account instanceof CarLoan)
          return carLoanRepo.update((CarLoan) account);

        else if (account instanceof HomeLoan)
          return homeLoanRepo.update((HomeLoan) account);

        return false;
    }

    public  ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        accounts.addAll(checkingRepo.getAll());

        accounts.addAll(savingsRepo.getAll());

        accounts.addAll(carLoanRepo.getAll());

        accounts.addAll(homeLoanRepo.getAll());

        return accounts;
    }

    public  void applyMonthlyUpdates() {
        ArrayList<BankAccount> accounts = loadAccounts();
        double amount = 0;
        TransactionType type ;

        for (BankAccount account : accounts) {
            if (account instanceof CheckingAccount) {
                amount = -1*((CheckingAccount) account).getFee();
                type =TransactionType.FEES;
            }

            else if (account instanceof SavingsAccount) {
                amount = ((SavingsAccount) account).getInterestRate() * account.getBalance() ;
                type =TransactionType.INTEREST;
            }

            // will be loanAccount
            else {
                amount = -1*((LoanAccount) account).getLoanAmount()/12;
                type = TransactionType.LOAN_PAYMENT;
            }

            double balance = account.getBalance();

           if(account.applyMonthlyUpdate()) {

               if (saveAccount(account)) {
                   transactionRepo.add(new Transaction(account.getAccountId(), type
                           , Math.abs(amount), balance, balance + amount
                           ,currentUser.getUsername()));
               }
           }
        }


    }

    public boolean deposit(int id,String password, Double amount) {
        BankAccount account = findByIdAndPassword(id,password);

        if (account == null || amount == null || amount <= 0)
            return false;

        double balance = account.getBalance();


        return  account.deposit(amount) && saveAccount(account)
                && transactionRepo.add(new Transaction( account.getAccountId(), TransactionType.DEPOSIT
                ,amount, balance, balance + amount  ,currentUser.getUsername()) );

    }

    public boolean withdraw(int id,String password, Double amount) {
       BankAccount account = findByIdAndPassword(id,password);

        if (account == null || amount == null || amount <= 0 )
            return false;


        double balance = account.getBalance();

        return  account.withdraw(amount) && saveAccount(account)
                && transactionRepo.add(new Transaction( account.getAccountId(),TransactionType.WITHDRAW,
                amount,balance,balance-amount ,currentUser.getUsername()));
    }

    public boolean transfer(int idFrom,String password , int idTo, double amount ) {
            BankAccount account1 = findByIdAndPassword(idFrom,password);
            BankAccount account2 = findById(idTo);

            if (account1 == null || account2 == null || amount <= 0 )
                return false;

            double balance1 = account1.getBalance();
            double balance2 = account2.getBalance();




        return account1.transfer(account2, amount) &&  saveAccount(account1) && saveAccount(account2)
                &&
                transactionRepo.add(new Transaction( account1.getAccountId(),TransactionType.TRANSFER,
                amount,balance1,balance1-amount ,currentUser.getUsername()))
                &&
                transactionRepo.add(new Transaction( account2.getAccountId(),TransactionType.TRANSFER,
                amount,balance2,balance2+amount ,currentUser.getUsername()));

    }

}
