package com.bespalov.shop.pane;

import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.controller.StatisticController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


public class StatisticPane {
    private Stage stage;

    public StatisticPane(Stage stage) {
        this.stage = stage;
    }

    public void showStatistic(List productData) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/StatisticOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.getIcons().add(new Image(
                new FileInputStream(java.nio.file.Paths.get("shop_1/src/main/resources/photo/images.png").toFile())));
        dialogStage.setResizable(false);
        dialogStage.setTitle(Languages.getResourceBundle().getString("menuItem.statistic"));
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);

        StatisticController controller = loader.getController();
        controller.setStage(stage);
        controller.setBarChart(productData);

        dialogStage.showAndWait();

    }
}
