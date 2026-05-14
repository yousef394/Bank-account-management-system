package com.bank_account_management_system.service;

import com.bank_account_management_system.Repository.TransactionRepository;
import com.bank_account_management_system.model.Transaction;


import java.util.ArrayList;

public class ReportService
{

    private static final TransactionRepository TransactionRepo = new TransactionRepository();

    public static ArrayList<Transaction> generateAccountReport(int id)  {
        return TransactionRepo.getTransactionsByAccountId(id);
    }

    public static ArrayList<Transaction> generateTransactionReport() {
        return TransactionRepo.getAll();
    }

}

