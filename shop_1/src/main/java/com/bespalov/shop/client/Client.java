package com.bespalov.shop.client;

import com.bespalov.shop.MainClass;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Scanner scanner;
    private String in;


    public Client(String host, int post) throws IOException {
        socket = new Socket(host, post);

    }

    public void initClient() {
        try {
            System.out.println(socket.isConnected());
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);

            System.out.print("Enter data: ");
            String output = scanner.nextLine();
            writer.println(output);

            Thread.sleep(1000);
            if (reader.ready()) {
                in = reader.readLine();
            }
            writer.close();
            reader.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public String getIn() {
        return in;
    }

}
