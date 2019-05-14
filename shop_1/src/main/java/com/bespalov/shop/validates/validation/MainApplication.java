package com.bespalov.shop.validates.validation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage = primaryStage;
        initValidation();
        stage.show();
    }

    private void initValidation() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ValidatorOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        ValidatorController validatorController = loader.getController();
        validatorController.setStage(stage);

        stage.getIcons().add(new Image(new FileInputStream(java.nio.file.Paths.get("shop_1/src/main/resources/photo/images.png").toFile())));
        stage.setResizable(false);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);

    }
}
