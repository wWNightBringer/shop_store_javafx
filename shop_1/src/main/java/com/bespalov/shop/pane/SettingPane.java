package com.bespalov.shop.pane;

import com.bespalov.shop.controller.SettingController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class SettingPane {
    private Stage stage;
    private Locale locale;
    private boolean flag;
    private double[] screenSizeResult;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void showSettingDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/SettingOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.getIcons().add(new Image(new FileInputStream(java.nio.file.Paths.get("shop_1/src/main/resources/photo/images.png").toFile())));
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        SettingController settingController = loader.getController();
        settingController.setDialogStage(dialogStage);
        locale = settingController.getLocale();
        flag = settingController.isFlag();
        screenSizeResult = settingController.getScreenSizeResult();

        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();
    }

    public Locale getLocale() {
        return locale;
    }

    public boolean isFlag() {
        return flag;
    }

    public double[] getScreenSizeResult() {
        return screenSizeResult;
    }
}
