package com.shop.spring_shop_store.dao.repository;

import com.shop.spring_shop_store.model.AuthenticationEmployer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticateDAO extends CrudRepository<AuthenticationEmployer, Integer> {
    Object findAuthenticationByUsernameAndPassword(String username, String password);
}
