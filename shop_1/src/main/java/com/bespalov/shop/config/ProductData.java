package com.bespalov.shop.config;

import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ProductData {
    private static ObservableList<Product> productList;
    private static ObservableList<Shop> shopList;
    private static ObservableList<String> address;
    private Connect connect;


    static {
        productList = FXCollections.observableArrayList();
        shopList = FXCollections.observableArrayList();
        address = FXCollections.observableArrayList();
    }

    public ProductData() throws IOException {
        connect = new Connect("getAllProduct");
        ObjectMapper objectMapper = new ObjectMapper();
        com.shop.spring_shop_store.model.Product[] products = objectMapper.readValue(connect.inputStream(null), com.shop.spring_shop_store.model.Product[].class);
        for (com.shop.spring_shop_store.model.Product product : products) {
            productList.add(new Product(product.getId(), product.getTitle(),
                    Instant.ofEpochMilli(product.getIncomingDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate(),
                    product.getSerialNumber(), product.getShopId(),
                    product.getCount(), product.getCondition()));
        }
        connect = new Connect("getAllShop");
        objectMapper = new ObjectMapper();
        com.shop.spring_shop_store.model.Shop[] shops = objectMapper.readValue(connect.inputStream(null), com.shop.spring_shop_store.model.Shop[].class);
        for (com.shop.spring_shop_store.model.Shop shop : shops) {
            shopList.add(new Shop(shop.getIdShop(), shop.getAddress(), shop.getTitle()));
            address.add(shop.getAddress());
        }
    }

    public static ObservableList<Product> getProductList() {
        return productList;
    }

    public static ObservableList<Shop> getShopList() {
        return shopList;
    }

    public static ObservableList<String> getAddressList() {
        return address;
    }
}
