package com.bespalov.shop.config;

import com.bespalov.shop.model.Product;
import com.bespalov.shop.model.ProductWrapper;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class JAXBInit {
    private Stage stage;
    private List<Product> productList = ProductData.getProductList();

    public void loadProductFromSystem(File file, Paths paths) throws IOException, JAXBException {

    }

    public void saveProcuctInSystem() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ProductWrapper.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        ProductWrapper productWrapper = new ProductWrapper();
        productWrapper.setProductList(ProductData.getProductList());
        marshaller.marshal(productWrapper, java.nio.file.Paths.get("shop_1/src/main/resources/xml/product.xml").toFile());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
