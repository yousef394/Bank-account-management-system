package com.bank_account_management_system.controller;

import com.bank_account_management_system.controller.DashboardController;
import com.bank_account_management_system.model.BankAccount;
import com.bank_account_management_system.model.CarLoan;
import com.bank_account_management_system.model.CheckingAccount;
import com.bank_account_management_system.model.SavingsAccount;
import com.bank_account_management_system.service.AccountService;
import com.bank_account_management_system.service.AccountType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class DepositController {
    @FXML private TextField idField;
    @FXML private TextField amountField;
    @FXML private ComboBox<AccountType> typeBox;

    @FXML
    public void initialize() {
        typeBox.getItems().setAll(AccountType.values());
    }

    // THE AUTOMATIC PART: Called by ReportService
    public void initData(BankAccount account) {
        if (account != null) {
            idField.setText(String.valueOf(account.getAccountId()));
            // Detect the type from the object class and set the ComboBox
            if (account instanceof CheckingAccount) typeBox.setValue(AccountType.CHECKING);
            else if (account instanceof SavingsAccount) typeBox.setValue(AccountType.SAVINGS);
            else if (account instanceof CarLoan) typeBox.setValue(AccountType.CARLOAN);
            else typeBox.setValue(AccountType.HOMELOAN);
        }
    }

    @FXML
    public void handleDeposit(ActionEvent event) {
        try {
            int id = Integer.parseInt(idField.getText());
            double amount = Double.parseDouble(amountField.getText());
            AccountType type = typeBox.getValue();

            if (AccountService.deposit(id, amount, type)) {
                DashboardController.instance.loadAccountData(); // Refresh the table
                handleCancel(event);
            }
        } catch (Exception e) { System.out.println("Check inputs!"); }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

}