package com.bank_account_management_system.Data;

import com.bank_account_management_system.model.Transaction;
import com.bank_account_management_system.model.TransactionType;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionData {

    static String transactionFileName = "src\\main\\java\\com\\bank_account_management_system\\Data\\transactionsFile.txt";
    static String delemeter = "#//#";

    static public boolean saveTransaction(Transaction transaction) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter( transactionFileName,true));

        String line =
                transaction.getAccountId()+delemeter
                +transaction.getType()+delemeter
                +transaction.getAmount()+delemeter
                +transaction.getBalanceBefore()+delemeter
                +transaction.getBalanceAfter()+delemeter
                +transaction.getDate()+"\n";

        bw.write(line);

        bw.close();

        return true;

    }

    static public ArrayList<Transaction> loadTransactions() throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(transactionFileName));

        String line ;
        String[] transaction ;
        ArrayList<Transaction> transactions = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            transaction = line.split(delemeter);

            transactions.add(
                    new Transaction(Integer.parseInt(transaction[0])
                            , TransactionType.valueOf(transaction[1]),
                    Double.parseDouble(transaction[2]),Double.parseDouble(transaction[3]),
                            Double.parseDouble(transaction[4]),
                            LocalDateTime.parse(transaction[5]))
            );

        }

        br.close();
        return transactions;

    }

    static public ArrayList<Transaction> findAccountTransaction(int accountId) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(transactionFileName));

        String line ;
        String[] transaction ;
        ArrayList<Transaction> transactions = new ArrayList<>();

        for(Transaction t: loadTransactions()){
            if(t.getAccountId() == accountId){
                transactions.add(t);
            }
        }


        br.close();
        return transactions;

    }




}
