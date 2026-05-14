package com.bank_account_management_system.controller;

import com.bank_account_management_system.service.AccountService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TransferController {

    @FXML private TextField fromAccountId;
    @FXML private TextField toAccountId;
    @FXML private TextField amountField;
    @FXML private Label errorLabel;


    @FXML
    public void initialize() {
        // Feed data to the ComboBox as requested
        AccountService.applySanitizer(errorLabel, fromAccountId, toAccountId, amountField);

    }
    @FXML
    public void handleTransfer(ActionEvent event) {
        try {
            AccountService.validatePresence(errorLabel, fromAccountId, toAccountId, amountField);
            int fromId = Integer.parseInt(fromAccountId.getText());
            int toId = Integer.parseInt(toAccountId.getText());
            double amount = Double.parseDouble(amountField.getText());
            if(amount <.01){
                System.out.println("can't transfer less than .01");
                errorLabel.setText("can't transfer less than .01");
            }
            // Call the transfer method in AccountService
            boolean success = AccountService.transfer(fromId, toId, amount);

            if (success) {
                System.out.println("Transfer Successful!");

                // REFRESH the dashboard automatically using your new static instance
                if (DashboardController.instance != null) {
                    DashboardController.instance.loadAccountData();
                }

                handleCancel(event); // Close the popup
            } else {
                System.out.println("Transfer Failed: Check balance or IDs.");
                errorLabel.setText("Transfer Failed: Check balance or IDs.");

            }

        }
        catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numbers for IDs, Amount.");
            errorLabel.setText("Error: Please enter valid numbers for IDs, Amount.");
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}