package com.bank_account_management_system.controller;
import com.bank_account_management_system.model.*;
import com.bank_account_management_system.service.AccountService;
import com.bank_account_management_system.service.ReportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class AddAccountController {
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> accountTypeBox;
    @FXML
    private TextField balanceField;
    @FXML
    private VBox dynamicFields;
    // Store dynamically created fields to read them during creation
    private Map<String, TextField> activeFields = new HashMap<>();

    @FXML
    public void initialize() {
        // Feed data to the ComboBox as requested
        accountTypeBox.getItems().addAll("Checking Account", "Savings Account", "Home Loan", "Car Loan");

    }

    @FXML
    public void handleCreateAccount(ActionEvent actionEvent) {
        try {
            // 1. DATA VALIDATION
            if (accountTypeBox.getValue() == null || nameField.getText().isEmpty() ||
                    passwordField.getText().isEmpty() || balanceField.getText().isEmpty()) {
                System.out.println("Error: Please fill in all basic fields.");
                return;
            }

            // 2. GENERATE THE ID (Replacing the '0')
            int newId = AccountService.getNextId();

            // 3. EXTRACT SHARED DATA
            String name = nameField.getText();
            String password = passwordField.getText();
            double balance = Double.parseDouble(balanceField.getText());
            String selectedType = accountTypeBox.getValue();

            BankAccount newAccount = null;

            // 4. OBJECT CREATION BASED ON TYPE
            switch (selectedType) {
                case "Checking Account":
                    double overdraft = Double.parseDouble(activeFields.get("overdraftLimit").getText());
                    // Matches constructor: (id, password, name, balance, overdraft)
                    newAccount = new CheckingAccount(newId, password, name, balance, overdraft);
                    break;

                case "Savings Account":
                    double rate = Double.parseDouble(activeFields.get("interestRate").getText());
                    // Matches constructor: (id, password, name, balance, interestRate)
                    newAccount = new SavingsAccount(newId, password, name, balance, rate);
                    break;

                case "Home Loan":
                    double homeAmt = Double.parseDouble(activeFields.get("loanAmount").getText());
                    double homeRem = Double.parseDouble(activeFields.get("remainingAmount").getText());
                    String address = activeFields.get("propertyAddress").getText();
                    // Matches constructor: (id, password, name, balance, loanAmt, remainAmt, address)
                    newAccount = new HomeLoan(newId, password, name, balance, homeAmt, homeRem, address);
                    break;

                case "Car Loan":
                    double carAmt = Double.parseDouble(activeFields.get("loanAmount").getText());
                    double carRem = Double.parseDouble(activeFields.get("remainingAmount").getText());
                    String model = activeFields.get("carModel").getText();
                    // Matches constructor: (id, password, name, balance, loanAmt, remainAmt, model)
                    newAccount = new CarLoan(newId, password, name, balance, carAmt, carRem, model);
                    break;
            }

            // 5. SAVE AND CLOSE
            if (newAccount != null) {
                boolean success = AccountService.createAccount(newAccount);
                if (success) {
                    System.out.println("Created Account With Id: " + newId);

                    if (DashboardController.instance != null) {
                        DashboardController.instance.loadAccountData();
                    }
                    handleCancel(actionEvent); // Closes the popup
                } else {
                    System.out.println("Failed to save the account to the database.");
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Input Error: Please ensure Balance, Rates, and Limits are numbers.");
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void handleCancel(ActionEvent actionEvent) {
        ReportService.closePopup(actionEvent);
    }

    public void handleTypeChange(ActionEvent actionEvent) {
        // 1. Clear the old fields
        dynamicFields.getChildren().clear();
        activeFields.clear();

        String selectedType = accountTypeBox.getValue();
        if (selectedType == null) return;

        // 2. Inject fields based on Concrete Classes
        switch (selectedType) {
            case "Checking Account":
                addField("overdraftLimit", "Overdraft Limit"); //
                break;
            case "Savings Account":
                addField("interestRate", "Interest Rate (e.g. 0.05)"); //
                break;
            case "Home Loan":
                addField("loanAmount", "Total Loan Amount");
                addField("remainingAmount", "Remaining Balance");
                addField("propertyAddress", "Property Address"); //
                break;
            case "Car Loan":
                addField("loanAmount", "Total Loan Amount");
                addField("remainingAmount", "Remaining Balance");
                addField("carModel", "Car Model"); //
                break;
        }
    }
    private void addField(String id, String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setStyle("-fx-background-radius:8;");
        dynamicFields.getChildren().add(tf);
        activeFields.put(id, tf); // Save reference to read later
    }
}
