package com.bespalov.shop.config;

import com.bespalov.shop.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ProductData {
    private static ObservableList<Product> productList;
    static {
        productList = FXCollections.observableArrayList();
        productList.add(new Product("TV_LG_1123", LocalDate.of(2017, 5, 12),
                "TV112312", 2, "Has"));
        productList.add(new Product("Phone_Nokia_4564", LocalDate.now(),
                "PN456412", 1, "Progress"));
        productList.add(new Product("Iron_Philips_6785", LocalDate.now(),
                "IR678512", 5, "Has"));
    }
    public ProductData() {

    }

    public static ObservableList<Product> getProductList() {
        return productList;
    }
}
