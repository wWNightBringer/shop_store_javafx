package com.shop.spring_shop_store.service.shop;

import com.shop.spring_shop_store.dao.ShopDAOImpl;
import com.shop.spring_shop_store.model.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService implements ShopRepository {
    private Logger logger = LoggerFactory.getLogger(ShopService.class);
    private ShopDAOImpl shopDAO;

    @Autowired
    public ShopService(ShopDAOImpl shopDAO) {
        this.shopDAO = shopDAO;
    }


    @Override
    public Object getShopById(Integer idShop) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getShopById", Integer.class).getName()));
        logger.info(String.format("Request %s", idShop));
        Object object = shopDAO.getShopById(idShop);
        logger.info(String.format("Response %s", object));
        return object;
    }

    @Override
    public Object getIdShopByTitle(String title) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getIdShopByTitle", String.class).getName()));
        logger.info(String.format("Request %s", title));
        Object object = shopDAO.getIdShopByTitle(title);
        logger.info(String.format("Response %s", object));
        return object;
    }

    @Override
    public void addNewShop(Shop shop) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("addNewShop", Shop.class).getName()));
        logger.info(String.format("Request %s", shop));
        shopDAO.addNewShop(shop);
    }

    @Override
    public List getAllShop() throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getAllShop").getName()));
        List shopList = shopDAO.getAllShop();
        logger.info(String.format("Response %s", shopList));
        return shopList;
    }
}
