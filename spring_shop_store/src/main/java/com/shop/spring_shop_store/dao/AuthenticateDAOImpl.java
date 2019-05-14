package com.shop.spring_shop_store.dao;

import com.shop.spring_shop_store.dao.repository.AuthenticateDAO;
import com.shop.spring_shop_store.model.AuthenticationEmployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticateDAOImpl {
    @Autowired
    private AuthenticateDAO authenticateDAO;

    public AuthenticationEmployer getUsernamePassword(String username, String password) {
        AuthenticationEmployer authentication = (AuthenticationEmployer) authenticateDAO.findAuthenticationByUsernameAndPassword(username, password);
        return authentication;
    }
}
