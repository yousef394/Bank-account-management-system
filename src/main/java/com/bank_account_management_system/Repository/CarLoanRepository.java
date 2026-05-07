package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.CarLoan;
import com.bank_account_management_system.model.CheckingAccount;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CarLoanRepository {
    //Encapsulation for  method & data fields will not be used outside
    private static String fileName="src/main/resources/Files/carLoan.txt";

    private static CarLoan parse(String line) {
        String[] lineArray = line.split(BankAccountRepository.separator);
        //to avoid crash
        if(lineArray.length<8)
            return null;

        return new CarLoan(Integer.parseInt(lineArray[0]),lineArray[1]
                , LocalDateTime.parse(lineArray[2]),lineArray[3]
                ,Double.parseDouble(lineArray[4])
                ,Double.parseDouble(lineArray[5]) ,Double.parseDouble(lineArray[6]),lineArray[7]);

    }

    private static String format(CarLoan object) {
        return BankAccountRepository.format( object.getAccountId(),object.getPassword(),object.getDateCreated()
                ,object.getHolderName(), object.getBalance())
                +BankAccountRepository.separator+ object.getLoanAmount()
                +BankAccountRepository.separator+ object.getRemainingAmount()
                +BankAccountRepository.separator+object.getCarModel();
    }


    public static ArrayList<CarLoan> getAllAccounts() {

        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        ArrayList<CarLoan> carLoans = new ArrayList<>();
        CarLoan carLoan;
        for(String line : fileData)
        {
            carLoan = parse(line);

            if(carLoan!=null)
                carLoans.add(carLoan);
        }


        return carLoans;
    }

    public static boolean add(CarLoan carLoan){
        String line =format(carLoan);
        return BankAccountRepository.uploadLineToFile( line,fileName);
    }

    public static CarLoan findById(int accountId) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        CarLoan carLoan;
        for(String line : fileData) {
            carLoan = parse(line);

            if(carLoan != null)
                if (accountId == carLoan.getAccountId())
                    return carLoan;
        }
        return null;
    }

    public static CarLoan findByIdAndPassword(int accountId, String password) {
        ArrayList<String>fileData =  BankAccountRepository.loadDataWithoutSplit(fileName);
        CarLoan carLoan;
        for(String line : fileData) {
            carLoan = parse(line);
            //may be return null
            if(carLoan != null)
                if (accountId == carLoan.getAccountId() && password.equals(carLoan.getPassword()))
                    return carLoan;
        }
        return null;
    }

    public static boolean delete(int accountId) {
        boolean result = false;
        ArrayList<CarLoan> carLoans = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);

        for(CarLoan carLoan : carLoans) {
            //skip
            if(carLoan.getAccountId() == accountId) {
                result = true;
                continue;
            }
            //fill file
            BankAccountRepository.uploadLineToFile(format(carLoan),fileName);

        }
        return result;
    }

    public static boolean update(CarLoan carLoan) {

        boolean result = false;
        ArrayList<CarLoan> carLoans = getAllAccounts();
        //to clear file
        BankAccountRepository.clearFile(fileName);
        for(CarLoan currentCarLoanAccount : carLoans) {
            //update data
            if(currentCarLoanAccount.getAccountId() == carLoan.getAccountId()) {
                currentCarLoanAccount.setPassword(carLoan.getPassword());
                currentCarLoanAccount.setHolderName(carLoan.getHolderName());
                currentCarLoanAccount.setBalance(carLoan.getBalance());
                currentCarLoanAccount.setLoanAmount(carLoan.getLoanAmount());
                currentCarLoanAccount.setRemainingAmount(carLoan.getRemainingAmount());
                currentCarLoanAccount.setCarModel(carLoan.getCarModel());

                result = true;

            }
            //fill file
            BankAccountRepository.uploadLineToFile(format(currentCarLoanAccount),fileName);

        }
        return result;


    }

    public static boolean isExist(int accountId) {
        return BankAccountRepository.isExist(accountId,fileName);
    }
}
