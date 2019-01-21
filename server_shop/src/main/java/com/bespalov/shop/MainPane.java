package com.bespalov.shop;

import com.bespalov.shop.config.Factory;
import com.bespalov.shop.impl.ProductDAOImpl;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.Shop;
import com.bespalov.shop.server.ServerInit;
import com.bespalov.shop.validators.ValidatorInit;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLData;

//TODO how install database.sql
//TODO изменить shop_id на правильный
public class MainPane {

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ServerInit serverInit = new ServerInit(9000);
        serverInit.initServer();
    }



}
