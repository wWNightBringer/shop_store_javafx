package com.shop.spring_shop_store.dao;

import com.shop.spring_shop_store.dao.repository.AuthenticateAdminDAO;
import com.shop.spring_shop_store.model.AdminAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAOImpl {
    @Autowired
    private AuthenticateAdminDAO authenticateAdminDAO;

    public AdminAuthentication getUsernamePasswordAdmin(String username, String password) {
        AdminAuthentication adminAuthentication = (AdminAuthentication) authenticateAdminDAO.findAdminAuthenticationByUsernameAndPassword(username, password);
        return adminAuthentication;
    }
}
