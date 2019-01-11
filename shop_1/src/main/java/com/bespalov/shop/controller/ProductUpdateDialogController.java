package com.bespalov.shop.controller;

import com.bespalov.shop.calendar.CalendatInit;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

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
    private ChoiceBox<String> condition;
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
    private Label yearName;
    @FXML
    private Label monthName;
    @FXML
    private Label dayName;
    private Stage dialogStage;
    private Product product;
    private boolean okClick = false;
    private CalendatInit calendatInit;

    public ProductUpdateDialogController() {
        calendatInit = new CalendatInit();
    }

    @FXML
    public void initialize() {
        year.setItems(calendatInit.getListYear());
        month.setItems(calendatInit.getListMonth());
        day.setItems(calendatInit.getListDay());

        condition.setItems(calendatInit.getCondition());
        monthName.setText(Languages.getResourceBundle().getString("month"));
        yearName.setText(Languages.getResourceBundle().getString("year"));
        dayName.setText(Languages.getResourceBundle().getString("day"));
        titleName.setText(Languages.getResourceBundle().getString("title"));
        incomingDateName.setText(Languages.getResourceBundle().getString("incomingDate"));
        serialNumberName.setText(Languages.getResourceBundle().getString("serialNumber"));
        countName.setText(Languages.getResourceBundle().getString("count"));
        conditionName.setText(Languages.getResourceBundle().getString("condition"));
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) {
        this.product = product;
        day.setValue(product.getIncomingDate().getDayOfMonth());
        month.setValue(product.getIncomingDate().getMonth().toString());
        year.setValue(product.getIncomingDate().getYear());
        title.setText(product.getTitle());
        serialNumber.setText(product.getSerialNumber());
        count.setText(product.getCount());
        condition.setValue(product.getCondition());
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
            product.setCondition(condition.getValue());
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
        if (condition.getSelectionModel() == null)
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

}
