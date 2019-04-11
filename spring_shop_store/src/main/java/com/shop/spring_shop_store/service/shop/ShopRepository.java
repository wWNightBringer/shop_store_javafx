package com.shop.spring_shop_store.service.shop;

import com.shop.spring_shop_store.model.Shop;

public interface ShopRepository {
    Object getShopById(Integer idShop) throws NoSuchMethodException;

    Object getIdShopByTitle(String title) throws NoSuchMethodException;

    void addNewShop(Shop shop) throws NoSuchMethodException;

}
