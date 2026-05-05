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


    static public void changeScene(String toPage, ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/bank_account_management_system/view/"+toPage));
        Parent root = loader.load();

        // Get the current Stage from the button click
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    static public void openPopup(String toPage) throws IOException {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/com/bank_account_management_system/view/"+toPage));
        Parent root = loader.load();

        Stage popupStage = new Stage();
        popupStage.setTitle("Open New Account");

        // Make it 'Modal' (blocks the main window)
        popupStage.initModality(Modality.APPLICATION_MODAL);

        popupStage.setScene(new Scene(root));
        popupStage.show();
    }

    @FXML
    static public void closePopup(ActionEvent event) {
        // Get the stage from the 'Cancel' button and close it
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

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
