package com.shop.spring_shop_store.service.products;

import com.shop.spring_shop_store.dao.ProductDAORepositoryImpl;
import com.shop.spring_shop_store.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductRepository {
    private Logger logger = LoggerFactory.getLogger(ProductService.class);
    private ProductDAORepositoryImpl productDAORepository;

    @Autowired
    public ProductService(ProductDAORepositoryImpl daoRepository) {
        this.productDAORepository = daoRepository;
    }

    @Override
    public Object getProductByTitle(String title) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getProductByTitle", String.class)));
        logger.info(String.format("Request %s", title));
        Object product = productDAORepository.getProductByTitle(title);
        logger.info(String.format("Response %s", product));
        return product;
    }

    @Override
    public Object getTitleById(Integer id) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getTitleById", Integer.class)));
        logger.info(String.format("Request %s", id));
        Object object = productDAORepository.getTitleById(id);
        logger.info(String.format("Response %s", object));
        return object;
    }

    @Override
    public List getAllProduct() throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getAllProduct")));
        List<Product> productList = productDAORepository.getAllProduct();
        logger.info(String.format("Response %s", productList));
        return productList;
    }

    @Override
    public void removeProductById(Integer id) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("removeProductById", Integer.class)));
        productDAORepository.removeProductById(id);
    }

    @Override
    public void addNewProduct(Product product) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("addNewProduct", Product.class)));
        logger.info(String.format("Request %s", product));
        productDAORepository.addNewProduct(product);
    }
}
