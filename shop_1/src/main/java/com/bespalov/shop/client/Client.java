package com.bespalov.shop.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    private Scanner scanner;

    public Client(String host, int post) throws IOException {
        socket = new Socket(host, post);
    }

    public void initClient() {
        try {
            System.out.println(socket.isConnected());
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                System.out.print("Enter data: ");
                String output = scanner.nextLine();
                writer.println(output);
                String in;
                Thread.sleep(1000);
                while (true) {
                    if (reader.ready()) {
                        in = reader.readLine();
                        System.out.println(in);
                    } else
                        break;
                }
                if (output.equalsIgnoreCase("Bye")) {
                    break;
                }
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
