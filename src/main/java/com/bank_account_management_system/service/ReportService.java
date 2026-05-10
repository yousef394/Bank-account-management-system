package com.bank_account_management_system.service;

import com.bank_account_management_system.Repository.TransactionRepository;
import com.bank_account_management_system.model.Transaction;


import java.util.ArrayList;

public class ReportService
{

    public static ArrayList<Transaction> generateAccountReport(int id)  {
        return TransactionRepository.accountTransactions(id);
    }

    public static ArrayList<Transaction> generateTransactionReport() {
        return TransactionRepository.allTransactions();
    }

}

