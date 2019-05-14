package com.shop.spring_shop_store.dao.repository;

import com.shop.spring_shop_store.model.AdminAuthentication;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticateAdminDAO extends CrudRepository<AdminAuthentication, Integer> {
    Object findAdminAuthenticationByUsernameAndPassword(String username, String password);
}
