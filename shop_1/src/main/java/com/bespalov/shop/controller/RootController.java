package com.bespalov.shop.controller;

import com.bespalov.shop.client.Client;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.Paths;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.pane.NewShopPane;
import com.bespalov.shop.pane.StatisticPane;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class RootController {
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem statistic;
    @FXML
    private MenuItem about;
    @FXML
    private MenuItem save;
    @FXML
    private Menu file;
    @FXML
    private Menu help;
    @FXML
    private MenuItem newShop;


    private StatisticPane statisticPane;
    private NewShopPane newShopPane;
    private Stage stage;
    private Paths paths;
    private Client client;
    private final String host = Inet4Address.getLocalHost().getHostAddress();
    private final int port = 9000;

    public RootController() throws UnknownHostException {
    }

    @FXML
    private void initialize() {
        save.setText(Languages.getResourceBundle().getString("save"));
        close.setText(Languages.getResourceBundle().getString("menuItem.close"));
        statistic.setText(Languages.getResourceBundle().getString("menuItem.statistic"));
        about.setText(Languages.getResourceBundle().getString("menuItem.about"));
        file.setText(Languages.getResourceBundle().getString("file"));
        help.setText(Languages.getResourceBundle().getString("help"));
        newShop.setText(Languages.getResourceBundle().getString("newShop"));
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        paths = new Paths(stage);
        statisticPane = new StatisticPane(stage);
        newShopPane = new NewShopPane(stage);
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

    @FXML
    public void handleNewShop() throws IOException {
        newShopPane.initNewShopPane();
    }
}
