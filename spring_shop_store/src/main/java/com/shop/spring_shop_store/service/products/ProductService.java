package com.shop.spring_shop_store.service.products;

import com.shop.spring_shop_store.dao.ProductDAOImpl;
import com.shop.spring_shop_store.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductRepository {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);
    private ProductDAOImpl productDAORepository;

    @Autowired
    public ProductService(ProductDAOImpl productDAORepository) {
        this.productDAORepository = productDAORepository;
    }

    @Override
    public Object getProductByTitle(String title) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getProductByTitle", String.class).getName()));
        logger.info(String.format("Request %s", title));
        Object product = productDAORepository.getProductByTitle(title);
        logger.info(String.format("Response %s", product));
        return product;
    }

    @Override
    public Object getTitleById(Integer id) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getTitleById", Integer.class).getName()));
        logger.info(String.format("Request %s", id));
        Object object = productDAORepository.getTitleById(id);
        logger.info(String.format("Response %s", object));
        return object;
    }

    @Override
    public List getAllProduct() throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getAllProduct").getName()));
        List<Product> productList = productDAORepository.getAllProduct();
        logger.info(String.format("Response %s", productList));
        return productList;
    }

    @Override
    public void removeProductById(Integer id) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("removeProductById", Integer.class).getName()));
        productDAORepository.removeProductById(id);
    }

    @Override
    public void addNewProduct(Product product) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("addNewProduct", Product.class).getName()));
        logger.info(String.format("Request %s", product));
        productDAORepository.addNewProduct(product);
    }

    @Override
    public Object updateProduct(Product product) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("updateProduct", Product.class).getName()));
        logger.info(String.format("Request %s", product));
        Object object = productDAORepository.updateProduct(product);
        logger.info(String.format("Response %s", object));
        return true;
    }
}
