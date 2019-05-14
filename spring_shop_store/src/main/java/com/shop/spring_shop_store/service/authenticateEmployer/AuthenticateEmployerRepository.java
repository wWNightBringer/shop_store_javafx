package com.shop.spring_shop_store.service.authenticateEmployer;


import com.shop.spring_shop_store.model.AuthenticationEmployer;

public interface AuthenticateEmployerRepository {
    Object getUsernamePassword(AuthenticationEmployer authentication) throws NoSuchMethodException;
}
