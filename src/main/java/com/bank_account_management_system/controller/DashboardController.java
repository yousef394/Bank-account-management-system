package com.bank_account_management_system.controller;
import com.bank_account_management_system.app.MainApplication;
import com.bank_account_management_system.model.*;
import com.bank_account_management_system.service.AccountService;
import com.bank_account_management_system.service.AccountType;
import com.bank_account_management_system.service.ReportService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import static com.bank_account_management_system.service.ReportService.*;

public class DashboardController {
    public static DashboardController instance;

    @FXML private Label totalAccountsLabel;
    @FXML private Label totalBalanceLabel;
    @FXML private Label loanCountLabel;

    public void handleLogout(ActionEvent event) throws IOException {
        changeScene("login.fxml", event);
    }

    public void handleDeposit(ActionEvent actionEvent) {
        try {
            // Grab whatever row the user clicked on
            BankAccount selected = accountsTable.getSelectionModel().getSelectedItem();

            // Pass it to the service
            ReportService.openActionPopup("deposit.fxml", selected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleWithdraw(ActionEvent actionEvent) {
        try {
            BankAccount selected = accountsTable.getSelectionModel().getSelectedItem();
            ReportService.openActionPopup("withdraw.fxml", selected);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleTransfer(ActionEvent actionEvent) throws  IOException {
        openPopup("transfer.fxml");
    }

    public void handleAddAccount(ActionEvent actionEvent)throws  IOException  {
        openPopup("Add_Account_Screen.fxml");
    }

    public void handleDeleteAccount(ActionEvent actionEvent) {
        // 1. Get the selected account from the TableView
        BankAccount selectedAccount = accountsTable.getSelectionModel().getSelectedItem();

        if (selectedAccount == null) {
            System.out.println("Please select an account from the table first.");
            return;
        }

        // 2. Identify the AccountType (needed for the service switch)
        AccountType type;
        if (selectedAccount instanceof CheckingAccount) type = AccountType.CHECKING;
        else if (selectedAccount instanceof SavingsAccount) type = AccountType.SAVINGS;
        else if (selectedAccount instanceof CarLoan) type = AccountType.CARLOAN;
        else type = AccountType.HOMELOAN;



        // 3. Call the service to remove it from the text files
        boolean success = AccountService.delete(selectedAccount.getAccountId(), type);

        if (success) {
            System.out.println("Account deleted successfully.");
            // 4. Refresh the UI using the same logic as Add/Transfer
            loadAccountData();
        } else {
            System.out.println("Error: Could not delete the account.");
        }
    }
    @FXML private TableView<BankAccount> accountsTable;
    @FXML private TableColumn<BankAccount, Integer> idColumn;
    @FXML private TableColumn<BankAccount, String> nameColumn;
    @FXML private TableColumn<BankAccount, Double> balanceColumn;
    @FXML private TableColumn<BankAccount, String> typeColumn;

    @FXML
    public void initialize() {
        instance = this;
        loadAccountData();
        // 1. Link columns to BankAccount properties
        // These strings MUST match the getter names (e.g., "accountId" matches "getAccountId()")
        idColumn.setCellValueFactory(new PropertyValueFactory<>("accountId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("holderName"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));

        // For the "Type" column, since it's not a simple variable,
        // we can use a custom CellValueFactory
        typeColumn.setCellValueFactory(cellData -> {
            String className = cellData.getValue().getClass().getSimpleName();
            return new SimpleStringProperty(className);
        });
        // 2. Load the data
        loadAccountData();
    }

    public void loadAccountData() {
// 1. Get the fresh list from the files
        ArrayList<BankAccount> allAccounts = AccountService.loadAccounts();

        // 2. Update the Table
        accountsTable.getItems().setAll(allAccounts);

        // 3. CALCULATE TOTALS
        int accountCount = allAccounts.size();
        double totalBalance = 0;
        int loanCount = 0;

        for (BankAccount acc : allAccounts) {
            totalBalance += acc.getBalance();

            // Check if the account is a loan (Car or Home)
            if (acc instanceof CarLoan || acc instanceof HomeLoan) {
                loanCount++;
            }
        }

        // 4. DISPLAY IN UI
        totalAccountsLabel.setText(String.valueOf(accountCount));
        totalBalanceLabel.setText(String.format("$%.2f", totalBalance));
        loanCountLabel.setText(String.valueOf(loanCount));

    }

    public void handleReports(ActionEvent event) {
        try {
            // Use the existing utility method in ReportService to swap views
            ReportService.changeScene("reports.fxml", event);
        } catch (IOException e) {
            System.out.println("Error loading reports.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

