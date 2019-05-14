package com.bespalov.shop;

import com.bespalov.shop.client.Client;
import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.Paths;
import com.bespalov.shop.controller.RootController;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.pane.MainPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Locale;

public class MainClass extends Application {
    private Stage primaryStage;
    private BorderPane borderPane;
    private MainPane mainPane;
    private Locale locale;
    private boolean flag;
    private double[] screenSizeResult;


    public MainClass() throws JAXBException, IOException {
        mainPane = new MainPane(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setTitle(Languages.getResourceBundle().getString("shop"));
        initStagePane();
        mainPane.initMainPane(borderPane);
        primaryStage.getIcons().add(new Image(
                new FileInputStream(java.nio.file.Paths.get("shop_1/src/main/resources/photo/images.png").toFile())));
        primaryStage.show();
        System.gc();

    }

    private void initStagePane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/RootLayout.fxml"));
        borderPane = loader.load();

        RootController controller = loader.getController();
        controller.setStage(primaryStage);
        Scene scene = new Scene(borderPane, 1000, 600);
        primaryStage.setFullScreen(flag);
        primaryStage.setScene(scene);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setScreenSizeResult(double[] screenSizeResult) {
        this.screenSizeResult = screenSizeResult;
    }
}
