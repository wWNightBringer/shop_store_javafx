package com.shop.spring_shop_store.service.authenticateEmployer;

import com.shop.spring_shop_store.dao.AuthenticateDAOImpl;
import com.shop.spring_shop_store.model.AuthenticationEmployer;
import com.shop.spring_shop_store.service.products.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateEmployerService implements AuthenticateEmployerRepository {
    private AuthenticateDAOImpl authenticateDAO;
    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    public AuthenticateEmployerService(AuthenticateDAOImpl authenticateDAO) {
        this.authenticateDAO = authenticateDAO;
    }

    @Override
    public Object getUsernamePassword(AuthenticationEmployer authentication) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getUsernamePassword", AuthenticationEmployer.class).getName()));
        logger.info(String.format("Request %s", authentication));
        Object object = authenticateDAO.getUsernamePassword(authentication.getUsername(), authentication.getPassword());
        logger.info(String.format("Response %s", object));
        return object;
    }
}
