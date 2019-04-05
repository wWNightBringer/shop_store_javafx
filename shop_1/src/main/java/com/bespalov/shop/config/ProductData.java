package com.bespalov.shop.config;

import com.bespalov.shop.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;

public class ProductData {
    private static ObservableList<Product> productList;
    private Connect connect;


    static {
        productList = FXCollections.observableArrayList();
    }

    public ProductData() throws IOException {
        connect = new Connect();
        ObjectMapper objectMapper = new ObjectMapper();
        com.shop.spring_shop_store.model.Product[] products = objectMapper.readValue(connect.inputStream(), com.shop.spring_shop_store.model.Product[].class);
        for (com.shop.spring_shop_store.model.Product product : products) {
            productList.add(new Product(product.getId(), product.getTitle(),
                    Instant.ofEpochMilli(product.getIncomingDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate(),
                    product.getSerialNumber(),
                    product.getCount(), product.getCondition()));
        }

    }

    public static ObservableList<Product> getProductList() {
        return productList;
    }
}
