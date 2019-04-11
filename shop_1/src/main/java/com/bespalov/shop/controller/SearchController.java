package com.bespalov.shop.controller;

import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.pane.InformtablePane;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        informtablePane = new InformtablePane(dialogStage);
        titleLabel.setText(Languages.getResourceBundle().getString("enter_title"));
    }

    @FXML
    private void isOK(javafx.scene.input.KeyEvent event) throws IOException {
        if (KeyCode.ENTER == event.getCode()) {
            if (validData()) {
                for (Product p : ProductData.getProductList()) {
                    if (p.getTitle().equalsIgnoreCase(title.getText())) {
                        product = p;
                        informtablePane.setProduct(product);
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
