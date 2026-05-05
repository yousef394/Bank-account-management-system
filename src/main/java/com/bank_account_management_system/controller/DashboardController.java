package com.bank_account_management_system.controller;
import com.bank_account_management_system.app.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static com.bank_account_management_system.service.ReportService.*;

public class DashboardController {
    @FXML
    private Button logoutBtn;

    @FXML
    private Button depositBtn;

    @FXML
    private Button withdrawBtn;

    @FXML
    private Button transferBtn;

    @FXML
    private Button addAccountBtn;

    @FXML
    private Button removeAccountBtn;


    public void handleLogout(ActionEvent event) throws IOException {
        changeScene("login.fxml", event);
    }

    public void handleDeposit(ActionEvent actionEvent) {
    }

    public void handleWithdraw(ActionEvent actionEvent) {
    }

    public void handleTransfer(ActionEvent actionEvent) throws  IOException {
        openPopup("transfer.fxml");
    }

    public void handleAddAccount(ActionEvent actionEvent) {
    }

    public void handleDeleteAccount(ActionEvent actionEvent) {

    }
}
