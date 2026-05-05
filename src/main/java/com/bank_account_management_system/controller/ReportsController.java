package com.bank_account_management_system.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ReportsController {
    @FXML private BarChart<String, Number> reportBarChart;
    @FXML private PieChart reportPieChart;
    @FXML private ComboBox<String> reportTypeBox;
    @FXML private TextArea reportArea;//    @FXML

    @FXML
    public void initialize() {
        // Feed data to the ComboBox as requested
        reportTypeBox.getItems().addAll("Bar Chart: Transactions", "Pie Chart: Distribution");

        // Hide charts initially or show a default
        reportBarChart.setVisible(true);
        reportPieChart.setVisible(false);
    }


    private void showBarChart() {
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

    private void showPieChart() {
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
    public void handleGenerateReport(ActionEvent actionEvent) {
        String selected = reportTypeBox.getValue();

        if (selected == null) {
            reportArea.setText("Please select a chart type!");
            return;
        }

        // The "UI IF Condition" logic
        if (selected.equals("Bar Chart: Transactions")) {
            showBarChart();
        } else if (selected.equals("Pie Chart: Distribution")) {
            showPieChart();
        }
    }
}
