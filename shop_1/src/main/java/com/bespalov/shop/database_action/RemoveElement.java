package com.bespalov.shop.database_action;

import com.bespalov.shop.client.Client;
import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveElement {
    private Client client;
    private JAXBInit jaxbInit;
    private ProductWrapper productWrapper;

    public RemoveElement() {
        jaxbInit = new JAXBInit();
        productWrapper = new ProductWrapper();

    }

    public void removeElementFromBase(Product product) throws IOException, JAXBException, InterruptedException {

    }
}
