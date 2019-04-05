package com.shop.spring_shop_store.dao;

import com.shop.spring_shop_store.dao.repository.ProductDAO;
import com.shop.spring_shop_store.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductDAORepositoryImpl {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private ProductDAO daoRepository;
    private List<Product> productList;

    @Autowired
    public ProductDAORepositoryImpl(ProductDAO daoRepository) {
        this.daoRepository = daoRepository;
    }

    public Object getProductByTitle(String title) {
        Product product = (Product) daoRepository.getProductByTitle(title);
        return product;
    }

    public Object getTitleById(Integer id) {
        Product product = (Product) daoRepository.findTitleById(id);
        return product;
    }

    public List<Product> getAllProduct() {
        productList = new ArrayList<>();
        for (Product product : daoRepository.findAll()) {
            productList.add(product);
        }
        return productList;
    }

    public void removeProductById(Integer id) {
        daoRepository.deleteById(id);
    }

    public void addNewProduct(Product product) {
        dataSource = getDataSource();
        jdbcTemplate = new JdbcTemplate(dataSource);
        Object[] args = {
                product.getTitle(), product.getIncomingDate(), product.getSerialNumber(), product.getShopId(), product.getCount(), product.getCondition()
        };
        jdbcTemplate.update("INSERT INTO product(Title,Incoming_date,Serial_number,Shop_Id,Count,`Condition`) VALUES (?,?,?,?,?,?)", args);
    }

    private static DriverManagerDataSource getDataSource() {
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        managerDataSource.setUrl("jdbc:mysql://localhost:8080/server?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        managerDataSource.setUsername("root");
        managerDataSource.setPassword("root");
        return managerDataSource;
    }
}
