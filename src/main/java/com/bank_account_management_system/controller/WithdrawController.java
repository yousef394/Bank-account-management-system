package com.bank_account_management_system.controller;

import com.bank_account_management_system.model.BankAccount;
import com.bank_account_management_system.model.CheckingAccount;
import com.bank_account_management_system.service.AccountService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class WithdrawController {
    public Label errorLabel;
    @FXML private TextField idField;
    @FXML private TextField amountField;

    @FXML
    public void initialize() {
        // Feed data to the ComboBox as requested
        AccountService.applySanitizer(errorLabel, idField, amountField);

    }


    // THE AUTOMATIC PART: Called by ReportService
    public void initData(BankAccount account) {
        if (account != null) {
            idField.setText(String.valueOf(account.getAccountId()));
        }
    }

    @FXML
    public void handleWithdraw(ActionEvent event) {
        try {
            AccountService.validatePresence(errorLabel, idField,amountField);
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(amountField.getText());
            // Calls your Checking-specific logic
            if (AccountService.withdraw(id, amount)) {
                DashboardController.instance.loadAccountData();
                handleCancel(event);
            }
            BankAccount acc = AccountService.find(id);
            if (!(acc instanceof CheckingAccount)){
                System.out.println("can't withdraw from a non-checking account");
                errorLabel.setText("can't withdraw from a non-checking account");

            }
            if (acc == null){
                System.out.println("id not found");
                errorLabel.setText("id not found");
            }
            if(amount <.01){
                System.out.println("can't withdraw less than .01");
                errorLabel.setText("can't withdraw less than .01");
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers for IDs, Amount... etc");
            errorLabel.setText("Error: Please enter valid numbers for IDs, Amount... etc");
        }

        catch (Exception e) {
            System.out.println("Withdraw error!");
            errorLabel.setText("Withdraw error!");
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
}