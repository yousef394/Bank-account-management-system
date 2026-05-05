package com.bank_account_management_system.controller;

import com.bank_account_management_system.service.ReportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.imageio.plugins.tiff.BaselineTIFFTagSet;

public class TransferController {
    @FXML
    private Button cancelBtn;

    public void handleTransfer(ActionEvent actionEvent) {

    }

    public void handleCancel(ActionEvent actionEvent) {
        ReportService.closePopup(actionEvent);
    }
}
