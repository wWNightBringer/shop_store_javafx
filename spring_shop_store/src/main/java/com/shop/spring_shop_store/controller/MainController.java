package com.shop.spring_shop_store.controller;

import com.shop.spring_shop_store.model.*;
import com.shop.spring_shop_store.service.authenticateAdmin.AuthenticateAdminRepository;
import com.shop.spring_shop_store.service.authenticateEmployer.AuthenticateEmployerRepository;
import com.shop.spring_shop_store.service.certificate.CertificateProtocolRepository;
import com.shop.spring_shop_store.service.products.ProductRepository;
import com.shop.spring_shop_store.service.shop.ShopRepository;
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
    private ShopRepository shopRepository;
    private AuthenticateEmployerRepository authenticateEmployerRepository;
    private AuthenticateAdminRepository authenticateAdminRepository;
    private CertificateProtocolRepository certificateProtocolRepository;

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public MainController(ProductRepository productRepository,
                          ShopRepository shopRepository,
                          AuthenticateEmployerRepository authenticateEmployerRepository,
                          AuthenticateAdminRepository authenticateAdminRepository,
                          CertificateProtocolRepository certificateProtocolRepository) {
        this.productRepository = productRepository;
        this.shopRepository = shopRepository;
        this.authenticateEmployerRepository = authenticateEmployerRepository;
        this.authenticateAdminRepository = authenticateAdminRepository;
        this.certificateProtocolRepository = certificateProtocolRepository;
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
        if (authentication(login, password))
            productRepository.addNewProduct(product);
    }

    @PostMapping(value = "updateProduct")
    public Object updateProduct(@RequestParam String login, @RequestParam String password, @RequestBody Product product) throws NoSuchMethodException {
        if (authentication(login, password))
            return productRepository.updateProduct(product);
        return authentication(login, password);
    }

    /**
     * Shop
     */

    @PostMapping(value = "getShopById")
    public Object getShopById(@RequestParam String login, @RequestParam String password, @RequestBody Shop shop) throws NoSuchMethodException {
        if (!authentication(login, password))
            return Boolean.FALSE;
        return shopRepository.getShopById(shop.getIdShop());
    }

    @PostMapping(value = "getIdShopByTitle")
    public Object getIdShopByTitle(@RequestParam String login, @RequestParam String password, @RequestBody Shop shop) throws NoSuchMethodException {
        if (!authentication(login, password))
            return Boolean.FALSE;
        return shopRepository.getIdShopByTitle(shop.getTitle());
    }

    @PostMapping(value = "addNewShop")
    public void addNewShop(@RequestParam String login, @RequestParam String password, @RequestBody Shop shop) throws NoSuchMethodException {
        if (authentication(login, password))
            shopRepository.addNewShop(shop);
    }

    @PostMapping(value = "getAllShop")
    public List getAllShop() throws NoSuchMethodException {
        return shopRepository.getAllShop();
    }

    /**
     * Authentication
     */
    @PostMapping(value = "getUsernamePassword")
    public Object getUsernamePassword(@RequestParam String login, @RequestParam String password, @RequestBody AuthenticationEmployer authenticationEmployer) throws NoSuchMethodException {
        if (!authentication(login, password))
            return Boolean.FALSE;
        return authenticateEmployerRepository.getUsernamePassword(authenticationEmployer);
    }
    @PostMapping(value = "getCertificateProtocol")
    public Object getCertificateProtocol(@RequestParam String login, @RequestParam String password, @RequestBody Certificate certificate) throws NoSuchMethodException {
        if (!authentication(login, password))
            return Boolean.FALSE;
        return certificateProtocolRepository.getCertificateProtocol(certificate);
    }
    @PostMapping(value = "getUsernamePasswordAdmin")
    public Object getUsernamePasswordAdmin(@RequestParam String login, @RequestParam String password, @RequestBody AdminAuthentication adminAuthentication) throws NoSuchMethodException {
        if (!authentication(login, password))
            return Boolean.FALSE;
        return authenticateAdminRepository.getUsernamePasswordAdmin(adminAuthentication);
    }


    private Boolean authentication(String login, String password) {
        if (!login.equalsIgnoreCase("test") || !password.equalsIgnoreCase("test")) {
            logger.info("Invalid login or password");
            return false;
        }
        return true;
    }


}
