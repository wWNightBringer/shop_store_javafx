package com.bespalov.shop.pane;

import com.bespalov.shop.controller.InformtableController;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InformtablePane {
    private Stage stage;
    private Product product;
    private Shop shop;

    public InformtablePane(Stage stage) {
        this.stage = stage;
    }

    public void showInformtable() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/dialog_window/InformtableOverview.fxml"));
        AnchorPane anchorPane = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initOwner(stage);
        dialogStage.setTitle("Show information");
        Scene scene = new Scene(anchorPane);
        dialogStage.setScene(scene);

        InformtableController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setProduct(product);
        controller.setShop(shop);

        dialogStage.showAndWait();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
