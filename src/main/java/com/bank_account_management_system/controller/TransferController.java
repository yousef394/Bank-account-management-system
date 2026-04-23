package com.bank_account_management_system.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TransferController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
