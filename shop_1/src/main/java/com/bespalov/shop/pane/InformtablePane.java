package com.bespalov.shop.pane;

import com.bespalov.shop.controller.InformtableController;
import com.bespalov.shop.model.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InformtablePane {
    private Stage stage;
    private Product product;

    public InformtablePane(Stage stage) {
        this.stage = stage;
    }

    public void showInformtable() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/ProductLabelOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.setTitle("Show information");
        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);

        InformtableController controller = loader.getController();
        controller.setDialogStage(stage);
        controller.setProduct(product);

        dialogStage.showAndWait();
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
