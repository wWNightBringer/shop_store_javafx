package com.bespalov.shop.pane;

import com.bespalov.shop.config.Languages;
import com.bespalov.shop.controller.StatisticController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class StatisticPane {
    private Stage stage;

    public StatisticPane(Stage stage) {
        this.stage = stage;
    }

    public void showStatistic(/*ProductData productData*/) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/StatisticOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.setTitle(Languages.getResourceBundle().getString("menuItem.statistic"));
        dialogStage.initOwner(stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);

        StatisticController controller = loader.getController();
        controller.setStage(stage);
        controller.setBarChart();

        dialogStage.showAndWait();

    }
}
