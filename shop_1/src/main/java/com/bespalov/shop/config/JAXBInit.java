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
import java.util.List;

public class JAXBInit {

    public String requestData(ProductWrapper productWrapper) throws IOException, JAXBException {
        StringWriter stringWriter = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(ProductWrapper.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(productWrapper, stringWriter);
        return stringWriter.toString();
    }

    public ProductWrapper responseData(String lineUnmarshall) throws JAXBException {
        StringReader stringReader = new StringReader(lineUnmarshall);
        JAXBContext context = JAXBContext.newInstance(ProductWrapper.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ProductWrapper productWrapper = (ProductWrapper) unmarshaller.unmarshal(stringReader);
        return productWrapper;
    }

}
