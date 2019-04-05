package com.bespalov.shop.server;

import com.bespalov.shop.config.Factory;
import com.bespalov.shop.database.Connection;
import com.bespalov.shop.impl.ProductDAOImpl;
import com.bespalov.shop.util.JAXBUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

public class ServerThread extends Thread {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private Connection connection;
    private ProductDAOImpl productDAO;
    private JAXBUtil jaxbUtil;
    private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ServerInit.class.getName());
    private Socket socket;

    public ServerThread(Socket socket) throws ClassNotFoundException, IOException {
        this.socket = socket;
        productDAO = new ProductDAOImpl();
        jaxbUtil = new JAXBUtil();
        connection = new Connection();
    }

    private synchronized void initThread(Socket socket) {
        try {
            System.out.println("Socket is connect " + socket.isConnected());
            if (socket.isConnected()) {
                printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Wait your command");
                String s = bufferedReader.readLine();

                if (s.equalsIgnoreCase("getAll")) {
                    logger.info(s);
                    printWriter.println(jaxbUtil.initJAXB(productDAO));
                }
                if (s.equalsIgnoreCase("Back")) {
                    logger.info(s);
                    connection.getPreparedStatement("ROLLBACK;").execute();
                }
                if (s.equalsIgnoreCase("Save")) {
                    logger.info(s);
                    connection.getPreparedStatement("COMMIT;").execute();
                }
                if (s.equalsIgnoreCase("Remove")) {
                    logger.info(s);
                    connection.getPreparedStatement("BEGIN;").execute();
                    String line = bufferedReader.readLine();
                    productDAO.deleteElement(jaxbUtil.getJAXB(line).getProductList().get(0).getTitle());
                }
                if (s.equalsIgnoreCase("Add")) {
                    logger.info(s);
                    connection.getPreparedStatement("BEGIN;").execute();
                    String line = bufferedReader.readLine();
                    productDAO.addElement(jaxbUtil.getJAXB(line).getProductList().get(0));
                }
                if (s.equalsIgnoreCase("Update")) {
                    logger.info(s);
                    connection.getPreparedStatement("BEGIN;").execute();
                    String line = bufferedReader.readLine();
                    productDAO.update(jaxbUtil.getJAXB(line).getProductList().get(0).getTitle(),
                            jaxbUtil.getJAXB(line).getProductList().get(0));
                }
                if (s.equalsIgnoreCase("exit")) {
                    printWriter.println("Bye");

                }
            }
            printWriter.close();
            bufferedReader.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void run() {
        initThread(socket);
    }
}


