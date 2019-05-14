package com.bespalov.shop.controller;

import com.bespalov.shop.calendar.CalendatInit;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private ChoiceBox<String> condition;
    @FXML
    private ChoiceBox<String> shopList;
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
    private Label shopListName;
    @FXML
    private Label yearName;
    @FXML
    private Label monthName;
    @FXML
    private Label dayName;
    private Stage dialogStage;
    private Product product;
    private Shop shop;
    private boolean okClick = false;
    private CalendatInit calendatInit;
    private Logger logger = LoggerFactory.getLogger(ProductUpdateDialogController.class);

    public ProductUpdateDialogController() {
        calendatInit = new CalendatInit();
    }

    @FXML
    public void initialize() {
        year.setItems(calendatInit.getListYear());
        month.setItems(calendatInit.getListMonth());
        day.setItems(calendatInit.getListDay());
        condition.setItems(calendatInit.getCondition());
        shopList.setItems(ProductData.getAddressList());

        monthName.setText(Languages.getResourceBundle().getString("month"));
        yearName.setText(Languages.getResourceBundle().getString("year"));
        dayName.setText(Languages.getResourceBundle().getString("day"));

        titleName.setText(Languages.getResourceBundle().getString("title"));
        incomingDateName.setText(Languages.getResourceBundle().getString("incomingDate"));
        serialNumberName.setText(Languages.getResourceBundle().getString("serialNumber"));
        countName.setText(Languages.getResourceBundle().getString("count"));
        conditionName.setText(Languages.getResourceBundle().getString("condition"));
        shopListName.setText(Languages.getResourceBundle().getString("address"));
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product) throws NoSuchMethodException {
        this.product = product;
        logger.info(String.format("== %s ==", getClass().getName()));
        logger.info(String.format("Request param(Method name %s, values: %s)", getClass().getMethod("setProduct", Product.class).getName(), product));
        if (product.getShopId() == 0) {
            condition.setValue("EMPTY");
            shopList.setValue(ProductData.getAddressList().get(0));
        } else {
            shopList.setValue(ProductData.getShopList().
                    stream().filter(v -> (v.getShopId() == product.getShopId())).findFirst().get().getAddress());
            condition.setValue(product.getCondition());
        }
        day.setValue(product.getIncomingDate().getDayOfMonth());
        month.setValue(product.getIncomingDate().getMonth().toString());
        year.setValue(product.getIncomingDate().getYear());
        title.setText(product.getTitle());
        serialNumber.setText(product.getSerialNumber());
        count.setText(product.getCount());

    }

    public boolean isOkClick() {
        return okClick;
    }

    @FXML
    public void isOk() throws NoSuchMethodException {
        if (validIsRight()) {
            product.setTitle(title.getText());
            product.setIncomingDate(LocalDate.of(year.getValue(), month.getSelectionModel().getSelectedIndex() + 1, day.getValue()));
            product.setSerialNumber(serialNumber.getText());
            product.setCount(count.getText());
            product.setCondition(condition.getValue());
            product.setShopId(ProductData.getShopList().stream().filter(v -> (v.getAddress().equalsIgnoreCase(shopList.getValue()))).findFirst().get().getShopId());
            okClick = true;
            logger.info(String.format("Response param(Method name %s, values: %s)", getClass().getMethod("isOk"), product));
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
        if (count.getText() == null || count.getText().length() == 0 || Integer.parseInt(count.getText()) < 0)
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
