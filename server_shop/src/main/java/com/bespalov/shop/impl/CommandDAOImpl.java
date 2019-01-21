package com.bespalov.shop.impl;

import com.bespalov.shop.dao.CommandDAO;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class CommandDAOImpl implements CommandDAO {
    private Properties properties;
    private Path path;

    public CommandDAOImpl() {
        path = Paths.get("server_shop/src/main/resources/command.properties");
        properties = new Properties();
        try {
            properties.load(new FileInputStream(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String getAllProduct() {
        String msg = properties.getProperty("select.all.products");
        msg = String.format(msg, "title", "incoming_date", "serial_number", "Count", "Condition");
        return msg;
    }

    @Override
    public String getProduct(String title) {
        String msg = properties.getProperty("select.one.product");
        msg = String.format(msg, "title", "incoming_date", "serial_number", "Count", "Condition", title);
        return msg;
    }

    @Override
    public String addProduct(Product product) {
        String msg = properties.getProperty("add.one.product");
        msg = String.format(msg, product.getTitle(), product.getIncomingDate(), product.getSerialNumber(), 1, product.getCount(), product.getCondition());
        return msg;
    }

    @Override
    public String updateProduct(String title, Product product) {
        String msg = properties.getProperty("update.one.product");
        msg = String.format(msg, product.getTitle(), product.getIncomingDate(), product.getSerialNumber(), 1, product.getCount(), product.getCondition(), title);
        return msg;
    }

    @Override
    public String deleteProduct(String title) {
        String msg = properties.getProperty("delete.one.product");
        msg = String.format(msg, title);
        return msg;
    }

    @Override
    public String getAllShop() {
        String msg = properties.getProperty("select.all.shop");
        msg = String.format(msg, "title", "Shop", "Address");
        return msg;
    }

    @Override
    public String getShop(String title) {
        String msg = properties.getProperty("select.one.shop");
        msg = String.format(msg, "title", "Shop", "Address", title);
        return msg;
    }

    @Override
    public String addShop(Shop shop) {
        String msg = properties.getProperty("add.one.shop");
        msg = String.format(msg, shop.getTitle(), shop.getCity(), shop.getAddress());
        return msg;
    }

    @Override
    public String updateShop(String title, Shop shop) {
        String msg = properties.getProperty("update.one.shop");
        msg = String.format(msg, shop.getTitle(), shop.getCity(), shop.getAddress(), title);
        return msg;
    }

    @Override
    public String deleteShop(String title) {
        String msg = properties.getProperty("delete.one.shop");
        msg = String.format(msg, title);
        return msg;
    }
}
