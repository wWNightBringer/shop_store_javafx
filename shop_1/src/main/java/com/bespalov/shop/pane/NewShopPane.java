package com.bespalov.shop.pane;

import com.bespalov.shop.controller.NewShopController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NewShopPane {
    private Stage stage;

    public NewShopPane(Stage stage) {
        this.stage = stage;
    }

    public void initNewShopPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/NewShopOverview.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.setTitle("New shop");
        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);

        NewShopController controller = loader.getController();
        controller.setStage(dialogStage);
        dialogStage.showAndWait();
    }
}
