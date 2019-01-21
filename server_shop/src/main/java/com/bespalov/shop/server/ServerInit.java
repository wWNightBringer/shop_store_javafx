package com.bespalov.shop.server;

import com.bespalov.shop.config.Factory;
import com.bespalov.shop.dao.DatabaseDAO;
import com.bespalov.shop.database.Connection;
import com.bespalov.shop.impl.ProductDAOImpl;
import com.bespalov.shop.util.JAXBUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerInit {
    private ThreadPoolExecutor executor;
    private ServerSocket serverSocket;
    private Socket socket;

    public ServerInit(int port) throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);
        executor = new ThreadPoolExecutor(2, 3, 2, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(500));
    }

    public void initServer() {
        try (ServerSocket serverSocket = this.serverSocket) {
            while (true) {
                socket = serverSocket.accept();
                executor.execute(new ServerThread(socket));
                if (executor.getActiveCount() < 0) {
                    executor.shutdown();
                }
            }
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}

