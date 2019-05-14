package com.bespalov.shop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.util.Locale;

public class SettingController {
    @FXML
    private CheckBox english;
    @FXML
    private CheckBox russian;
    @FXML
    private CheckBox ukrainean;
    @FXML
    private ChoiceBox<String> screenSize;
    @FXML
    private CheckBox fullscreen;

    private ObservableList<String> observableList;
    private Locale locale;
    private boolean flag = false;
    private Stage dialogStage;

    @FXML
    private void initialize() {
        observableList = FXCollections.observableArrayList("800*500", "1000*600", "1200*700", "1400*800");
        english.setSelected(false);
        russian.setSelected(false);
        ukrainean.setSelected(false);
        screenSize.setItems(observableList);
        screenSize.setValue(observableList.get(0));
        fullscreen.setSelected(false);
        initLanguage();
    }

    @FXML
    private boolean handleOk() {
        if (fullscreen.isSelected()) {
            flag = true;
        }
        if ((english.isSelected() && russian.isSelected()) || (english.isSelected() && ukrainean.isSelected()) || (russian.isSelected() && ukrainean.isSelected())) {
            dialogStage.close();
            return false;
        }
        if (english.isSelected()) {
            locale = Locale.US;
            Locale.setDefault(locale);
            dialogStage.close();
            return true;
        }
        if (russian.isSelected()) {
            locale = new Locale("ru", "RU");
            Locale.setDefault(locale);
            dialogStage.close();
            return true;
        }
        if (ukrainean.isSelected()) {
            locale = new Locale("uk", "UA");
            Locale.setDefault(locale);
            dialogStage.close();
            return true;
        }
        return true;
    }

    @FXML
    private void handleClose() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Locale getLocale() {
        return locale;
    }

    public boolean isFlag() {
        return flag;
    }

    public double[] getScreenSizeResult() {
        double[] doubles;
        doubles = new double[2];
        switch (screenSize.getValue()) {
            case "800*500":
                doubles[0] = 800.0;
                doubles[1] = 500.0;
            case "1000*600":
                doubles[0] = 1000.0;
                doubles[1] = 600.0;
            case "1200*700":
                doubles[0] = 1200.0;
                doubles[1] = 700.0;
            case "1400*800":
                doubles[0] = 1400.0;
                doubles[1] = 800.0;
            default:
                doubles[0] = 998.0;
                doubles[1] = 626.0;

        }
        return doubles;
    }

    private void initLanguage() {
        Locale localeDefault = Locale.getDefault();
        switch (localeDefault.getLanguage()) {
            case "en":
                english.setSelected(true);
            case "ru":
                russian.setSelected(true);
            case "uk":
                ukrainean.setSelected(true);
            default:
        }
    }

}
