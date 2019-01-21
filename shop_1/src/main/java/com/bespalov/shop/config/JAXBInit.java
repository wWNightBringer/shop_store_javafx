package com.bespalov.shop.config;

import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;
import com.bespalov.shop.repository.ShopStoreRepository;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class JAXBInit {
    private Logger logger = Logger.getLogger(JAXBInit.class.getName());

    public StringWriter requestData(List list) throws IOException, JAXBException {
        if (!list.isEmpty()) {
            ProductWrapper productWrapper = new ProductWrapper();
            StringWriter stringWriter = new StringWriter();
            productWrapper.setProductList(list);
            JAXBContext context = JAXBContext.newInstance(ProductWrapper.class);

            Marshaller marshaller = context.createMarshaller();

            marshaller.marshal(productWrapper, stringWriter);
            marshaller.marshal(productWrapper, Paths.get("shop_1/src/main/resources/xml/product.xml").toFile());
            return stringWriter;
        } else {
            return null;
        }

    }

    public ProductWrapper responseData(String lineUnmarshall) throws JAXBException {
        StringReader stringReader = new StringReader(lineUnmarshall);
        JAXBContext context = JAXBContext.newInstance(ProductWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ProductWrapper productWrapper = (ProductWrapper) unmarshaller.unmarshal(stringReader);
        return productWrapper;
    }

}
