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
            CheckingAccountRepository.add((CheckingAccount) account);
        else if(account instanceof SavingsAccount)
            SavingsAccountRepository.add((SavingsAccount) account);
        else if (account instanceof CarLoan)
            CarLoanRepository.add((CarLoan) account);
        else if (account instanceof HomeLoan)
            HomeLoanRepository.add((HomeLoan) account);

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
            CheckingAccountRepository.update((CheckingAccount) account);
        else if(account instanceof SavingsAccount)
            SavingsAccountRepository.update((SavingsAccount) account);
        else if (account instanceof CarLoan)
            CarLoanRepository.update((CarLoan) account);
        else if (account instanceof HomeLoan)
            HomeLoanRepository.update((HomeLoan) account);

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

        for (BankAccount BA : accounts)
            BA.applyMonthlyUpdate();


    }

    static public boolean deposite(int id, Double amount, accountType type) {
        switch (type) {
            case CHECKING:
                CheckingAccount CA = CheckingAccountRepository.findById(id);
                CA.deposit(amount);
                return save(CA);
            case SAVINGS:
                SavingsAccount SA = SavingsAccountRepository.findById(id);
                SA.deposit(amount);
                return save(SA);
            case CARLOAN:
                CarLoan cAR = CarLoanRepository.findById(id);
                cAR.deposit(amount);
                return save(cAR);
            case HOMELOAN:
                HomeLoan hL = HomeLoanRepository.findById(id);
                hL.deposit(amount);
                return save(hL);

        }
        return false;
    }

    static public boolean withdrawFromCheckingAccount(int id, Double amount) {
        CheckingAccount checkingAccount = CheckingAccountRepository.findById(id);

        if(checkingAccount == null)
            return false;

        checkingAccount.withdraw(amount);
        return save(checkingAccount);
    }

}
