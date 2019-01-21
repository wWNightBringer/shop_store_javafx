package com.bespalov.shop.dao;

import com.bespalov.shop.model.Product;
import com.bespalov.shop.repository.ElementRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DatabaseDAO {

    void addElement(Product product);

    void update(String title, Product product);

    void deleteElement(String title);

    ElementRepository getElement(String title);

    List<?> getAllElements();

    void deleteAllElements();
}
