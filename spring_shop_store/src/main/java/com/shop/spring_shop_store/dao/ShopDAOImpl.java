package com.shop.spring_shop_store.dao;

import com.shop.spring_shop_store.config.DriverManagerServer;
import com.shop.spring_shop_store.dao.repository.ShopDAO;
import com.shop.spring_shop_store.model.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ShopDAOImpl {
    @Autowired
    private ShopDAO shopDAO;
    private static DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private Object[] args;

    static {
        dataSource = DriverManagerServer.getDataSource();
    }


    public Object getShopById(Integer id) {
        Shop shop = (Shop) shopDAO.findShopByIdShop(id);
        return shop;
    }

    public Object getIdShopByTitle(String title) {
        Shop shop = (Shop) shopDAO.findShopByTitle(title);
        return shop;
    }

    public void addNewShop(Shop shop) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        args = new Object[]{
                shop.getAddress(), shop.getTitle()
        };
        jdbcTemplate.update("INSERT INTO shop (Address, Title) VALUES (?,?)", args);
    }

}
