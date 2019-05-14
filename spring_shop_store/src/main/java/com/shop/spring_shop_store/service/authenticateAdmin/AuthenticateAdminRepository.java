package com.shop.spring_shop_store.service.authenticateAdmin;

import com.shop.spring_shop_store.model.AdminAuthentication;


public interface AuthenticateAdminRepository {

    Object getUsernamePasswordAdmin(AdminAuthentication adminAuthentication) throws NoSuchMethodException;

}
