package com.bank_account_management_system.controller;

import com.bank_account_management_system.service.ReportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button btnLogin;

    public void handleLogin(ActionEvent event) throws IOException {

        ReportService.changeScene("dashboard.fxml", event);

    }
}
