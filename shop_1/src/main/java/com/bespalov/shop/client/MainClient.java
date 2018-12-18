package com.bespalov.shop.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client("192.168.0.110", 9000);
        client.initClient();
    }
}
