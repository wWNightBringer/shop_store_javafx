package com.bespalov.shop.controller;

import com.bespalov.shop.config.Connect;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.model.Product;
import com.shop.spring_shop_store.model.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class NewShopController {
    @FXML
    private Label address;
    @FXML
    private Label titleShop;
    @FXML
    private TextField addressField;
    @FXML
    private TextField titleShopField;
    @FXML
    private Button buttonOk;

    private Boolean flag = true;
    private Stage dialogStage;
    private String error;
    private Connect connect;
    private Logger logger = LoggerFactory.getLogger(NewShopController.class);

    public NewShopController() {
    }

    @FXML
    public void initialize() {
        address.setText(Languages.getResourceBundle().getString("address"));
        titleShop.setText(Languages.getResourceBundle().getString("titleShop"));
    }

    public void setStage(Stage stage) {
        this.dialogStage = stage;
    }

    @FXML
    public void pressOk() throws IOException, NoSuchMethodException {
        logger.info(String.format("== %s ==", getClass().getName()));
        if (validIsRightData()) {
            connect = new Connect("addNewShop");
            Shop shop = new Shop();
            shop.setAddress(addressField.getText());
            shop.setTitle(titleShopField.getText());
            logger.info(String.format("Response param(Method name %s, values: %s)", getClass().getMethod("pressOk").getName(), shop));
            connect.inputStream(shop);
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(dialogStage);
            alert.setTitle("Shop window");
            alert.setHeaderText("Invalid data");
            alert.setContentText(error);
            alert.showAndWait();
        }
    }

    @FXML
    public void close(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE)
            dialogStage.close();
    }

    private boolean validIsRightData() {
        error = "";
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            error += "Invalid address\n";
            flag = false;
        }
        if (titleShopField.getText() == null || titleShopField.getText().length() == 0) {
            error += "Invalid title";
            flag = false;
        }
        return flag;
    }
}
