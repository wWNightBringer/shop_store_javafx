package com.bespalov.shop.controller;

import com.bespalov.shop.config.Connect;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;
import com.bespalov.shop.pane.InformtablePane;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SearchController {
    @FXML
    private TextField title;
    @FXML
    private Label titleLabel;


    private InformtablePane informtablePane;
    private Stage dialogStage;
    private Product product;
    private boolean isOk = false;
    private Logger logger = LoggerFactory.getLogger(SearchController.class);
    private com.shop.spring_shop_store.model.Shop shop;
    private Connect connect;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        informtablePane = new InformtablePane(dialogStage);
        titleLabel.setText(Languages.getResourceBundle().getString("enter_title"));
    }

    @FXML
    public void isOK(javafx.scene.input.KeyEvent event) throws IOException, NoSuchMethodException {
        if (KeyCode.ENTER == event.getCode()) {
            shop = new com.shop.spring_shop_store.model.Shop();
            if (validData()) {
                logger.info(String.format("== %s ==", getClass().getName()));
                for (Product p : ProductData.getProductList()) {
                    if (p.getTitle().equalsIgnoreCase(title.getText())) {
                        logger.info(String.format("== %s ==", getClass().getName()));
                        logger.info(String.format("Request param(Method name %s, values: %s)", getClass().getMethod("isOK", javafx.scene.input.KeyEvent.class).getName(), p));
                        shop.setIdShop(p.getShopId());
                        connect = new Connect("getShopById");
                        ObjectMapper objectMapper = new ObjectMapper();
                        shop = objectMapper.readValue(connect.inputStream(shop), com.shop.spring_shop_store.model.Shop.class);
                        informtablePane = new InformtablePane(dialogStage);
                        informtablePane.setProduct(p);
                        informtablePane.setShop(new Shop(shop.getIdShop(), shop.getAddress(), shop.getTitle()));
                        informtablePane.showInformtable();
                        isOk = true;
                        break;
                    }
                }
                dialogStage.close();
            }
        }

    }

    @FXML
    public void isCancel(javafx.scene.input.KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            dialogStage.close();
        }

    }

    private boolean validData() {
        String error = "";
        long count = ProductData.getProductList().stream().filter(v -> (v.getTitle().equalsIgnoreCase(title.getText()))).count();
        if (count == 0 || (title.getText() == null || title.getText().length() == 0))
            error += "Invalid title";
        if (error.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initModality(Modality.WINDOW_MODAL);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid title");
            alert.setHeaderText("Enter correct title");
            alert.setContentText(error);
            alert.showAndWait();
        }
        return false;
    }

    public Product getProduct() {
        return product;
    }

}
