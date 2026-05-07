package com.bank_account_management_system.Repository;

import com.bank_account_management_system.model.Transaction;
import com.bank_account_management_system.model.TransactionType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionRepository {

    static String transactionFileName = "src/main/resources/Files/transactionsFile.txt";

    static String separater = "#//#";

    static private String formate(Transaction transaction){
        return transaction.getAccountId()+ separater
                +transaction.getType()+ separater
                +transaction.getAmount()+ separater
                +transaction.getBalanceBefore()+ separater
                +transaction.getBalanceAfter()+ separater
                +transaction.getDate();
    }

    static private Transaction parse(String line) {

        String[] dataLine = line.split(separater);

        if(line.length() < 6) {
            return null;
        }
       return new Transaction(Integer.parseInt(dataLine[0])
                , TransactionType.valueOf(dataLine[1]),
                Double.parseDouble(dataLine[2]),Double.parseDouble(dataLine[3]),
                Double.parseDouble(dataLine[4]),
                LocalDateTime.parse(dataLine[5]));
    }


    static private boolean uploadLineToFile(String line) {
        boolean result = false;
        try( BufferedWriter bw = new BufferedWriter(new FileWriter( transactionFileName,true)) )
        {
            bw.write(line+"\n");
            result =true;
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }

        return result;
    }


    static public boolean saveTransaction(Transaction transaction){
        return uploadLineToFile(formate(transaction));
    }

    static public ArrayList<Transaction> allTransactions() {

        ArrayList<Transaction> transactions = new ArrayList<>();
      try( BufferedReader br = new BufferedReader(new FileReader(transactionFileName)) )
      {

          String line ;
          Transaction transaction ;
          while ((line = br.readLine()) != null) {
              transaction = parse(line);

                if (transaction != null) {
                    transactions.add(transaction);
                }
          }
      }
      catch(IOException e){
          throw new RuntimeException(e);
      }

        return transactions;

    }

    static public ArrayList<Transaction> accountTransactions(int accountId)  {
        ArrayList<Transaction> transactions = new ArrayList<>();

        for(Transaction transaction : allTransactions()){
            if(transaction.getAccountId() == accountId){
                transactions.add(transaction);
            }
        }
        return transactions;
    }




}
