package com.bespalov.shop.config;

import com.bespalov.shop.dao.DatabaseDAO;
import com.bespalov.shop.impl.ProductDAOImpl;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.repository.ElementRepository;
import com.bespalov.shop.util.JAXBUtil;

import javax.xml.bind.JAXBContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factory {


    private List<DatabaseDAO> databaseDAOList;
    private static JAXBUtil jaxbUtil;

    static {
        jaxbUtil = new JAXBUtil();
    }

    public Factory() throws ClassNotFoundException {
        databaseDAOList = new ArrayList<>();
        databaseDAOList.add(new ProductDAOImpl());
    }

    public void showProduct() {
        for (DatabaseDAO dao : databaseDAOList) {
            dao.getAllElements().forEach(System.out::println);
            jaxbUtil.initJAXB(dao);
        }
    }
}
