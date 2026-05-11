package com.bank_account_management_system.service;


import com.bank_account_management_system.Repository.*;
import com.bank_account_management_system.model.*;


import java.util.ArrayList;


public class AccountService {

    private static final CheckingAccountRepository checkingRepo = new CheckingAccountRepository();
    private static final SavingsAccountRepository savingsRepo = new SavingsAccountRepository();
    private static final CarLoanRepository carLoanRepo = new CarLoanRepository();
    private static final HomeLoanRepository homeLoanRepo = new HomeLoanRepository();
    private static final TransactionRepository transactionRepo = new TransactionRepository();

    public static boolean createAccount(BankAccount account) {
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

    public static boolean delete(int id, AccountType type) {
        switch (type) {
            case CHECKING:
                return checkingRepo.delete(id);
            case SAVINGS:
                return savingsRepo.delete(id);
            case CARLOAN:
                return carLoanRepo.delete(id);
            case HOMELOAN:
                return homeLoanRepo.delete(id);
        }
        return false;
    }

    public static BankAccount find(int id, AccountType type) {
        switch (type) {
            case CHECKING:
                return checkingRepo.findById(id);
            case SAVINGS:
                return savingsRepo.findById(id);
            case CARLOAN:
                return carLoanRepo.findById(id);
            case HOMELOAN:
                return homeLoanRepo.findById(id);
        }
        return null;
    }

    public static boolean saveAccount(BankAccount account) {
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

    public static ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        accounts.addAll(new CheckingAccountRepository().getAll());

        accounts.addAll(new SavingsAccountRepository().getAll());

        accounts.addAll(new CarLoanRepository().getAll());

        accounts.addAll(new HomeLoanRepository().getAll());

        return accounts;
    }

    public static void applyMonthlyUpdates() {
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
                amount = -1*((LoanAccount) account).getLoanAmount();
                type = TransactionType.LOAN_PAYMENT;
            }

            double balance = account.getBalance();

            account.applyMonthlyUpdate();

           saveAccount(account) ;

            transactionRepo.add(new Transaction(account.getAccountId(),type
                    ,Math.abs(amount), balance, balance + amount));
        }
    }

    static public boolean deposit(int id, Double amount, AccountType type) {
        BankAccount account = find(id, type);

        if (account == null )
            return false;

        double balance = account.getBalance();


        return  account.deposit(amount) && saveAccount(account)
                && transactionRepo.add(new Transaction( account.getAccountId(), TransactionType.DEPOSIT
                ,amount, balance, balance + amount) );

    }

    static public boolean withdrawFromCheckingAccount(int id, Double amount) {
       BankAccount account = find(id, AccountType.CHECKING);

        if(account == null)
            return false;


        double balance = account.getBalance();

        return  account.withdraw(amount) && saveAccount(account)
                && transactionRepo.add(new Transaction( account.getAccountId(),TransactionType.WITHDRAW,
                amount,balance,balance-amount));
    }

    static public boolean transfer(int id1 , int id2, AccountType type, double amount ) {
            BankAccount account1 = find(id1, AccountType.CHECKING);
            BankAccount account2 = find(id2, type);

            if (account1 == null || account2 == null )
                return false;

            double balance1 = account1.getBalance();
            double balance2 = account2.getBalance();




        return account1.transfer(account2, amount) &&  saveAccount(account1) && saveAccount(account2)
                &&
                transactionRepo.add(new Transaction( account1.getAccountId(),TransactionType.TRANSFER,
                amount,balance1,balance1-amount))
                &&
                transactionRepo.add(new Transaction( account2.getAccountId(),TransactionType.TRANSFER,
                amount,balance2,balance2+amount));

    }

}
