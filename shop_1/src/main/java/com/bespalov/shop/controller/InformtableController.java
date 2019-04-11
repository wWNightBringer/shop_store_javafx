package com.bespalov.shop.controller;

import com.bespalov.shop.config.Connect;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class InformtableController {
    @FXML
    private Label title;
    @FXML
    private Label incomingDate;
    @FXML
    private Label serialNumber;
    @FXML
    private Label count;
    @FXML
    private Label condition;
    @FXML
    private Label titleName;
    @FXML
    private Label incomingDateName;
    @FXML
    private Label serialNumberName;
    @FXML
    private Label countName;
    @FXML
    private Label conditionName;
    @FXML
    private Label titleShop;
    @FXML
    private Label address;
    @FXML
    private Label titleShopName;
    @FXML
    private Label addressName;

    private Stage dialogStage;
    private Product product;
    private Shop shop;


    @FXML
    public void initialize() {
        titleName.setText(Languages.getResourceBundle().getString("title"));
        incomingDateName.setText(Languages.getResourceBundle().getString("incomingDate"));
        serialNumberName.setText(Languages.getResourceBundle().getString("serialNumber"));
        countName.setText(Languages.getResourceBundle().getString("count"));
        conditionName.setText(Languages.getResourceBundle().getString("condition"));
        titleShopName.setText(Languages.getResourceBundle().getString("titleShop"));
        addressName.setText(Languages.getResourceBundle().getString("address"));
    }

    @FXML
    public void isOkPressed(KeyEvent event) {
        if (KeyCode.ENTER == event.getCode() || KeyCode.ESCAPE == event.getCode()) {
            dialogStage.close();
        }
    }

    @FXML
    public void isCancelPressed(KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {
            dialogStage.close();
        }
    }

    @FXML
    public void isOk() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;
        title.setText(product.getTitle());
        incomingDate.setText(product.getIncomingDate().toString());
        serialNumber.setText(product.getSerialNumber());
        count.setText(product.getCount());
        condition.setText(product.getCondition());
    }

    public void setShop(Shop shop) {
        this.shop = shop;
        titleShop.setText(shop.getTitleShop());
        address.setText(shop.getAddress());
    }
}
