package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.CarLoan;
import com.bank_account_management_system.model.HomeLoan;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HomeLoanRepository {
    //Encapsulation for  method & data fields will not be used outside
    private static String fileName="src/main/resources/Files/homeLoan.txt";

    private static HomeLoan parse(String line) {
        String[] lineArray = line.split(BankAccountRepository.separator);
        //to avoid crash
        if(lineArray.length<8)
            return null;

        return new HomeLoan(Integer.parseInt(lineArray[0]),lineArray[1]
                , LocalDateTime.parse(lineArray[2]),lineArray[3]
                ,Double.parseDouble(lineArray[4])
                ,Double.parseDouble(lineArray[5]) ,Double.parseDouble(lineArray[6]),lineArray[7]);

    }

    private static String format(HomeLoan object) {
        return BankAccountRepository.format( object.getAccountId(),object.getPassword(),object.getDateCreated()
                ,object.getHolderName(), object.getBalance())
                +BankAccountRepository.separator+ object.getLoanAmount()
                +BankAccountRepository.separator+ object.getRemainingAmount()
                +BankAccountRepository.separator+object.getPropertyAddress();
    }


    public static ArrayList<HomeLoan> getAllAccounts() {

        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        ArrayList<HomeLoan> homeLoans = new ArrayList<>();
        HomeLoan homeLoan;
        for(String line : fileData)
        {
            homeLoan = parse(line);

            if(homeLoan!=null)
                homeLoans.add(homeLoan);
        }


        return homeLoans;
    }

    public static boolean add(HomeLoan homeLoan){
        String line =format(homeLoan);
        return BankAccountRepository.uploadLineToFile( line,fileName);
    }

    public static HomeLoan findById(int accountId) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        HomeLoan homeLoan;
        for(String line : fileData) {
            homeLoan = parse(line);

            if(homeLoan != null)
                if (accountId == homeLoan.getAccountId())
                    return homeLoan;
        }
        return null;
    }


    public static boolean delete(int accountId) {
        boolean result = false;
        ArrayList<HomeLoan> homeLoans = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);

        for(HomeLoan homeLoan : homeLoans) {
            //skip
            if(homeLoan.getAccountId() == accountId) {
                result = true;
                continue;
            }
            //fill file
            BankAccountRepository.uploadLineToFile(format(homeLoan),fileName);

        }
        return result;
    }

    public static boolean update(HomeLoan homeLoan) {

        boolean result = false;
        ArrayList<HomeLoan> homeLoans = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);
        for(HomeLoan currentHomeLoanAccount : homeLoans) {
            //update data
            if(currentHomeLoanAccount.getAccountId() == homeLoan.getAccountId()) {
                currentHomeLoanAccount = homeLoan;
                result = true;

            }
            //fill file
            BankAccountRepository.uploadLineToFile(format(currentHomeLoanAccount),fileName);

        }
        return result;


    }

    public static boolean isExist(int accountId) {
        return BankAccountRepository.isExist(accountId,fileName);
    }

}
