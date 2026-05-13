package com.bank_account_management_system.controller;

import com.bank_account_management_system.controller.DashboardController;
import com.bank_account_management_system.model.BankAccount;
import com.bank_account_management_system.service.AccountService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawController {
    @FXML private TextField idField;
    @FXML private TextField amountField;

    // THE AUTOMATIC PART: Called by ReportService
    public void initData(BankAccount account) {
        if (account != null) {
            idField.setText(String.valueOf(account.getAccountId()));
        }
    }

    @FXML
    public void handleWithdraw(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(amountField.getText());

            // Calls your Checking-specific logic
            if (AccountService.withdrawFromCheckingAccount(id, amount)) {
                DashboardController.instance.loadAccountData();
                handleCancel(event);
            }
        } catch (Exception e) { System.out.println("Withdraw error!"); }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}