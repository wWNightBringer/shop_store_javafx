package com.shop.spring_shop_store.service.products;

import com.shop.spring_shop_store.model.Product;

import java.util.List;

public interface ProductRepository {
    Object getProductByTitle(String title) throws NoSuchMethodException;

    Object getTitleById(Integer id) throws NoSuchMethodException;

    List getAllProduct() throws NoSuchMethodException;

    void removeProductById(Integer id) throws NoSuchMethodException;

    void addNewProduct(Product product) throws NoSuchMethodException;

}
