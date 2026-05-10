package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.CheckingAccount;
import com.bank_account_management_system.model.SavingsAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SavingsAccountRepository {
    //Encapsulation for  method & data fields will not be used outside
    private static String fileName="src/main/resources/Files/savingsAccount.txt";

    private static SavingsAccount parse(String line) {
        String[] lineArray = line.split(BankAccountRepository.separator);
        //to avoid crash
        if(lineArray.length<6)
            return null;

        return new SavingsAccount(Integer.parseInt(lineArray[0]),lineArray[1]
                , LocalDateTime.parse(lineArray[2]),lineArray[3]
                ,Double.parseDouble(lineArray[4]),Double.parseDouble(lineArray[5]));

    }

    private static String format(SavingsAccount object) {
        return BankAccountRepository.format( object.getAccountId(),object.getPassword(),object.getDateCreated()
                ,object.getHolderName(), object.getBalance())
                +BankAccountRepository.separator+ object.getInterestRate();
    }


    public static ArrayList<SavingsAccount> getAllAccounts() {

        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
        SavingsAccount savingsAccount;
        for(String line : fileData)
        {
            savingsAccount = parse(line);

            if(savingsAccount!=null)
                savingsAccounts.add(savingsAccount);
        }


        return savingsAccounts;
    }

    public static boolean add(SavingsAccount savingsAccount){
        String line =format(savingsAccount);
        return BankAccountRepository.uploadLineToFile( line,fileName);
    }

    public static SavingsAccount findById(int accountId) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        SavingsAccount savingsAccount;
        for(String line : fileData) {
            savingsAccount = parse(line);
            if(savingsAccount != null)
            if (accountId == savingsAccount.getAccountId())
                return savingsAccount;
        }
        return null;
    }

    public static SavingsAccount findByIdAndPassword(int accountId, String password) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        SavingsAccount savingsAccount;
        for(String line : fileData) {
            savingsAccount = parse(line);
            //may be return null
            if(savingsAccount != null)
            if (accountId == savingsAccount.getAccountId() && password.equals(savingsAccount.getPassword()))
                return savingsAccount;
        }
        return null;
    }

    public static boolean delete(int accountId) {
        boolean result = false;
        ArrayList<SavingsAccount> savingsAccounts = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);

        for(SavingsAccount savingsAccount : savingsAccounts) {
            //skip
            if(savingsAccount.getAccountId() == accountId) {
                result = true;
                continue;
            }
            //fill file
            BankAccountRepository.uploadLineToFile(format(savingsAccount),fileName);

        }
        return result;
    }

    public static boolean update(SavingsAccount savingsAccount) {

        boolean result = false;
        ArrayList<SavingsAccount> savingsAccounts = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);
        for(SavingsAccount currentSavingsAccount : savingsAccounts) {
            //update data
            if(currentSavingsAccount.getAccountId() == savingsAccount.getAccountId()) {
                currentSavingsAccount.setPassword(savingsAccount.getPassword());
                currentSavingsAccount.setHolderName(savingsAccount.getHolderName());
                currentSavingsAccount.setInterestRate(savingsAccount.getInterestRate());

                result = true;

            }
            //fill file
            BankAccountRepository.uploadLineToFile(format(currentSavingsAccount),fileName);

        }
        return result;


    }

    public static boolean isExist(int accountId) {
        return BankAccountRepository.isExist(accountId,fileName);
    }
}
