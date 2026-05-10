package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.SavingsAccount;
import java.time.LocalDateTime;


public class SavingsAccountRepository extends BaseRepository<SavingsAccount> {

    public SavingsAccountRepository() {
        //file path
        super("src/main/resources/Files/savingsAccount.txt");

    }

    //=======implements abstract methods======

    @Override
    protected SavingsAccount parse(String line) {
        String[] lineArray = line.split(separator);
        //to avoid crash
        if(lineArray.length<6)
            return null;

        return new SavingsAccount(Integer.parseInt(lineArray[0]),lineArray[1]
                , LocalDateTime.parse(lineArray[2]),lineArray[3]
                ,Double.parseDouble(lineArray[4]),Double.parseDouble(lineArray[5]));

    }

    @Override
    protected String format(SavingsAccount object) {
        return super.commonFormat(object)+ separator+ object.getInterestRate();
    }

    @Override
    protected int getId(SavingsAccount object) {
        return object.getAccountId();
    }

}
