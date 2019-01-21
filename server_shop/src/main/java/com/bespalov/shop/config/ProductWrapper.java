package com.bespalov.shop.config;

import com.bespalov.shop.model.Product;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "product_wrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWrapper {
    @XmlElementWrapper(name = "product", nillable = true)
    private List<Product> productList;

    public ProductWrapper() {
        productList = new ArrayList<>();
    }

    public List<Product> getProductList() {
        return productList;
    }
}
