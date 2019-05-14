package com.shop.spring_shop_store.dao.repository;

import com.shop.spring_shop_store.model.Certificate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateProtocolDAO extends CrudRepository<Certificate, Integer> {
    Object findByCertificateProtocol(String certificateProtocol);
}
