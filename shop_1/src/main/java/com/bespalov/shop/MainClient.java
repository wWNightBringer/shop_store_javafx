package com.bespalov.shop;

import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Language");
        System.out.println(resourceBundle.getString("button.update"));
    }
}
