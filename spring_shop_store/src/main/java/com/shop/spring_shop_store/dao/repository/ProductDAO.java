package com.shop.spring_shop_store.dao.repository;

import com.shop.spring_shop_store.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductDAO extends CrudRepository<Product, Integer> {
    Object getProductByTitle(String title);

    Object findTitleById(Integer id);

}
