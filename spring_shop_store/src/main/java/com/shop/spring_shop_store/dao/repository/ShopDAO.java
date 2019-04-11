package com.shop.spring_shop_store.dao.dao_repository;

import com.shop.spring_shop_store.model.Shop;
import org.springframework.data.repository.CrudRepository;

public interface ShopDAO extends CrudRepository<Shop, Integer> {
    Object findShopByIdShop(Integer id);

    Object findShopByTitle(String title);
}
