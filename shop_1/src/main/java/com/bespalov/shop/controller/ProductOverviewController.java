package com.bespalov.shop.controller;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.client.Client;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.database_action.RemoveElement;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.pane.EditProductPane;
import com.bespalov.shop.pane.SearchPane;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
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
    private RemoveElement removeElement;
    private final String host = "192.168.0.111";
    private final int port = 9000;

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
    public void remove() throws IOException, JAXBException, InterruptedException {

        int selectItem = productTable.getSelectionModel().getSelectedIndex();
        if (selectItem >= 0) {
            client = new Client(host, port);
            client.actionToDatabase(productTable.getItems().get(selectItem), "Remove");
            productTable.getItems().remove(selectItem);

        } else {
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
        boolean okClicked = editProductPane.showProductDialog(product);
        if (okClicked) {
            client = new Client(host, port);
            try {
                client.actionToDatabase(product, "Add");
            } catch (InterruptedException | JAXBException e) {
                e.printStackTrace();
            }
            ProductData.getProductList().add(product);
            logger.info("All fine");
        }

    }

    @FXML
    private void handleEditPerson() throws IOException {
        Product product = productTable.getSelectionModel().getSelectedItem();
        int index = productTable.getSelectionModel().getSelectedIndex();

        if (product != null) {
            boolean okClicked = editProductPane.showProductDialog(product);
            client = new Client(host, port);
            try {
                client.actionToDatabase(product, "Update");
            } catch (InterruptedException | IOException | JAXBException e) {
                e.printStackTrace();
            }
            ProductData.getProductList().set(index, product);
            logger.info("Update ended!");

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
    @FXML
    private void close(javafx.scene.input.KeyEvent event){
        if(KeyCode.ESCAPE==event.getCode()){
            System.exit(0);
        }
    }
}
