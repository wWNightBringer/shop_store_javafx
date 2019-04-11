package com.shop.spring_shop_store.dao;

import com.shop.spring_shop_store.dao.repository.ProductDAO;
import com.shop.spring_shop_store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAOImpl {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductDAO daoRepository;

    public Object getProductByTitle(String title) {
        Product product = (Product) daoRepository.getProductByTitle(title);
        return product;
    }

    public Object getTitleById(Integer id) {
        Product product = (Product) daoRepository.findTitleById(id);
        return product;
    }

    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        for (Product product : daoRepository.findAll()) {
            productList.add(product);
        }
        return productList;
    }

    public void removeProductById(Integer id) {
        daoRepository.deleteById(id);
    }

    public void addNewProduct(Product product) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = {
                product.getTitle(), product.getIncomingDate(), product.getSerialNumber(), product.getShopId(), product.getCount(), product.getCondition()
        };
        jdbcTemplate.update("INSERT INTO product(Title,Incoming_date,Serial_number,Shop_Id,Count,`Condition`) VALUES (?,?,?,?,?,?)", args);
    }

    public Product updateProduct(Product product) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = {
                product.getTitle(), product.getIncomingDate(), product.getSerialNumber(), product.getShopId(),
                product.getCount(), product.getCondition(), product.getId()
        };
        jdbcTemplate.update("UPDATE product SET Title=?,Incoming_date=?,Serial_number=?,Shop_Id=?,Count=?,`Condition`=? WHERE product.ID=?", args);
        return product;
    }


}
