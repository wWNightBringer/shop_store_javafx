package com.bespalov.shop.pane;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.controller.ProductOverviewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPane {
    private Stage stage;

    public MainPane(Stage stage) {
        this.stage = stage;
    }

    public void initMainPane(BorderPane borderPane) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ShopOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        borderPane.setCenter(anchorPane);
        ProductOverviewController controller = loader.getController();
        controller.setMainApp(stage);



    }
}
