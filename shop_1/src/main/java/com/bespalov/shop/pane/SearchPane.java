package com.bespalov.shop.pane;

import com.bespalov.shop.config.Languages;
import com.bespalov.shop.controller.ProductUpdateDialogController;
import com.bespalov.shop.controller.SearchController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class SearchPane {
    private Stage stage;

    public SearchPane(Stage stage) {
        this.stage = stage;
    }

    public void showSearchStage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/SearchOverview.fxml"));
        AnchorPane anchorPane = loader.load();
        Stage dialogStage = new Stage();
        dialogStage.getIcons().add(new Image(
                new FileInputStream(java.nio.file.Paths.get("shop_1/src/main/resources/photo/images.png").toFile())));
        dialogStage.setTitle(Languages.getResourceBundle().getString("search"));
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(stage);
        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);

        SearchController controller = loader.getController();
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();

    }
}
