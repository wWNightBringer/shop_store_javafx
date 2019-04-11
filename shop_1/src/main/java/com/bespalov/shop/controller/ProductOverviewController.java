package com.bespalov.shop.controller;


import com.bespalov.shop.config.Connect;
import com.bespalov.shop.config.Languages;
import com.bespalov.shop.config.ProductData;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;
import com.bespalov.shop.pane.EditProductPane;
import com.bespalov.shop.pane.InformtablePane;
import com.bespalov.shop.pane.SearchPane;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.logging.Logger;

public class ProductOverviewController {

    private Connect connect;
    private ProductData productData;
    private Stage stage;

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
    private EditProductPane editProductPane;
    private SearchPane searchPane;
    private InformtablePane informtablePane;

    private com.shop.spring_shop_store.model.Product product;
    private com.shop.spring_shop_store.model.Shop shop;

    public ProductOverviewController() throws UnknownHostException {
        try {
            productData = new ProductData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() throws FileNotFoundException {

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
            Product removeProduct = productTable.getItems().get(selectItem);
            product = new com.shop.spring_shop_store.model.Product();
            product.setId(removeProduct.getIdProduct());
            connect = new Connect("removeProductById");
            connect.outputStream(product);
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
        Product addProduct = new Product();
        boolean okClicked = editProductPane.showProductDialog(addProduct);
        if (okClicked) {
            product = new com.shop.spring_shop_store.model.Product();
            product.setId(addProduct.getIdProduct());
            product.setTitle(addProduct.getTitle());
            product.setIncomingDate(Date.valueOf(addProduct.getIncomingDate()));
            product.setSerialNumber(addProduct.getSerialNumber());
            product.setCount(Integer.parseInt(addProduct.getCount()));
            product.setCondition(addProduct.getCondition());
            connect = new Connect("addNewProduct");
            connect.outputStream(product);
            ProductData.getProductList().add(addProduct);
            logger.info("All fine");
        }
    }

    @FXML
    private void handleEditProduct() throws IOException {
        Product updateProduct = productTable.getSelectionModel().getSelectedItem();
        int index = productTable.getSelectionModel().getSelectedIndex();

        if (index > 0) {
            boolean okClicked = editProductPane.showProductDialog(updateProduct);
            if (okClicked) {
                product = new com.shop.spring_shop_store.model.Product();
                product.setId(updateProduct.getIdProduct());
                product.setTitle(updateProduct.getTitle());
                product.setIncomingDate(Date.valueOf(updateProduct.getIncomingDate()));
                product.setSerialNumber(updateProduct.getSerialNumber());
                product.setCount(Integer.parseInt(updateProduct.getCount()));
                product.setCondition(updateProduct.getCondition());
                connect = new Connect("updateProduct");
                connect.outputStream(product);
                ProductData.getProductList().set(index, updateProduct);
                logger.info("Update ended!");
            }
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
    private void handleSearchProduct() throws IOException {
        int index = productTable.getSelectionModel().getSelectedIndex();
        shop = new com.shop.spring_shop_store.model.Shop();
        shop.setIdShop(productTable.getSelectionModel().getSelectedItem().getShopId());

        connect = new Connect("getShopById");
        ObjectMapper objectMapper = new ObjectMapper();
        shop = objectMapper.readValue(connect.inputStream(shop), com.shop.spring_shop_store.model.Shop.class);

        if (index > 0) {
            Product searchProduct = productTable.getSelectionModel().getSelectedItem();
            informtablePane = new InformtablePane(stage);
            informtablePane.setProduct(searchProduct);
            informtablePane.setShop(new Shop(shop.getIdShop(), shop.getAddress(), shop.getTitle()));
            informtablePane.showInformtable();
        } else {
            searchPane.showSearchStage();
        }

    }

    @FXML
    private void close(javafx.scene.input.KeyEvent event) {
        if (KeyCode.ESCAPE == event.getCode()) {
            System.exit(0);
        }
    }
}
