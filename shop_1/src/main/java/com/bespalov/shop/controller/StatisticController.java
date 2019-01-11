package com.bespalov.shop.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class StatisticController {
    @FXML
    private BarChart<String, Integer> barChart;

    private Stage stage;
    private final String[] shops = {"ATB", "VARUS", "FOXTROT", "Comfy", "Multiplex"};
    private final Integer[] amountProducts = {5, 7, 3, 10, 2};

    @FXML
    private void initialize() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBarChart(/*ProductData productData*/) {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Shops");
        for (int i = 0; i < shops.length; i++) {
            series.getData().add(new XYChart.Data<>(shops[i], amountProducts[i]));
        }
        barChart.getData().add(series);
    }
}
