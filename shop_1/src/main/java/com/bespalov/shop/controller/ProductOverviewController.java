package com.bespalov.shop.controller;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.client.Client;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.pane.EditProductPane;
import com.bespalov.shop.pane.SearchPane;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.logging.Logger;

public class ProductOverviewController {

    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> title;
    @FXML
    private TableColumn<Product, String> incomingDate;
    @FXML
    private TableColumn<Product, String> serialNumber;
    @FXML
    private TableColumn<Product, String> count;
    @FXML
    private TableColumn<Product, String> condition;
    @FXML
    private Button search;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button remove;

    private Logger logger = Logger.getLogger(ProductUpdateDialogController.class.getName());
    private ProductData productData;
    private Stage stage;
    private EditProductPane editProductPane;
    private SearchPane searchPane;
    private Client client;

    public ProductOverviewController() {
        try {
            productData = new ProductData();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize() {
        title.setCellValueFactory(v -> v.getValue().titleProperty());
        incomingDate.setCellValueFactory(v -> v.getValue().incomingDateProperty().asString());
        serialNumber.setCellValueFactory(v -> v.getValue().serialNumberProperty());
        count.setCellValueFactory(v -> v.getValue().countProperty());
        condition.setCellValueFactory(v -> v.getValue().conditionProperty());
        title.setText(Languages.getResourceBundle().getString("title"));
        incomingDate.setText(Languages.getResourceBundle().getString("incomingDate"));
        serialNumber.setText(Languages.getResourceBundle().getString("serialNumber"));
        count.setText(Languages.getResourceBundle().getString("count"));
        condition.setText(Languages.getResourceBundle().getString("condition"));
        search.setText(Languages.getResourceBundle().getString("search"));
        add.setText(Languages.getResourceBundle().getString("new"));
        update.setText(Languages.getResourceBundle().getString("update"));
        remove.setText(Languages.getResourceBundle().getString("remove"));
    }

    public void setMainApp(Stage stage) {
        this.stage = stage;
        productTable.setItems(ProductData.getProductList());
        editProductPane = new EditProductPane(stage);
        searchPane = new SearchPane(stage);
    }

    @FXML
    public void remove() {
        int selectItem = productTable.getSelectionModel().getSelectedIndex();
        if (selectItem >= 0)
            productTable.getItems().remove(selectItem);
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(stage);
            alert.setTitle("No selection");
            alert.setHeaderText("No product selected");
            alert.setContentText("Choose product in table");
            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewProduct() throws IOException {
        Product product = new Product();
        client = new Client("192.168.0.111", 9000);
        try {
            client.sendDataForDatabase();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        boolean okClicked = editProductPane.showProductDialog(product);
        if (okClicked) {


            ProductData.getProductList().add(product);
            logger.info("All fine");
        }

    }

    @FXML
    private void handleEditPerson() throws IOException {
        Product product = productTable.getSelectionModel().getSelectedItem();
        if (product != null) {
            boolean okClicked = editProductPane.showProductDialog(product);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("No product");
            alert.setHeaderText("No selected");
            alert.setContentText("Choose correct possible");
            alert.showAndWait();
        }


    }

    @FXML
    private void handleSearchPerson() throws IOException {
        searchPane.showSearchStage();
    }
}
