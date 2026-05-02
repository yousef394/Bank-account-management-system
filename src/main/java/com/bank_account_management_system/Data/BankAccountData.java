package com.bank_account_management_system.Data;


import com.bank_account_management_system.model.CarLoan;
import com.bank_account_management_system.model.CheckingAccount;
import com.bank_account_management_system.model.HomeLoan;
import com.bank_account_management_system.model.SavingsAccount;

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

        public static boolean Update(int accountId, String password, String holderName, double balance,double overdraftLimit) throws IOException {

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
                   checkingAccount.setBalance(balance);
                   checkingAccount.setOverdraftLimit(overdraftLimit);
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

        public static boolean Update(int accountId, String password, String holderName,double balance ,double interests) throws IOException {

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
                    savingsAccount.setBalance(balance);
                    savingsAccount.setInterestRate(interests);
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

        static String carLoanFileName = "C:\\Users\\pc\\Bank-account-management-system\\src\\main\\java" +
                "\\com\\bank_account_management_system\\Data\\carLoan.txt";
        static String delemetar="#//#";

        public static CarLoan Find(int accountId, String password) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(carLoanFileName));
            String line;
            String[] carLoan ;
            while((line = br.readLine())!= null) {
                carLoan =line.split(delemetar);
                if(Integer.parseInt(carLoan[0]) == accountId && carLoan[1].equals( password)) {
                    br.close();
                    return  new CarLoan
                            ( Integer.parseInt(carLoan[0]),carLoan[1] , LocalDateTime.parse(carLoan[2]) ,
                                    carLoan[3],Double.parseDouble(carLoan[4])
                                    ,Double.parseDouble(carLoan[5]),Double.parseDouble(carLoan[6]),carLoan[7]) ;
                }
            }
            br.close();
            return null;
        }

        public static boolean IsExist(int accountId) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(carLoanFileName));
            String line;
            String[] carLoan ;
            while((line = br.readLine())!= null) {
                carLoan =line.split(delemetar);
                if(Integer.parseInt(carLoan[0]) == accountId ) {
                    br.close();
                    return true ;
                }

            }
            br.close();
            return false;
        }

        public static boolean Add(int accountId, String password, String holderName,
        double balance, double loanAmount, double remainingAmount , String CarModel) throws IOException {

            LocalDateTime DateCreated =LocalDateTime.now();

            if(accountId==0||IsExist(accountId)) {
                return false;
            }

            String carLoan = accountId +delemetar+ password +delemetar+DateCreated.toString()+
                    delemetar+holderName +delemetar+ balance+delemetar+ loanAmount+delemetar
                    +remainingAmount+delemetar+CarModel+"\n";


            BufferedWriter bw = new BufferedWriter(new FileWriter(carLoanFileName,true));

            bw.write(carLoan);
            bw.close();

            return true;
        }

        public static ArrayList<CarLoan> GetAllAccounts() throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(carLoanFileName));
            String line;
            String[] accountData ;
            ArrayList<CarLoan> carLoans = new ArrayList<>();
            while((line = br.readLine())!= null) {
                accountData =line.split(delemetar);

                carLoans.add( new CarLoan
                        ( Integer.parseInt(accountData[0]),accountData[1] , LocalDateTime.parse(accountData[2]) ,
                                accountData[3],Double.parseDouble(accountData[4])
                                ,Double.parseDouble(accountData[5]),Double.parseDouble(accountData[6]),accountData[7]) );


            }
            br.close();
            return carLoans;

        }

        public static boolean Delete(int accountId) throws IOException  {
            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<CarLoan>  carLoans = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(carLoanFileName,false));
            String data;

            for(CarLoan carLoan : carLoans) {
                if(carLoan.getAccountId()==accountId) {
                    continue;
                }
                data  = carLoan.getAccountId() +delemetar+
                        carLoan.getPassword() +delemetar+carLoan.getDateCreated().toString()
                        +delemetar+ carLoan.getHolderName()
                        +delemetar+ carLoan.getBalance()
                        +delemetar+ carLoan.getLoanAmount()
                        +delemetar+carLoan.getRemainingAmount()
                        +delemetar+carLoan.getCarModel()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;

        }

        public static boolean Update(int accountId, String password, String holderName
                ,double balance, double loanAmount , double remainingAmount, String carModel) throws IOException {

            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<CarLoan>  carLoans = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(carLoanFileName,false));

            String data;
            for(CarLoan carLoan : carLoans) {
                if(carLoan.getAccountId()==accountId) {
                    carLoan.setPassword(password);
                    carLoan.setHolderName(holderName);
                    carLoan.setBalance(balance);
                    carLoan.setLoanAmount(loanAmount);
                    carLoan.setRemainingAmount(remainingAmount);
                    carLoan.setCarModel(carModel);
                }
                data  = carLoan.getAccountId() +delemetar+
                        carLoan.getPassword() +delemetar+carLoan.getDateCreated().toString()
                        +delemetar+ carLoan.getHolderName()
                        +delemetar+ carLoan.getBalance()
                        +delemetar+ carLoan.getLoanAmount()
                        +delemetar+carLoan.getRemainingAmount()
                        +delemetar+carLoan.getCarModel()+"\n";

                bw.write(data);
            }

            bw.close();
            return true;


        }

    }

    static public class HomeLoanData{

        static String homeLoanFileName = "C:\\Users\\pc\\Bank-account-management-system\\src\\" +
                "main\\java\\com\\bank_account_management_system\\Data\\homeLoan.txt";
        static String delemetar="#//#";

        public static HomeLoan Find(int accountId, String password) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(homeLoanFileName));
            String line;
            String[] homeLoan ;
            while((line = br.readLine())!= null) {
                homeLoan =line.split(delemetar);
                if(Integer.parseInt(homeLoan[0]) == accountId && homeLoan[1].equals( password)) {
                    br.close();

                    return  new HomeLoan
                            ( Integer.parseInt(homeLoan[0]),homeLoan[1] , LocalDateTime.parse(homeLoan[2]) ,
                                    homeLoan[3],Double.parseDouble(homeLoan[4])
                                    ,Double.parseDouble(homeLoan[5]),Double.parseDouble(homeLoan[6]),homeLoan[7]) ;
                }
            }
            br.close();
            return null;
        }

        public static boolean IsExist(int accountId) throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(homeLoanFileName));
            String line;
            String[] homeLone ;
            while((line = br.readLine())!= null) {
                homeLone =line.split(delemetar);
                if(Integer.parseInt(homeLone[0]) == accountId ) {
                    br.close();
                    return true ;
                }

            }
            br.close();
            return false;
        }

        public static boolean Add(int accountId, String password, String holderName,
                                  double balance, double loanAmount, double remainingAmount , String propertyAddress) throws IOException {

            LocalDateTime DateCreated =LocalDateTime.now();

            if(accountId==0||IsExist(accountId)) {
                return false;
            }

            String carLoan = accountId +delemetar+ password +delemetar+DateCreated.toString()+
                    delemetar+holderName +delemetar+ balance+delemetar+ loanAmount+delemetar
                    +remainingAmount+delemetar+propertyAddress+"\n";


            BufferedWriter bw = new BufferedWriter(new FileWriter(homeLoanFileName,true));

            bw.write(carLoan);
            bw.close();

            return true;
        }

        public static ArrayList<HomeLoan> GetAllAccounts() throws IOException {

            BufferedReader br = new BufferedReader(new FileReader(homeLoanFileName));
            String line;
            String[] accountData ;
            ArrayList<HomeLoan> homeLoans = new ArrayList<>();
            while((line = br.readLine())!= null) {
                accountData =line.split(delemetar);

                homeLoans.add( new HomeLoan
                        ( Integer.parseInt(accountData[0]),accountData[1] , LocalDateTime.parse(accountData[2]) ,
                                accountData[3],Double.parseDouble(accountData[4])
                                ,Double.parseDouble(accountData[5]),Double.parseDouble(accountData[6]),accountData[7]) );


            }
            br.close();
            return homeLoans;

        }

        public static boolean Delete(int accountId) throws IOException  {
            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<HomeLoan>  homeLoans = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(homeLoanFileName,false));
            String data;

            for(HomeLoan homeLoan : homeLoans) {
                if(homeLoan.getAccountId()==accountId) {
                    continue;
                }
                data  = homeLoan.getAccountId() +delemetar+
                        homeLoan.getPassword() +delemetar+homeLoan.getDateCreated().toString()
                        +delemetar+ homeLoan.getHolderName()
                        +delemetar+ homeLoan.getBalance()
                        +delemetar+ homeLoan.getLoanAmount()
                        +delemetar+homeLoan.getRemainingAmount()
                        +delemetar+homeLoan.getPropertyAddress()+"\n";
                bw.write(data);
            }

            bw.close();
            return true;

        }

        public static boolean Update(int accountId, String password, String holderName,double balance
                                    ,double loanAmount , double remainingAmount , String propertyAddress) throws IOException {

            if(accountId==0||! IsExist(accountId)) {
                return false;
            }

            ArrayList<HomeLoan>  homeLoans = GetAllAccounts();


            BufferedWriter bw = new BufferedWriter(new FileWriter(homeLoanFileName,false));

            String data;
            for(HomeLoan homeLoan : homeLoans) {
                if(homeLoan.getAccountId()==accountId) {
                    homeLoan.setPassword(password);
                    homeLoan.setHolderName(holderName);
                    homeLoan.setBalance(balance);
                    homeLoan.setLoanAmount(loanAmount);
                    homeLoan.setRemainingAmount(remainingAmount);
                    homeLoan.setPropertyAddress(propertyAddress);
                }
                data  = homeLoan.getAccountId() +delemetar+
                        homeLoan.getPassword() +delemetar+homeLoan.getDateCreated().toString()
                        +delemetar+ homeLoan.getHolderName()
                        +delemetar+ homeLoan.getBalance()
                        +delemetar+ homeLoan.getLoanAmount()
                        +delemetar+homeLoan.getRemainingAmount()
                        +delemetar+homeLoan.getPropertyAddress()+"\n";

                bw.write(data);
            }

            bw.close();
            return true;


        }

    }


}

