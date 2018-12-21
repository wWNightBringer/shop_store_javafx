package com.bespalov.shop.config;

import com.bespalov.shop.client.Client;
import com.bespalov.shop.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class ProductData {
    private static ObservableList<Product> productList;
    private static Client client;
    private static JAXBInit jaxbInit;

    static {
        productList = FXCollections.observableArrayList();
        try {
            client = new Client("192.168.0.111", 9000);
            jaxbInit = new JAXBInit();
            client.getDataForTable();
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    public ProductData() throws JAXBException {
        for (Product product : jaxbInit.responseData(client.getInputData()).getProductList()) {
            productList.add(new Product(product.getTitle(), product.getIncomingDate(), product.getSerialNumber(),
                    Integer.valueOf(product.getCount()), product.getCondition()));
        }
    }

    public static ObservableList<Product> getProductList() {
        return productList;
    }
}
