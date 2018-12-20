package com.bespalov.shop.controller;

import com.bespalov.shop.model.Product;
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

    private Stage dialogStage;
    private Product product;

    @FXML
    public void initialize() {
        System.out.println(product);

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
}
