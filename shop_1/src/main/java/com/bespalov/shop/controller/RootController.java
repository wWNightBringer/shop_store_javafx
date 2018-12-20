package com.bespalov.shop.controller;

import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.config.Paths;
import com.bespalov.shop.config.ProductData;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

    private Stage stage;
    private Paths paths;


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
