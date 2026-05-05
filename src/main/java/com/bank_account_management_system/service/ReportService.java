package com.bank_account_management_system.service;

import com.bank_account_management_system.app.MainApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportService{
    static public void changeScene(String toPage, ActionEvent event) throws IOException {
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
    public static void showBarChart(BarChart reportBarChart, PieChart reportPieChart, TextArea reportArea) {
        // 1. Toggle visibility and layout management
        reportBarChart.setVisible(true);
        reportBarChart.setManaged(true);
        reportPieChart.setVisible(false);
        reportPieChart.setManaged(false);

        // 2. Clear previous data to prevent overlapping
        reportBarChart.getData().clear();

        // 3. Create a series and add data using standard JavaFX objects
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Number of Transactions");

        series.getData().add(new XYChart.Data<>("1001", 24));
        series.getData().add(new XYChart.Data<>("1002", 38));
        series.getData().add(new XYChart.Data<>("1003", 15));
        series.getData().add(new XYChart.Data<>("1004", 45));
        series.getData().add(new XYChart.Data<>("1005", 28));

        // 4. Add the series to the chart. JavaFX will use default colors automatically.
        reportBarChart.getData().add(series);

        reportArea.setText("Report Generated.");
    }

    public static void showPieChart(BarChart reportBarChart, PieChart reportPieChart, TextArea reportArea) {
        reportPieChart.setVisible(true);
        reportPieChart.setManaged(true);
        reportBarChart.setVisible(false);
        reportBarChart.setManaged(false);

        reportPieChart.getData().clear();

        // PieChart.Data objects will use the default color cycle
        reportPieChart.getData().add(new PieChart.Data("Checking", 60));
        reportPieChart.getData().add(new PieChart.Data("Savings", 40));

        reportArea.setText("Distribution Generated.");
    }


}