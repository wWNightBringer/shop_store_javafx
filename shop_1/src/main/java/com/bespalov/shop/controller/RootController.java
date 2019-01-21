package com.bespalov.shop.controller;

import com.bespalov.shop.client.Client;
import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.Paths;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.pane.StatisticPane;
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
    private MenuItem refresh;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem statistic;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem back;
    @FXML
    private MenuItem save;
    @FXML
    private Menu file;
    @FXML
    private Menu help;

    private StatisticPane statisticPane;
    private Stage stage;
    private Paths paths;
    private Client client;
    private final String host = Languages.getResourceBundle().getString("host");
    private final int port = 9000;

    @FXML
    private void initialize() {
        //newItem.setText(Languages.getResourceBundle().getString("menuItem.new"));
        save.setText(Languages.getResourceBundle().getString("save"));
        back.setText(Languages.getResourceBundle().getString("back"));
        close.setText(Languages.getResourceBundle().getString("menuItem.close"));
        statistic.setText(Languages.getResourceBundle().getString("menuItem.statistic"));
        about.setText(Languages.getResourceBundle().getString("menuItem.about"));
        file.setText(Languages.getResourceBundle().getString("file"));
        help.setText(Languages.getResourceBundle().getString("help"));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        paths = new Paths(stage);
        statisticPane = new StatisticPane(stage);
    }

    @FXML
    private void handleBack() throws IOException, JAXBException, InterruptedException {
        client = new Client(host, port);
        client.actionToDatabase(null, "Back");
    }

    @FXML
    private void handleSave() throws InterruptedException, JAXBException, IOException {
        client = new Client(host, port);
        client.actionToDatabase(null, "Save");
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

    @FXML
    private void handleStatistic() throws IOException {
        statisticPane.showStatistic(ProductData.getProductList());
    }
}
