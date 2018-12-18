package com.bespalov.shop.controller;

import com.bespalov.shop.calendar.CalendatInit;
import com.bespalov.shop.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ProductUpdateDialogController {
    @FXML
    private TextField title;
    @FXML
    private ChoiceBox<Integer> year;
    @FXML
    private ChoiceBox<String> month;
    @FXML
    private ChoiceBox<Integer> day;
    @FXML
    private TextField serialNumber;
    @FXML
    private TextField count;
    @FXML
    private TextField condition;


    private Stage dialogStage;
    private Product product;
    private boolean okClick = false;
    private CalendatInit calendatInit;

    public ProductUpdateDialogController() {
        calendatInit = new CalendatInit();
    }

    @FXML
    public void initialize() {
        year.setValue(2000);
        year.setItems(calendatInit.getListYear());
        month.setValue("January");
        month.setItems(calendatInit.getListMonth());
        day.setValue(1);
        day.setItems(calendatInit.getListDay());
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;
        title.setText(product.getTitle());
        serialNumber.setText(product.getSerialNumber());
        count.setText(product.getCount());
        condition.setText(product.getCondition());
    }

    public boolean isOkClick() {
        return okClick;
    }

    @FXML
    public void isOk() {
        if (validIsRight()) {
            product.setTitle(title.getText());
            product.setIncomingDate(LocalDate.of(year.getValue(), month.getSelectionModel().getSelectedIndex(), day.getValue()));
            product.setSerialNumber(serialNumber.getText());
            product.setCount(count.getText());
            product.setCondition(condition.getText());
            okClick = true;
            dialogStage.close();
        }
    }

    @FXML
    public void isCancel() {
        dialogStage.close();
    }

    private boolean validIsRight() {
        String errorMsg = "";
        if (title.getText() == null || title.getText().length() == 0)
            errorMsg += "Invalid title";
        if (serialNumber.getText() == null || serialNumber.getText().length() == 0)
            errorMsg += "Invalid serial number";
        if (count.getText() == null || count.getText().length() == 0)
            errorMsg += "Invalid count";
        if (condition.getText() == null || condition.getText().length() == 0 || !isCondition())
            errorMsg += "Invalid condition";
        if (errorMsg.length() == 0)
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid date");
            alert.setContentText("Enter correct data");
            alert.setContentText(errorMsg);
            alert.showAndWait();
            return false;
        }
    }

    enum Condition {
        Has, Progress, Empty
    }

    private boolean isCondition() {
        for (Condition s : Condition.values()) {
            if (condition.getText().equalsIgnoreCase(s.name())) {
                return true;
            }

        }
        return false;
    }
}
