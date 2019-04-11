package com.shop.spring_shop_store.dao.dao_repository;

import com.shop.spring_shop_store.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
    Object getProductByTitle(String title);

    Object findTitleById(Integer id);

}
