package com.bank_account_management_system.controller;

import com.bank_account_management_system.service.AccountService;
import com.bank_account_management_system.service.AccountType;
import com.bank_account_management_system.service.ReportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

public class TransferController {

    @FXML private TextField fromAccountId;
    @FXML private TextField toAccountId;
    @FXML private TextField amountField;
    @FXML private ComboBox<AccountType> toAccountTypeBox;

    @FXML
    public void initialize() {
        // Fill the dropdown with CHECKING, SAVINGS, etc.
        toAccountTypeBox.getItems().setAll(AccountType.values());

    }

    @FXML
    public void handleTransfer(ActionEvent event) {
        try {
            int fromId = Integer.parseInt(fromAccountId.getText());
            int toId = Integer.parseInt(toAccountId.getText());
            double amount = Double.parseDouble(amountField.getText());
            AccountType targetType = toAccountTypeBox.getValue();

            if (targetType == null) {
                System.out.println("Please select the recipient's account type.");
                return;
            }

            // Call the transfer method in AccountService
            boolean success = AccountService.transfer(fromId, toId, targetType, amount);

            if (success) {
                System.out.println("Transfer Successful!");

                // REFRESH the dashboard automatically using your new static instance
                if (DashboardController.instance != null) {
                    DashboardController.instance.loadAccountData();
                }

                handleCancel(event); // Close the popup
            } else {
                System.out.println("Transfer Failed: Check balance or IDs.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers for IDs and Amount.");
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}