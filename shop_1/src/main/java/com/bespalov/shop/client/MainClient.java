package com.bespalov.shop.client;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.config.Paths;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("192.168.0.111", 9000);
        client.initClient();
        try {
            StringReader stringReader = new StringReader(client.getIn());
            System.out.println(client.getIn());
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ProductWrapper wrapper = (ProductWrapper) unmarshaller.unmarshal(stringReader);
            for (Product p : wrapper.getProductList()) {
                System.out.println(p.getTitle()+" "+p.getSerialNumber()+" "+p.getIncomingDate());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
