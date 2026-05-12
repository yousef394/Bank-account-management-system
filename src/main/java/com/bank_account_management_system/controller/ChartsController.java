package com.bank_account_management_system.controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ChartsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void handleTypeChange(ActionEvent actionEvent) {

    }

    public void handleCreateAccount(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {

    }
}
