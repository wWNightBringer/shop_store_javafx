package com.bespalov.shop.client;

import com.bespalov.shop.MainClass;
import com.bespalov.shop.config.JAXBInit;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Client {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private String inputData;
    private JAXBInit jaxbInit;
    private Logger logger = Logger.getLogger(Client.class.getName());

    public Client(String host, int post) throws IOException {
        socket = new Socket(host, post);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void getDataForTable() {
        logger.info("Client is connecting");
        try {
            if (socket.isConnected()) {
                writer.println("getAll");
                Thread.sleep(1000);
                if (reader.ready()) {
                    inputData = reader.readLine();
                }
                writer.close();
                reader.close();
            } else
                JOptionPane.showMessageDialog(null, "Server not ready");
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void actionToDatabase(Product product, String action) throws InterruptedException, IOException, JAXBException {
        jaxbInit = new JAXBInit();
        if (socket.isConnected()) {
            Thread.sleep(1000);
            List<Product> list = new ArrayList<>();
            list.add(product);

            if (action.equalsIgnoreCase("Remove")) {
                writer.println("Remove");
                writer.println(jaxbInit.requestData(list));
            }
            if (action.equalsIgnoreCase("Add")) {
                writer.println("Add");
                writer.println(jaxbInit.requestData(list));

            }
            if (action.equalsIgnoreCase("Update")) {
                writer.println("Update");
                writer.println(jaxbInit.requestData(list));
            }
            if (action.equalsIgnoreCase("Back"))
                writer.println("Back");
            if (action.equalsIgnoreCase("Save"))
                writer.println("Save");

            writer.close();
            reader.close();
        }
    }

    public String getInputData() {
        return inputData;
    }

    public Socket getSocket() {
        return socket;
    }
}
