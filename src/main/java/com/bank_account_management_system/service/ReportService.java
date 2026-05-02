package com.bank_account_management_system.service;

import com.bank_account_management_system.Data.TransactionData;
import com.bank_account_management_system.model.Transaction;

import java.io.IOException;
import java.util.ArrayList;

public class ReportService
{

    public String generateAccountReport(int id) throws IOException {
        ArrayList<Transaction>AccountTransaction = TransactionData.findAccountTransaction(id);

        String accountReport="";

        for (Transaction transaction : AccountTransaction)
            accountReport += transaction.printDetails()+"\n";

        return accountReport;
    }

    public String generateTransactionReport() throws IOException {
        ArrayList<Transaction>  transactions = TransactionData.loadTransactions();
        String transactionReport="";
        for (Transaction transaction : transactions)
            transactionReport += transaction.printDetails()+"\n";

        return transactionReport;
    }

}

