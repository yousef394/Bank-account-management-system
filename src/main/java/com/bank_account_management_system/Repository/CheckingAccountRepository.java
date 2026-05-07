package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.CheckingAccount;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

 public  class CheckingAccountRepository  {

     //Encapsulation for  method & data fields will not be used outside
    private static String fileName="src/main/resources/Files/checkingAccount.txt";

    private static CheckingAccount parse(String line) {
       String[] lineArray = line.split(BankAccountRepository.separator);
       //to avoid crash
       if(lineArray.length<7)
           return null;

        return new CheckingAccount(Integer.parseInt(lineArray[0]),lineArray[1]
                , LocalDateTime.parse(lineArray[2]),lineArray[3]
                ,Double.parseDouble(lineArray[4])
                ,Double.parseDouble(lineArray[5]) ,Double.parseDouble(lineArray[6]));

    }

    private static String format(CheckingAccount object) {
     return BankAccountRepository.format( object.getAccountId(),object.getPassword(),object.getDateCreated()
             ,object.getHolderName(), object.getBalance())
             +BankAccountRepository.separator+ object.getOverdraftLimit()
             +BankAccountRepository.separator+ object.getFee();
    }


    public static ArrayList<CheckingAccount> getAllAccounts() {

      ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
      ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
      CheckingAccount checkingAccount;
      for(String line : fileData)
      {
          checkingAccount = parse(line);

          if(checkingAccount!=null)
          checkingAccounts.add(checkingAccount);
      }


      return checkingAccounts;
    }

    public static boolean add(CheckingAccount checkingAccount){
        String line =format(checkingAccount);
        return BankAccountRepository.uploadLineToFile( line,fileName);
    }

    public static CheckingAccount findById(int accountId) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        CheckingAccount checkingAccount;
        for(String line : fileData) {
            checkingAccount = parse(line);
                    if(checkingAccount == null)
                        return null;

            if (accountId == checkingAccount.getAccountId())
                return checkingAccount;
        }
        return null;
    }

    public static CheckingAccount findByIdAndPassword(int accountId, String password) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        CheckingAccount checkingAccount;
        for(String line : fileData) {
            checkingAccount = parse(line);
            //may be return null
            if(checkingAccount == null)
                return null;

            if (accountId == checkingAccount.getAccountId() && password.equals(checkingAccount.getPassword()))
                return checkingAccount;
        }
        return null;
    }

    public static boolean delete(int accountId) {
        boolean result = false;
        ArrayList<CheckingAccount> checkingAccounts = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);

        for(CheckingAccount checkingAccount : checkingAccounts) {
            //skip
             if(checkingAccount.getAccountId() == accountId) {
                result = true;
                 continue;
             }
             //fill file
            BankAccountRepository.uploadLineToFile(format(checkingAccount),fileName);

        }
        return result;
    }

    public static boolean update(CheckingAccount checkingAccount) {

        boolean result = false;
         ArrayList<CheckingAccount> checkingAccounts = getAllAccounts();
         //to clear file
        BankAccountRepository.clearFile(fileName);
         for(CheckingAccount currentCheckingAccount : checkingAccounts) {
             //update data
             if(currentCheckingAccount.getAccountId() == checkingAccount.getAccountId()) {
                 currentCheckingAccount.setPassword(checkingAccount.getPassword());
                 currentCheckingAccount.setHolderName(checkingAccount.getHolderName());
                 currentCheckingAccount.setBalance(checkingAccount.getBalance());
                 currentCheckingAccount.setOverdraftLimit(checkingAccount.getOverdraftLimit());

                 result = true;

             }
             //fill file
             BankAccountRepository.uploadLineToFile(format(currentCheckingAccount),fileName);

         }
         return result;


    }

    public static boolean isExist(int accountId) {
         return BankAccountRepository.isExist(accountId,fileName);
     }
}

