package com.shop.spring_shop_store.service.authenticateAdmin;

import com.shop.spring_shop_store.dao.AdminDAOImpl;
import com.shop.spring_shop_store.model.AdminAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateAdminService implements AuthenticateAdminRepository {

    private Logger logger = LoggerFactory.getLogger(AuthenticateAdminService.class);
    private AdminDAOImpl adminDAO;

    @Autowired
    public AuthenticateAdminService(AdminDAOImpl adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public Object getUsernamePasswordAdmin(AdminAuthentication adminAuthentication) throws NoSuchMethodException {
        logger.info(String.format("== %s, %s ==", getClass().getName(), getClass().getMethod("getUsernamePasswordAdmin", AdminAuthentication.class).getName()));
        logger.info(String.format("Request %s", adminAuthentication));
        Object object = adminDAO.getUsernamePasswordAdmin(adminAuthentication.getUsername(), adminAuthentication.getPassword());
        logger.info(String.format("Response %s", object));
        return object;
    }
}
