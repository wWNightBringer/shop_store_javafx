package com.bespalov.shop;

import com.bespalov.shop.model.Product;
import com.bespalov.shop.pane.MainPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClass extends Application {
    private Stage primaryStage;
    private BorderPane borderPane;
    private MainPane mainPane;

    public MainClass() {
        mainPane = new MainPane(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Shop");

        initStagePane();
        mainPane.initMainPane(borderPane);

        primaryStage.show();
    }

    private void initStagePane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
        borderPane = loader.load();

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
