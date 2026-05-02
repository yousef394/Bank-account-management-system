package com.bank_account_management_system.service;

import com.bank_account_management_system.Data.BankAccountData;
import com.bank_account_management_system.Repository.Repository;
import com.bank_account_management_system.model.*;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class AccountService  {

    public static class createAccount {
        static public boolean CheckingAccount ( int id, String password, String holderName,
        double balance, double overdraft ) throws IOException {
            return BankAccountData.CheckingAccountData.Add(id, password, holderName, balance, overdraft);

        }

        static public boolean SavingAccount ( int id, String password, String holderName,
        double balance, double interestRate ) throws IOException {
            return BankAccountData.SavingAccountData.Add(id, password, holderName, balance, interestRate);

        }

        static public boolean CarLoan ( int id, String password, String holderName,
        double balance, double loanAmount,
        double remainingAmount, String CarModel) throws IOException {
            return BankAccountData.CarLoanData.Add(id, password, holderName, balance, loanAmount, remainingAmount, CarModel);

        }

        static public boolean HomeLoan ( int id, String password, String holderName,
        double balance, double loanAmount,
        double remainingAmount, String propertyAddress) throws IOException {
            return BankAccountData.CarLoanData.Add(id, password, holderName, balance, loanAmount, remainingAmount, propertyAddress);

        }

    }

    public static class DeleteAccount {
        static public boolean CheckingAccount ( int id) throws IOException {
            return BankAccountData.CheckingAccountData.Delete(id);
        }
        static public boolean SavingAccount ( int id) throws IOException {
            return BankAccountData.SavingAccountData.Delete(id);
        }
        static public boolean CarLoan ( int id) throws IOException {
            return BankAccountData.CarLoanData.Delete(id);
        }
        static public boolean homeLoan ( int id) throws IOException {
            return BankAccountData.CarLoanData.Delete(id);
        }
    }

    public static class FindAccount {
        static public CheckingAccount checkingAccount ( int id,String password) throws IOException {
            return BankAccountData.CheckingAccountData.Find(id, password);
        }
        static public SavingsAccount savingAccount ( int id,String password) throws IOException {
            return BankAccountData.SavingAccountData.Find(id, password);
        }
        static public CarLoan carLoan ( int id,String password) throws IOException {
            return BankAccountData.CarLoanData.Find(id, password);
        }
        static public HomeLoan homeLoan ( int id,String password) throws IOException {
            return BankAccountData.HomeLoanData.Find(id, password);
        }
    }

    public static class SaveAccount {
        static public boolean checkingAccount (CheckingAccount checkingAccount) throws IOException
        {
            return checkingAccount.Save();
        }
        static public boolean savingsAccount(SavingsAccount savingsAccount) throws IOException {
            return savingsAccount.Save();
        }
        static public boolean carLoan (CarLoan carLoan) throws IOException
        {
            return carLoan.Save();
        }
        static public boolean homeLoan (HomeLoan homeLoan) throws IOException
        {
            return homeLoan.Save();
        }
    }

    public static ArrayList<BankAccount> loadAccounts() throws IOException {
        ArrayList<BankAccount> accounts = new ArrayList<>();

        for(CheckingAccount CA : BankAccountData.CheckingAccountData.GetAllAccounts())
            accounts.add(CA);

        for(SavingsAccount SA : BankAccountData.SavingAccountData.GetAllAccounts())
            accounts.add(SA);

        for (CarLoan CL : BankAccountData.CarLoanData.GetAllAccounts())
            accounts.add(CL);

        for (HomeLoan HL : BankAccountData.HomeLoanData.GetAllAccounts())
            accounts.add(HL);

        return accounts;
    }

    public static void applyMonthlyUpdates(BankAccountData bankAccountData) throws IOException {
        ArrayList<BankAccount> accounts = loadAccounts();

        for(BankAccount BA : accounts)
            BA.applyMonthlyUpdate();


    }


    public static class Deposite{
        static public boolean checkingAccount (int id,String Password,Double amount) throws IOException {
            CheckingAccount checkingAccount = BankAccountData.CheckingAccountData.Find(id, Password);
            checkingAccount.deposit(amount);

            return checkingAccount.Save();
        }
        static public boolean savingAccount (int id,String Password,Double amount) throws IOException {
            SavingsAccount savingsAccount = BankAccountData.SavingAccountData.Find(id, Password);
            savingsAccount.deposit(amount);

            return savingsAccount.Save();
        }
        static public boolean carLoan (int id,String Password,Double amount) throws IOException {
            CarLoan carLoan = BankAccountData.CarLoanData.Find(id, Password);
            carLoan.deposit(amount);

            return carLoan.Save();
        }
        static public boolean homeLoan (int id,String Password,Double amount) throws IOException {
            HomeLoan homeLoan = BankAccountData.HomeLoanData.Find(id, Password);
            homeLoan.deposit(amount);

            return homeLoan.Save();
        }
    }

    public static class withdraw{
        static public boolean CheckingAccount (int id,String Password,Double amount) throws IOException {
            CheckingAccount checkingAccount = BankAccountData.CheckingAccountData.Find(id, Password);
            checkingAccount.withdraw(amount);
            return checkingAccount.Save();
        }
    }



}
