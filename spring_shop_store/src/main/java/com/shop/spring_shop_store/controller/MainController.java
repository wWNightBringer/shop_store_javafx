package com.shop.spring_shop_store.controller;

import com.shop.spring_shop_store.model.Product;
import com.shop.spring_shop_store.service.products.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    private ProductRepository productRepository;
    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Products
     */
    @PostMapping(value = "getAllProduct")
    public List getAllProduct() throws NoSuchMethodException {
        return productRepository.getAllProduct();
    }

    @PostMapping(value = "getProductByTitle")
    public Object getProductByTitle(@RequestParam String login, @RequestParam String password, @RequestBody Product product) throws NoSuchMethodException {
        if (!authentication(login, password))
            return Boolean.FALSE;
        return productRepository.getProductByTitle(product.getTitle());
    }

    @PostMapping(value = "getTitleById")
    public Object getTitleById(@RequestParam String login, @RequestParam String password, @RequestBody Product product) throws NoSuchMethodException {
        if (!authentication(login, password))
            return null;
        return productRepository.getTitleById(product.getId());
    }

    @PostMapping(value = "removeProductById")
    public void removeProductById(@RequestParam String login, @RequestParam String password, @RequestBody Product product) throws NoSuchMethodException {
        if (authentication(login, password))
            productRepository.removeProductById(product.getId());
    }

    @PostMapping(value = "addNewProduct")
    public void addNewProduct(@RequestParam String login, @RequestParam String password, @RequestBody Product product) throws NoSuchMethodException {
        System.out.println(product);
        if (authentication(login, password))
            productRepository.addNewProduct(product);
    }

    private Boolean authentication(String login, String password) {
        if (!login.equalsIgnoreCase("test") || !password.equalsIgnoreCase("test")) {
            logger.info("Invalid login or password");
            return false;
        }
        return true;
    }


}
