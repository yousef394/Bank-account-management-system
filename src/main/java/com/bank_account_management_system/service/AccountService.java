package com.bank_account_management_system.service;


import com.bank_account_management_system.Repository.*;
import com.bank_account_management_system.model.*;


import java.util.ArrayList;

public class AccountService {

    public static enum accountType {
        CHECKING,
        SAVINGS,
        CARLOAN,
        HOMELOAN
    }

    public static boolean createAccount(BankAccount account) {
        if (account instanceof CheckingAccount)
         return  CheckingAccountRepository.add((CheckingAccount) account);

        else if(account instanceof SavingsAccount)
         return  SavingsAccountRepository.add((SavingsAccount) account);

        else if (account instanceof CarLoan)
         return CarLoanRepository.add((CarLoan) account);

        else if (account instanceof HomeLoan)
         return  HomeLoanRepository.add((HomeLoan) account);

    return false;

    }

    public static boolean delete(int id, accountType type) {
        switch (type) {
            case CHECKING:
                return CheckingAccountRepository.delete(id);
            case SAVINGS:
                return SavingsAccountRepository.delete(id);
            case CARLOAN:
                return CarLoanRepository.delete(id);
            case HOMELOAN:
                return HomeLoanRepository.delete(id);
        }
        return false;
    }

    public static BankAccount find(int id, accountType type) {
        switch (type) {
            case CHECKING:
                return CheckingAccountRepository.findById(id);
            case SAVINGS:
                return SavingsAccountRepository.findById(id);
            case CARLOAN:
                return CarLoanRepository.findById(id);
            case HOMELOAN:
                return HomeLoanRepository.findById(id);
        }
        return null;
    }

    public static boolean save(BankAccount account) {
        if (account instanceof CheckingAccount)
          return   CheckingAccountRepository.update((CheckingAccount) account);

        else if(account instanceof SavingsAccount)
          return SavingsAccountRepository.update((SavingsAccount) account);

        else if (account instanceof CarLoan)
          return  CarLoanRepository.update((CarLoan) account);

        else if (account instanceof HomeLoan)
          return  HomeLoanRepository.update((HomeLoan) account);

        return false;
    }

    public static ArrayList<BankAccount> loadAccounts() {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        for (CheckingAccount CA : CheckingAccountRepository.getAllAccounts())
            accounts.add(CA);

        for (SavingsAccount SA : SavingsAccountRepository.getAllAccounts())
            accounts.add(SA);

        for (CarLoan CL : CarLoanRepository.getAllAccounts())
            accounts.add(CL);

        for (HomeLoan HL : HomeLoanRepository.getAllAccounts())
            accounts.add(HL);

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
                amount = ((SavingsAccount) account).getInterestRate() * amount;
                type =TransactionType.INTEREST;
            }

            // will be loanAccount
            else {
                amount = -1*((LoanAccount) account).getLoanAmount();
                type = TransactionType.LOAN_PAYMENT;
            }

            TransactionRepository.saveTransaction(new Transaction(account.getAccountId(), TransactionType.LOAN_PAYMENT
                    ,Math.abs(amount), account.getBalance(), account.getBalance() + amount));
            account.applyMonthlyUpdate();

        }
    }

    static public boolean deposit(int id, Double amount, accountType type) {
        BankAccount account = find(id, type);

        if (account == null )
            return false;

        TransactionRepository.saveTransaction(new Transaction( account.getAccountId(), TransactionType.DEPOSIT
                ,amount, account.getBalance(), account.getBalance() + amount) );

        return  account.deposit(amount) && save(account);

    }

    static public boolean withdrawFromCheckingAccount(int id, Double amount) {
       BankAccount account = find(id, accountType.CHECKING);

        if(account == null)
            return false;

        TransactionRepository.saveTransaction(new Transaction( account.getAccountId(),TransactionType.WITHDRAW,
                amount,account.getBalance(),account.getBalance()-amount));


        return  account.withdraw(amount) && save(account);
    }

    static public boolean Transfer(int id1 ,int id2,accountType type, double amount ) {
       return withdrawFromCheckingAccount(id1,amount)
                    && deposit(id2,amount,type);
    }

}
