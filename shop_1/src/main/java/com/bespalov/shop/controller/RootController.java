package com.bespalov.shop.controller;

import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.Paths;
import com.bespalov.shop.config.ProductData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class RootController {
    @FXML
    private MenuItem newItem;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem statistic;
    @FXML
    private MenuItem about;
    @FXML
    private Menu file;
    @FXML
    private Menu help;


    private Stage stage;
    private Paths paths;

    @FXML
    private void initialize() {
        newItem.setText(Languages.getResourceBundle().getString("menuItem.new"));
        close.setText(Languages.getResourceBundle().getString("menuItem.close"));
        statistic.setText(Languages.getResourceBundle().getString("menuItem.statistic"));
        about.setText(Languages.getResourceBundle().getString("menuItem.about"));
        file.setText(Languages.getResourceBundle().getString("file"));
        help.setText(Languages.getResourceBundle().getString("help"));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        paths = new Paths(stage);
    }

    @FXML
    private void handleNew() {
        ProductData.getProductList().clear();
        paths.setPathFile(null);
    }

    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("App");
        alert.setHeaderText("About");
        alert.setContentText("Author Andrew, welcome");
        alert.showAndWait();
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void pressClose(KeyEvent keyEvent) {
        if (KeyCode.ESCAPE == keyEvent.getCode())
            System.exit(0);
    }
}
