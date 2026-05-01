package com.bank_account_management_system.Data;


import com.bank_account_management_system.model.CheckingAccount;
import com.bank_account_management_system.model.SavingsAccount;
import javafx.util.converter.LocalDateStringConverter;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class BankAccountData {

    static public class CheckingAccountData{

        static String checkingFileName = "C:\\Users\\pc\\Bank-account-management-system\\" +
                "src\\main\\java\\com\\bank_account_management_system\\Data\\checkingAccountFile.txt";
        static String delemetar="#//#";

        public static CheckingAccount Find(int accountId,String password) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(checkingFileName));
            String line;
            String[] checkingAccount ;
            while((line = br.readLine())!= null) {
                checkingAccount =line.split(delemetar);
                if(Integer.parseInt(checkingAccount[0]) == accountId && checkingAccount[1].equals( password)) {
                    br.close();
                    return new CheckingAccount(Integer.parseInt(checkingAccount[0]),checkingAccount[1]
                            ,LocalDateTime.parse(checkingAccount[2]),checkingAccount[3]
                            ,Double.parseDouble(checkingAccount[4])
                            ,Double.parseDouble(checkingAccount[5]) ,Double.parseDouble(checkingAccount[6]));
                }
            }
                br.close();
            return null;
        }

        public static boolean IsExist(int accountId) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(checkingFileName));
            String line;
            String[] checkingAccount ;
            while((line = br.readLine())!= null) {
                checkingAccount =line.split(delemetar);
                if(Integer.parseInt(checkingAccount[0]) == accountId ) {
                    br.close();
                    return true ;
                }

            }
                br.close();
            return false;
        }

        public static boolean Add(int accountId, String password, String holderName,
                                                    double balance,
                                                    double overdraftLimit ) throws IOException {

            if(accountId==0||IsExist(accountId)) {
                return false;
            }
             LocalDateTime DateCreated = LocalDateTime.now();
            String CheckingAccount = accountId +delemetar+ password+delemetar+DateCreated.toString()
                    +delemetar+ holderName +delemetar+ balance
                    +delemetar+ overdraftLimit +delemetar+ 0+"\n";
            // 0 for fee

            BufferedWriter bw = new BufferedWriter(new FileWriter(checkingFileName,true));

            bw.write(CheckingAccount);
            bw.close();

            return true;
        }

        public static ArrayList<CheckingAccount> GetAllAccounts() throws IOException {

                BufferedReader br = new BufferedReader(new FileReader(checkingFileName));
                String line;
                String[] accountData ;
                ArrayList<CheckingAccount> checkingAccounts = new ArrayList<>();
                while((line = br.readLine())!= null) {
                    accountData =line.split(delemetar);

                   checkingAccounts.add( new CheckingAccount(Integer.parseInt(accountData[0]),accountData[1]
                           ,LocalDateTime.parse(accountData[2]),accountData[3]
                           ,Double.parseDouble(accountData[4])
                           ,Double.parseDouble(accountData[5]) ,Double.parseDouble(accountData[6])) );

                }
                br.close();
                return checkingAccounts;

        }

        public static boolean Delete(int accountId) throws IOException  {
            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<CheckingAccount>  checkingAccounts = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(checkingFileName,false));
            String data;
            for(CheckingAccount checkingAccount : checkingAccounts) {
                if(checkingAccount.getAccountId()==accountId) {
                    continue;
                }
                data  = checkingAccount.getAccountId() +delemetar+
                        checkingAccount.getPassword() +delemetar+ checkingAccount.getDateCreated()+delemetar+
                        checkingAccount.getHolderName()
                        +delemetar+ checkingAccount.getBalance()+delemetar+ checkingAccount.getOverdraftLimit()
                        +delemetar+ checkingAccount.getFee()+"\n";
                      bw.write(data);
            }

            bw.close();
            return true;

        }

        public static boolean Update(int accountId, String password, String holderName) throws IOException {

            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<CheckingAccount>  checkingAccounts = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(checkingFileName,false));

            String data;
            for(CheckingAccount checkingAccount : checkingAccounts) {
                if(checkingAccount.getAccountId()==accountId) {
                   checkingAccount.setPassword(password);
                   checkingAccount.setHolderName(holderName);
                }
                data  = checkingAccount.getAccountId() +delemetar+
                        checkingAccount.getPassword() +delemetar+ checkingAccount.getDateCreated()+delemetar+
                        checkingAccount.getHolderName()
                        +delemetar+ checkingAccount.getBalance()+delemetar+ checkingAccount.getOverdraftLimit()
                        +delemetar+ checkingAccount.getFee()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;


        }

    }

    static public class SavingAccountData{

        static String savingsFileName = "C:\\Users\\pc\\Bank-account-management-system\\src\\main\\java" +
                "\\com\\bank_account_management_system\\Data\\savingsAccountFile.txt";
        static String delemetar="#//#";

        public static SavingsAccount Find(int accountId, String password) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(savingsFileName));
            String line;
            String[] savingAccount ;
            while((line = br.readLine())!= null) {
                savingAccount =line.split(delemetar);
                if(Integer.parseInt(savingAccount[0]) == accountId && savingAccount[1].equals( password)) {
                    br.close();
                    return  new SavingsAccount
                            ( Integer.parseInt(savingAccount[0]),savingAccount[1] , LocalDateTime.parse(savingAccount[2]) ,
                                    savingAccount[3],Double.parseDouble(savingAccount[4])
                                    ,Double.parseDouble(savingAccount[5])) ;
                }
            }
            br.close();
            return null;
        }

        public static boolean IsExist(int accountId) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(savingsFileName));
            String line;
            String[] savingsAccount ;
            while((line = br.readLine())!= null) {
                savingsAccount =line.split(delemetar);
                if(Integer.parseInt(savingsAccount[0]) == accountId ) {
                    br.close();
                    return true ;
                }

            }
            br.close();
            return false;
        }

        public static boolean Add(int accountId, String password, String holderName,
                                  double balance,double interests) throws IOException {
            LocalDateTime DateCreated =LocalDateTime.now();

            if(accountId==0||IsExist(accountId)) {
                return false;
            }

            String savingsAccount = accountId +delemetar+ password +delemetar+DateCreated.toString()+
                    delemetar+holderName +delemetar+ balance+delemetar+ interests +"\n";
            // 0 for fee

            BufferedWriter bw = new BufferedWriter(new FileWriter(savingsFileName,true));

            bw.write(savingsAccount);
            bw.close();

            return true;
        }

        public static ArrayList<SavingsAccount> GetAllAccounts() throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(savingsFileName));
            String line;
            String[] accountData ;
            ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
            while((line = br.readLine())!= null) {
                accountData =line.split(delemetar);

                savingsAccounts.add( new SavingsAccount
                        ( Integer.parseInt(accountData[0]),accountData[1] ,LocalDateTime.parse(accountData[2]),
                                accountData[3],Double.parseDouble(accountData[4])
                                ,Double.parseDouble(accountData[5])) );


            }
            br.close();
            return savingsAccounts;

        }

        public static boolean Delete(int accountId) throws IOException  {
            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<SavingsAccount>  savingsAccounts = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(savingsFileName,false));
            String data;

            for(SavingsAccount savingsAccount : savingsAccounts) {
                if(savingsAccount.getAccountId()==accountId) {
                    continue;
                }
                data  = savingsAccount.getAccountId() +delemetar+
                        savingsAccount.getPassword() +delemetar+savingsAccount.getDateCreated().toString()
                        +delemetar+ savingsAccount.getHolderName()
                        +delemetar+ savingsAccount.getBalance()
                        +delemetar+ savingsAccount.getInterestRate()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;

        }

        public static boolean Update(int accountId, String password, String holderName) throws IOException {

            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<SavingsAccount>  savingsAccounts = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(savingsFileName,false));

            String data;
            for(SavingsAccount savingsAccount : savingsAccounts) {
                if(savingsAccount.getAccountId()==accountId) {
                    savingsAccount.setPassword(password);
                    savingsAccount.setHolderName(holderName);
                }
                data  = savingsAccount.getAccountId() +delemetar+
                        savingsAccount.getPassword() +delemetar+savingsAccount.getDateCreated().toString()
                        +delemetar+ savingsAccount.getHolderName()
                        +delemetar+ savingsAccount.getBalance()
                        +delemetar+ savingsAccount.getInterestRate()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;


        }

    }

    static public class CarLoanData{

        static String savingsFileName = "C:\\Users\\pc\\Bank-account-management-system\\src\\main\\java" +
                "\\com\\bank_account_management_system\\Data\\savingsAccountFile.txt";
        static String delemetar="#//#";

        public static SavingsAccount Find(int accountId, String password) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(savingsFileName));
            String line;
            String[] savingAccount ;
            while((line = br.readLine())!= null) {
                savingAccount =line.split(delemetar);
                if(Integer.parseInt(savingAccount[0]) == accountId && savingAccount[1].equals( password)) {
                    br.close();
                    return  new SavingsAccount
                            ( Integer.parseInt(savingAccount[0]),savingAccount[1] , LocalDateTime.parse(savingAccount[2]) ,
                                    savingAccount[3],Double.parseDouble(savingAccount[4])
                                    ,Double.parseDouble(savingAccount[5])) ;
                }
            }
            br.close();
            return null;
        }

        public static boolean IsExist(int accountId) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(savingsFileName));
            String line;
            String[] savingsAccount ;
            while((line = br.readLine())!= null) {
                savingsAccount =line.split(delemetar);
                if(Integer.parseInt(savingsAccount[0]) == accountId ) {
                    br.close();
                    return true ;
                }

            }
            br.close();
            return false;
        }

        public static boolean Add(int accountId, String password, String holderName,
                                  double balance,double interests) throws IOException {
            LocalDateTime DateCreated =LocalDateTime.now();

            if(accountId==0||IsExist(accountId)) {
                return false;
            }

            String savingsAccount = accountId +delemetar+ password +delemetar+DateCreated.toString()+
                    delemetar+holderName +delemetar+ balance+delemetar+ interests +"\n";
            // 0 for fee

            BufferedWriter bw = new BufferedWriter(new FileWriter(savingsFileName,true));

            bw.write(savingsAccount);
            bw.close();

            return true;
        }

        public static ArrayList<SavingsAccount> GetAllAccounts() throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(savingsFileName));
            String line;
            String[] accountData ;
            ArrayList<SavingsAccount> savingsAccounts = new ArrayList<>();
            while((line = br.readLine())!= null) {
                accountData =line.split(delemetar);

                savingsAccounts.add( new SavingsAccount
                        ( Integer.parseInt(accountData[0]),accountData[1] ,LocalDateTime.parse(accountData[2]),
                                accountData[3],Double.parseDouble(accountData[4])
                                ,Double.parseDouble(accountData[5])) );


            }
            br.close();
            return savingsAccounts;

        }

        public static boolean Delete(int accountId) throws IOException  {
            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<SavingsAccount>  savingsAccounts = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(savingsFileName,false));
            String data;

            for(SavingsAccount savingsAccount : savingsAccounts) {
                if(savingsAccount.getAccountId()==accountId) {
                    continue;
                }
                data  = savingsAccount.getAccountId() +delemetar+
                        savingsAccount.getPassword() +delemetar+savingsAccount.getDateCreated().toString()
                        +delemetar+ savingsAccount.getHolderName()
                        +delemetar+ savingsAccount.getBalance()
                        +delemetar+ savingsAccount.getInterestRate()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;

        }

        public static boolean Update(int accountId, String password, String holderName) throws IOException {

            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<SavingsAccount>  savingsAccounts = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(savingsFileName,false));

            String data;
            for(SavingsAccount savingsAccount : savingsAccounts) {
                if(savingsAccount.getAccountId()==accountId) {
                    savingsAccount.setPassword(password);
                    savingsAccount.setHolderName(holderName);
                }
                data  = savingsAccount.getAccountId() +delemetar+
                        savingsAccount.getPassword() +delemetar+savingsAccount.getDateCreated().toString()
                        +delemetar+ savingsAccount.getHolderName()
                        +delemetar+ savingsAccount.getBalance()
                        +delemetar+ savingsAccount.getInterestRate()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;


        }

    }



}

