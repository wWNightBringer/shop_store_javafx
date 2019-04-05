package com.bespalov.shop.controller;

import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.model.Product;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class StatisticController {
    @FXML
    private BarChart<String, Integer> barChart;

    @FXML
    private NumberAxis xAxis = new NumberAxis();
    @FXML
    private CategoryAxis yAxis = new CategoryAxis();
    private Stage stage;

    @FXML
    private void initialize() {

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setBarChart(List<Product> productDataList) throws FileNotFoundException {
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        series.setName("Products");
        for (Product product : productDataList) {
            series.getData().add(new XYChart.Data<>(product.getTitle(), Integer.parseInt(product.getCount())));
        }

        barChart.getData().add(series);
    }
}
