package com.bespalov.shop.impl;

import com.bespalov.shop.dao.DatabaseDAO;
import com.bespalov.shop.database.Connection;
import com.bespalov.shop.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO this class use for get data from database
public class ProductDAOImpl implements DatabaseDAO {
    private Connection connection;
    private CommandDAOImpl commandDAOImpl;
    private List<Product> productList;

    public ProductDAOImpl() throws ClassNotFoundException {
        connection = new Connection();
        commandDAOImpl = new CommandDAOImpl();
    }

    public List<Product> getAllElements() {
        productList = new ArrayList<>();
        try {
            ResultSet set = connection.initConnection().executeQuery(commandDAOImpl.getAllProduct());
            while (set.next()) {
                productList.add(new Product(set.getString("Title"), set.getString("Incoming_date"),
                        set.getString("Serial_number"), set.getInt("Count"), set.getString("Condition")
                        ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (productList.isEmpty())
                productList.clear();
        }
        return productList;
    }

    public Product getElement(String title) {
        Product product = null;
        try {
            ResultSet set = connection.initConnection().executeQuery(commandDAOImpl.getProduct("'" + title + "'"));
            while (set.next()) {
                product = new Product(set.getString("Title"), set.getString("Incoming_date"),
                        set.getString("Serial_number"), set.getInt("Count"), set.getString("Condition")
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void addElement(Product product) {
        connection.getPreparedStatement(commandDAOImpl.addProduct(product));
    }

    public void update(String title, Product product) {

        connection.getPreparedStatement(commandDAOImpl.updateProduct(title, product));

    }

    public void deleteElement(String title) {
        try {
            connection.initConnection().execute(commandDAOImpl.deleteProduct(title));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAllElements() {

    }

    @Override
    public String toString() {
        return "ProductDAOImpl{" +
                "productList=" + productList +
                '}';
    }
}
