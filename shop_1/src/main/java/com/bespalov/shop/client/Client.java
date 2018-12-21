package com.bespalov.shop.client;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String inputData;
    private JAXBInit jaxbInit;

    public Client(String host, int post) throws IOException {
        socket = new Socket(host, post);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public void getDataForTable() {
        try {
            System.out.println(socket.isConnected());
            writer.println("getAll");
            Thread.sleep(1000);
            if (reader.ready()) {
                inputData = reader.readLine();
            }
            writer.close();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sendDataForDatabase() throws InterruptedException, IOException, JAXBException {
        jaxbInit = new JAXBInit();
        Thread.sleep(1000);
        System.out.println(socket.isConnected());
        ProductWrapper productWrapper = new ProductWrapper();
        List<Product> list = new ArrayList<>();
        list.add(new Product("asd", LocalDate.now(), "asd121", 2, "Has"));
        productWrapper.setProductList(list);
        writer.println(jaxbInit.requestData(productWrapper));
        writer.close();


    }

    public String getInputData() {
        return inputData;
    }

}
