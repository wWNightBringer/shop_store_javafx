package com.bespalov.shop.util;

import com.bespalov.shop.config.ProductWrapper;
import com.bespalov.shop.dao.DatabaseDAO;
import com.bespalov.shop.impl.ProductDAOImpl;
import com.bespalov.shop.model.Product;
import com.bespalov.shop.repository.ElementRepository;
import sun.rmi.runtime.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

//TODO проверить изменить, Rollback, Threads
public class JAXBUtil {
    private Path path;
    private JAXBContext context;
    private StringWriter stringWriter;
    private Logger logger = Logger.getLogger(JAXBUtil.class.getName());

    public JAXBUtil() {
        path = Paths.get("shop_1/src/main/resources/shop_store.xml");

    }

    public StringWriter initJAXB(DatabaseDAO databaseDAO) {
        stringWriter = new StringWriter();
        if (databaseDAO == null) {
            throw new NullPointerException();
        }
        try {
            ProductWrapper wrapper = new ProductWrapper();
            wrapper.getProductList().addAll((Collection<? extends Product>) databaseDAO.getAllElements());
            context = JAXBContext.newInstance(Product.class, ProductWrapper.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(wrapper, stringWriter);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return stringWriter;
    }

    public ProductWrapper getJAXB(String unmarshall) {
        if (unmarshall == null) {
            throw new NullPointerException();
        }
        StringReader stringReader = new StringReader(unmarshall);
        try {
            context = JAXBContext.newInstance(ProductWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductWrapper productWrapper = (ProductWrapper) unmarshaller.unmarshal(stringReader);
            return productWrapper;
        } catch (JAXBException e) {
            e.printStackTrace();

        }
        throw new NullPointerException();
    }

}
